package model;

import java.util.Objects;

public class Request {
    private String id;
    private long timeStamp;

    public Request(String id, long timeStamp) {
        this.id = id;
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return timeStamp == request.timeStamp && Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeStamp);
    }
}
