package service;

import model.Value;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DataService {
    Value get(String key);
    List<String> search(String attributeKey, String attributeValue);
    // PAIR class is no longer available in Java
    void put(String key, List<Map .Entry<String, String>> listOfAttributePairs) throws Exception;
    void deleteKey(String key);
    Set<String> keys();
}
