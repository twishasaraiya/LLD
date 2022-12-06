package storage;

public interface Storage<K, V> {

    void add(K key, V value);

    void remove(K key);

    V get(K key);
}
