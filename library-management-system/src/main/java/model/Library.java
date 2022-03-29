package model;

import java.util.List;
import java.util.UUID;

/**
 * Library only consists of metadata type information about the library like numOfRacks, name, address
 * The actual book and racks are maintained in different service
 */
public class Library {
    private UUID id;
    private Integer numRacks;

    // list of books
    public Library(Integer numRacks) {
        this.id = UUID.randomUUID();
        this.numRacks = numRacks;
    }

    public UUID getId() {
        return id;
    }

    public Integer getNumRacks() {
        return numRacks;
    }
}
