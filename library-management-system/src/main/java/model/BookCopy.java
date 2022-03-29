package model;


public class BookCopy {
    private final Integer id;
    private final Book book;

    public BookCopy(Integer id, Book bookId) {
        this.id = id;
        this.book = bookId;
    }

    public Integer getId() {
        return id;
    }

    public Book getBookId() {
        return book;
    }
}
