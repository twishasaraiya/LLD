package service;

import java.util.Date;
import java.util.List;

public interface LibraryManagementService {
    void createLibrary(Integer numOfRacks);
    void addBook(Integer bookId, String bookTitle, List<String> authors, List<String> publishers, List<Integer> bookCopyIds);
    void removeBookCopy(Integer bookCopyId);
    void borrowBook(Integer bookId, Integer userId, Date dueDate);
    void borrowBookCopy(Integer bookCopyId, Integer userId, Date dueDate);
    void returnBookCopy(Integer bookCopyId);
    void printBorrowed(Integer userId);
    void search(String attribute, String value);
}
