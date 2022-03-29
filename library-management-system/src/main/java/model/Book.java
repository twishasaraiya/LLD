package model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Book {
    private final Integer id;
    private final String title;
    private final List<String> authors;
    private final List<String> publishers;

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
