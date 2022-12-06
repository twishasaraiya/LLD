package storage;

import exceptions.KeyNotFoundException;
import exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<K, V> implements Storage<K, V> {

    private final Map<K, V> storage;
    private final Integer capacity;

    public HashMapStorage(Integer capacity) {
        this.capacity = capacity;
        this.storage = new HashMap<K, V>();
    }


    public void add(K key, V value) {
        if(isFull()) throw new StorageFullException();
        storage.put(key, value);
    }

    public void remove(K key) {
        if(!storage.containsKey(key)) throw new KeyNotFoundException();
        storage.remove(key);
    }

    public V get(K key) {
        if(!storage.containsKey(key)) throw new KeyNotFoundException();
        return storage.get(key);
    }


    private boolean isFull(){
        return storage.size() == capacity;
    }
}
