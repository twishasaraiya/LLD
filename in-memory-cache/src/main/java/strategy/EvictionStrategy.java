package strategy;

public interface EvictionStrategy<K> {

    void keyAccessed(K key);

    K evictKey();
}
