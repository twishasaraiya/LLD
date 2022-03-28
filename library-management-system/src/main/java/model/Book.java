package model;

import java.util.List;

public class Book {
    private Integer id;
    private String title;
    private List<String> authors;
    private List<String> publishers;

    // list book copies

    public Book(Integer id, String title, List<String> authors, List<String> publishers) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishers = publishers;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getPublishers() {
        return publishers;
    }
}
