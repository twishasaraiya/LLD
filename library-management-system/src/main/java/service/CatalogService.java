package service;

import model.Book;
import model.BookCopy;

import java.util.List;

public interface CatalogService {
    Boolean containsBookId(Integer bookId);
    List<BookCopy> getAllBookCopiesForBook(Integer bookId);
    void updateBookCatalog(Book book);
    void updateBookCopyCatalog(Book book, BookCopy bookCopy);
    void search(String attribute, String value);
}
