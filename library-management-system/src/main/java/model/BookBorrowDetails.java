package model;

import java.util.Date;

public class BookBorrowDetails {
    private final Integer bookCopyId;
    private final Integer userId;
    private final Date dueDate;

    public BookBorrowDetails(Integer bookCopyId, Integer userId, Date dueDate) {
        this.bookCopyId = bookCopyId;
        this.userId = userId;
        this.dueDate = dueDate;
    }

    public Integer getBookCopyId() {
        return bookCopyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
