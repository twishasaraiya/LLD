package service;

import model.Rack;

public interface RackService {
    Rack newRack(Integer id);
    Boolean isBookAvailableOnRack(Integer bookCopyId);
    Rack getRackForBook(Integer bookCopyId);
    Boolean isRackAvailableForBooks(Integer numBooks);
    Rack assignBookCopyToRack(Integer bookCopyId);
    Rack removeBookCopyFromRack(Integer bookCopyId);
}
