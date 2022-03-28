package service;

import java.util.Date;

public interface BorrowServiceWriter extends BorrowServiceReader {
    Boolean checkIfUserIsAllowedToBorrowBook(Integer userId);
    void addBookCopyToUserBorrowDetails(Integer userId, Integer bookCopyId, Date dueDate);
    void removeBookCopyFromUserBorrowDetails(Integer bookCopyId);
}
