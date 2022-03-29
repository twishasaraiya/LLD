package service.impl;

import model.Book;
import model.BookBorrowDetails;
import model.BookCopy;
import model.Rack;
import service.BorrowServiceReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * SINGLETON since all the properties are static
 */
public class CatalogServiceImpl {

    private static Map<Integer, Book> bookMap;
    private static Map<Integer, BookCopy> bookCopyMap;
    private static Map<Integer, Integer> bookCopyIdToBookIdMap;
    private static Map<Integer, List<BookCopy>> bookIdToBookCopiesMap;

    private final RackServiceImpl rackService; // PRINT
    private final BorrowServiceReader borrowService; // PRINT

    private static CatalogServiceImpl catalogService = null;

    private CatalogServiceImpl() {
        this.bookMap = new HashMap<>();
        this.bookCopyMap = new HashMap<>();
        this.bookCopyIdToBookIdMap = new HashMap<>();
        this.bookIdToBookCopiesMap = new HashMap<>();
        this.rackService = RackServiceImpl.getRackService();
        this.borrowService = new BorrowServiceImpl();
        this.catalogService = this;
    }

    public static CatalogServiceImpl getCatalogService(){
        if(catalogService == null){
            return new CatalogServiceImpl();
        }
        return catalogService;
    }

    public Boolean containsBookId(Integer bookId){
        return bookMap.containsKey(bookId);
    }

    public List<BookCopy> getAllBookCopiesForBook(Integer bookId){
        return bookIdToBookCopiesMap.get(bookId);
    }

    public void updateBookCatalog(Book book){
        bookMap.put(book.getId(), book);
    }

    public Boolean containsBookCopyId(Integer bookCopyId){
        return bookCopyMap.containsKey(bookCopyId);
    }

    public void removeBookCopy(Integer bookCopyId){
        Integer bookId = bookCopyIdToBookIdMap.get(bookCopyId);
        BookCopy bookCopy = bookCopyMap.get(bookCopyId);
        bookCopyMap.remove(bookCopyId);
        bookCopyIdToBookIdMap.remove(bookCopyId);
        bookIdToBookCopiesMap.get(bookId).remove(bookCopy);
    }

    public void updateBookCopyCatalog(Book book, BookCopy bookCopy){
        bookCopyMap.put(bookCopy.getId(), bookCopy);
        bookCopyIdToBookIdMap.put(bookCopy.getId(), book.getId());

        if(!bookIdToBookCopiesMap.containsKey(book.getId())){
            bookIdToBookCopiesMap.put(book.getId(), new ArrayList<>());
        }
        bookIdToBookCopiesMap.get(book.getId()).add(bookCopy);
    }

    public void search(String attribute, String value) {
        // filteredBooks = for all books check attribute value = value
        // print all bookCopies for filteredBooks

        // TODO: How to optimize this?
        // Print Book Copy: <book_copy_id> <book_id> <title> <comma_separated_authors> <comma_separated_publishers> <rack_no> <borrowed_by_id> <due_date>

        // search by book id
        // maintain hash map of bookId -> list of book copy id

        // search by author
        // maintain hash map of author -> list of book ids

        // to get other details for printing
        // maintain a hash map of bookCopyId -> borrow Details

        switch (attribute){
            case "book_id":
                searchByBookId(Integer.valueOf(value));
                break;
            case "author_id":
                searchByAuthor(value);
                break;
            default:
                System.out.println("Invalid attribute");
        }
    }

    private void searchByBookId(Integer bookId){
        Book book = bookMap.get(bookId);

        if(book == null){
            return; // no such book exists in library
        }

        // print in ascending order of rack
        // TODO: Keep it in sorted order instead of sorting it on fly everytime
        Queue<BookSearchResult> searchResults = new PriorityQueue<>();
        for (BookCopy bookCopy:
                bookIdToBookCopiesMap.get(bookId)) {
            searchResults.add(new BookSearchResult(bookCopy.getId(), book, getRackForBook(bookCopy.getId()), getBookBorrowDetails(bookCopy.getId())));
        }

        printSearchResult(searchResults);
    }

    private void searchByAuthor(String author){

        if(author == null){
            return; // no such book exists in library
        }

        // print in ascending order of rack
        Queue<BookSearchResult> searchResults = new PriorityQueue<>();
        for (Book book: bookMap.values()) {
            if(book.getAuthors().contains(author)){
                for (BookCopy bookCopy:
                        bookIdToBookCopiesMap.get(book.getId())) {
                    searchResults.add(new BookSearchResult(bookCopy.getId(), book, getRackForBook(bookCopy.getId()), getBookBorrowDetails(bookCopy.getId())));
                }
            }
        }

        printSearchResult(searchResults);
    }

    private void printSearchResult(Collection<BookSearchResult> searchResults){
        searchResults.stream()
                .forEach(BookSearchResult::print);
    }

    private Rack getRackForBook(Integer bookCopyId){
        return rackService.getRackForBook(bookCopyId);
    }

    private BookBorrowDetails getBookBorrowDetails(Integer bookCopyId){
        return borrowService.getBookBorrowDetailsForBookId(bookCopyId);
    }

    private class BookSearchResult implements Comparable<BookSearchResult>{
        private Integer bookCopyId;
        private Book book;
        private Rack rack;
        private BookBorrowDetails bookBorrowDetails;

        public BookSearchResult(Integer bookCopyId, Book book, Rack rack, BookBorrowDetails bookBorrowDetails) {
            this.bookCopyId = bookCopyId;
            this.book = book;
            this.rack = rack;
            this.bookBorrowDetails = bookBorrowDetails;
        }

        public void print(){
            System.out.print("Book Copy: " + bookCopyId + " " + book.getId() + " " + book.getTitle() + " " +  book.getAuthors() + " " + book.getPublishers() + " " + getRackNumber());
            printBorrowDetails();
            System.out.println();
        }

        public Integer getRackNumber(){
            if(rack != null)
                return rack.getId();
            else
                return -1;
        }



        private void printBorrowDetails(){
            if(bookBorrowDetails != null)
                System.out.print(" " + bookBorrowDetails.getUserId() + " " + bookBorrowDetails.getDueDate());
        }

        @Override
        public int compareTo(BookSearchResult o) {
            return getRackNumber().compareTo(o.getRackNumber());
        }
    }
}
