package model;

import java.util.Date;

public class BookBorrowDetails {
    private Integer bookCopyId;
    private Integer userId;
    private Date dueDate;

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
