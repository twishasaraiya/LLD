package services;

import exceptions.KeyNotFoundException;
import exceptions.StorageFullException;
import storage.Storage;
import strategy.EvictionStrategy;

public class CacheService<K, V> {

    private final Storage<K, V> storage;
    private final EvictionStrategy<K> evictionStrategy;

    public CacheService(Storage<K, V> storage, EvictionStrategy<K> evictionStrategy) {
        this.storage = storage;
        this.evictionStrategy = evictionStrategy;
    }

    public void put(K key, V value){
        try {
            storage.add(key, value);
            evictionStrategy.keyAccessed(key);
        }
        catch (StorageFullException ex){
            System.out.println("Storage is full, evicting key.");
            K evictedKey = evictionStrategy.evictKey();
            if(evictedKey == null){
                throw new RuntimeException("Storage is full and no key to evict.");
            }
            storage.remove(evictedKey);
            put(key, value);
        }
    }


    public V get(K key){
        try {
            V value = storage.get(key);
            evictionStrategy.keyAccessed(key);
            return value;
        }
        catch (KeyNotFoundException ex){
            System.out.println("Key is not present in Cache.");
        }
        return null;
    }
}
