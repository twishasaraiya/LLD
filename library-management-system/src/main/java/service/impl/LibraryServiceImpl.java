package service.impl;

import model.Book;
import model.BookCopy;
import model.Rack;
import service.BorrowServiceWriter;
import service.LibraryManagementService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryServiceImpl implements LibraryManagementService {
    private final CatalogServiceImpl catalogService;
    private final RackServiceImpl rackService;
    private final BorrowServiceWriter borrowService;


    public LibraryServiceImpl() {
        this.catalogService = CatalogServiceImpl.getCatalogService();
        this.rackService = RackServiceImpl.getRackService();
        this.borrowService = new BorrowServiceImpl();
    }

    @Override
    public void createLibrary(Integer numOfRacks){
//        Library library = new Library(numOfRacks);
        for (int i = 1; i <= numOfRacks; i++) {
            rackService.newRack(i);
        }
        System.out.println("Created library with " + numOfRacks + " racks");
    }

    @Override
    public void addBook(Integer bookId, String bookTitle, List<String> authors, List<String> publishers, List<Integer> bookCopyIds){
        // find rack for book and all the book copies
        // do not assign book one by one bcz then if rack is not found for one of the copies then we will have to rollback on the entire thing
        // assign all book + book copies to rack or none

        // for each book copy id assign a rack in sequential order of availability
        // maybe maintain a queue of empty racks to find the first available rack
        // maintain a map of bookId to rackId for easy search

        if(!rackService.isRackAvailableForBooks(bookCopyIds.size())){
            System.out.println("Rack not available");
            return;
        }

        Book book = new Book(bookId, bookTitle, authors, publishers);
        catalogService.updateBookCatalog(book);

        List<Integer> assignedRacks = new ArrayList<>();
        for (Integer bookCopyId:
                bookCopyIds) {
            BookCopy bookCopy = new BookCopy(bookCopyId, book);
            catalogService.updateBookCopyCatalog(book, bookCopy);
           assignedRacks.add(rackService.assignBookCopyToRack(bookCopyId).getId());
        }

        System.out.println("Added Book to racks: " + assignedRacks);
    }

    @Override
    public void removeBookCopy(Integer bookCopyId){
        // check if bookCopyId exists in which rack
        // remove it from the rack
        // move the rack to the empty queue
        // remove it from catalog as well
        if(!rackService.isBookAvailableOnRack(bookCopyId)){
            System.out.println("Invalid book copy id");
            return;
        }

        catalogService.removeBookCopy(bookCopyId);
        Rack rack = rackService.removeBookCopyFromRack(bookCopyId);
        System.out.println("Removed book copy: " + bookCopyId + " from rack: " + rack.getId());
    }

    @Override
    public void borrowBook(Integer bookId, Integer userId, Date dueDate) {
        // check if bookId is valid

        // check if user has already borrowed 5 books
        // maintain a map of userId to list of books borrowed

        // check if there is a book copy for bookId in any of the racks
        // return / print the rack no

        if(!catalogService.containsBookId(bookId)){
            System.out.println("Invalid Book ID");
            return;
        }

        if(!borrowService.checkIfUserIsAllowedToBorrowBook(userId)){
            System.out.println("Over limit");
            return;
        }


        Optional<Rack> rack = rackService.getClosestBookCopy(
                catalogService.getAllBookCopiesForBook(bookId)
                .stream()
                .map(BookCopy::getId)
                .collect(Collectors.toList())
        );

        if(rack.isPresent()){
            borrow(userId, rack.get().getBookCopyId(), dueDate);
        }else{
            System.out.println("Not available");
        }
    }

    @Override
    public void borrowBookCopy(Integer bookCopyId, Integer userId, Date dueDate) {
        // check if bookCopyId is valid
        // check if there is a book copy for bookCopyId in any of the racks

        // check if user has already borrowed 5 books
        // maintain a map of userId to list of books borrowed

        // return / print the rack no
        if(!rackService.isBookAvailableOnRack(bookCopyId)){
            System.out.println("Invalid Book Copy ID");
            return;
        }

        if (!borrowService.checkIfUserIsAllowedToBorrowBook(userId)){
            System.out.println("Over limit");
            return;
        }

        borrow(userId, bookCopyId, dueDate);
    }

    @Override
    public void returnBookCopy(Integer bookCopyId) {
        // check if bookcopy id is valid
        // remove book copy from the map of user -> books borrowed
        // move book copy back to a rack for availability

        // TODO: Optimize multiple map issue
        if(!catalogService.containsBookCopyId(bookCopyId)){
            System.out.println("Invalid Book Copy ID");
            return;
        }

        returnBook(bookCopyId);
    }

    @Override
    public void printBorrowed(Integer userId) {
        borrowService.printBorrowDetails(userId);
    }

    @Override
    public void search(String attribute, String value) {
        catalogService.search(attribute, value);
    }


    private void borrow(Integer userId, Integer bookCopyId, Date dueDate){
        Rack rack = rackService.removeBookCopyFromRack(bookCopyId);
        borrowService.addBookCopyToUserBorrowDetails(userId, bookCopyId, dueDate);
        System.out.println("Borrowed Book from rack: " + rack.getId());
    }

    private void returnBook(Integer bookCopyId){
        Rack rack = rackService.assignBookCopyToRack(bookCopyId);
        borrowService.removeBookCopyFromUserBorrowDetails(bookCopyId);
        System.out.println("Returned book copy " + bookCopyId + " and added to rack: " + rack.getId());
    }

}
