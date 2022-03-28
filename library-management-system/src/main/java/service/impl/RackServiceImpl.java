package service.impl;

import model.Rack;
import service.RackService;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class RackServiceImpl implements RackService {
    private static Queue<Rack> emptyRackQueue;
    private static Map<Integer, Rack> bookCopyIdToRackMap;

    public RackServiceImpl() {
        // sorted by number in ascending order
        this.emptyRackQueue = new PriorityQueue<>();
        this.bookCopyIdToRackMap = new HashMap<>();

    }

    public Boolean isRackAvailableForBooks(Integer numBooks){
        return numBooks <= emptyRackQueue.size();
    }

    public Boolean isBookAvailableOnRack(Integer bookCopyId){
        return bookCopyIdToRackMap.containsKey(bookCopyId);
    }

    public Rack getRackForBook(Integer bookCopyId){
        if(isBookAvailableOnRack(bookCopyId)){
            return bookCopyIdToRackMap.get(bookCopyId);
        }
        return null;
    }

    public Rack newRack(Integer id){
        Rack rack = new Rack(id);
        emptyRackQueue.add(rack);
        return rack;
    }

    public Rack assignBookCopyToRack(Integer bookCopyId){
        Rack rack = emptyRackQueue.poll();
        rack.setBookCopyId(bookCopyId);
        bookCopyIdToRackMap.put(bookCopyId, rack);
        return rack;
    }


    public Rack removeBookCopyFromRack(Integer bookCopyId){
        Rack rack = bookCopyIdToRackMap.get(bookCopyId);
        rack.setBookCopyId(null);
        emptyRackQueue.add(rack);
        bookCopyIdToRackMap.remove(bookCopyId);
        return rack;
    }
}
