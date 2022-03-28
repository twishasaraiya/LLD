package service.impl;

import model.Book;
import model.BookCopy;
import model.Library;
import model.Rack;
import service.BorrowServiceWriter;
import service.CatalogService;
import service.LibraryManagementService;
import service.RackService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryServiceImpl implements LibraryManagementService {
    private final CatalogService catalogService;
    private final RackService rackService;
    private BorrowServiceWriter borrowService;

    private Library library;
    private Map<Integer, BookCopy> bookCopyMap;

    public LibraryServiceImpl() {
        this.catalogService = new CatalogServiceImpl();
        this.rackService = new RackServiceImpl();
        this.borrowService = new BorrowServiceImpl();
        bookCopyMap = new HashMap<>();
    }

    @Override
    public void createLibrary(Integer numOfRacks){
        // TODO: Why this list is used?
        List<Rack> rackList = new ArrayList<>();
        for (int i = 1; i <= numOfRacks; i++) {
            rackList.add(rackService.newRack(i));
        }
        this.library = new Library(rackList);
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
            bookCopyMap.put(bookCopyId, bookCopy);
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
        if(!rackService.isBookAvailableOnRack(bookCopyId)){
            System.out.println("Invalid book copy id");
            return;
        }

        // TODO: remove from catalog service
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

        if(catalogService.containsBookId(bookId)){
            System.out.println("Invalid Book ID");
            return;
        }

        if(!borrowService.checkIfUserIsAllowedToBorrowBook(userId)){
            System.out.println("Over limit");
            return;
        }

        // TODO: Return closest rack value
        for (BookCopy bookCopy:
                catalogService.getAllBookCopiesForBook(bookId)) {
            if(rackService.isBookAvailableOnRack(bookCopy.getId())){
                borrow(userId, bookCopy.getId(), dueDate);
            }
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
        if(!bookCopyMap.containsKey(bookCopyId)){
            System.out.println("Invalid Book Copy ID");
            return;
        }

        returnBook(bookCopyId);
    }

    @Override
    public void printBorrowed(Integer userId) {
        // print the value of the map user -> books borrowed
        // TODO: Pending
        borrowService.printBorrowDetails();
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
