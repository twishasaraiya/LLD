package service;

import model.BookBorrowDetails;

public interface BorrowServiceReader {
    BookBorrowDetails getBookBorrowDetailsForBookId(Integer bookCopyId);
    void printBorrowDetails(Integer userId);
}
