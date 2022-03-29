package service.impl;

import model.Rack;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * SINGLETON since all the properties are static
 */
public class RackServiceImpl{
    private static Queue<Rack> emptyRackQueue;
    // using tree since we want to borrow book copy from the closest rack
    private static Map<Integer, Rack> bookCopyIdToRackMap;

    private static RackServiceImpl rackService = null;

    private RackServiceImpl() {
        // sorted by number in ascending order
        this.emptyRackQueue = new PriorityQueue<>();
        this.bookCopyIdToRackMap = new HashMap<>();

    }

    public static RackServiceImpl getRackService(){
        if(rackService == null){
            return new RackServiceImpl();
        }
        return rackService;
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

    public Optional<Rack> getClosestBookCopy(List<Integer> bookCopyIds){
        return bookCopyIds
                .stream()
                .map(bookCopyId -> bookCopyIdToRackMap.getOrDefault(bookCopyId,null))
                .filter(Objects::nonNull)
                .sorted(new Comparator<Rack>() {
                    @Override
                    public int compare(Rack o1, Rack o2) {
                        return o1.compareTo(o2);
                    }
                })
                .findFirst();
    }
}
