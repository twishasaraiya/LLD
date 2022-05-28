package model;

import java.util.Objects;

public class Request {
    private final String id;

    public Request(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
