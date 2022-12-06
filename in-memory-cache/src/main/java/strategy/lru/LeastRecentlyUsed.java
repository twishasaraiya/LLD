package strategy.lru;


import strategy.EvictionStrategy;

import java.util.HashMap;
import java.util.Map;

public class LeastRecentlyUsed<K> implements EvictionStrategy<K> {

    private static final String DEFAULT_KEY = "default-key";
    /*
        Most Recently Used Key => start.next
        Least Recently Used Key => end.prev
    */
    private Node<K> start;
    private Node<K> end;

    private Map<K, Node<K>> map;

    public LeastRecentlyUsed() {
        start = new Node<>((K) DEFAULT_KEY);
        end =  new Node<>((K) DEFAULT_KEY);
        start.next = end;
        end.prev = start;
        map = new HashMap<>();
    }


    public void keyAccessed(K key) {
        if(!map.containsKey(key)){
            Node<K> newNode = new Node<>(key);
            map.put(key, newNode);
            addNode(map.get(key));
            return;
        }
        Node<K> currNode = map.get(key);
        removeNode(currNode);
        addNode(currNode);
    }

    public K evictKey() {
        if(map.isEmpty()) return null;

        Node<K> toEvict = end.prev;
        removeNode(toEvict);
        map.remove(toEvict.key);
        System.out.println("Evicted Key: " + toEvict.key);
        return toEvict.key;
    }

    private void addNode(Node<K> node){
        node.next = start.next;
        node.prev = start;
        start.next.prev = node;
        start.next = node;
    }

    private void removeNode(Node<K> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = node.next = null;
    }
}
