package model;

import java.util.List;
import java.util.UUID;

public class Library {
    private UUID id;
    private List<Rack> racks;

    // list of books
    public Library(List<Rack> racks) {
        this.id = UUID.randomUUID();
        this.racks = racks;
    }

    public UUID getId() {
        return id;
    }

    public List<Rack> getRacks() {
        return racks;
    }
}
