package service;

import exception.DataTypeException;
import model.Value;
import util.Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeDataServiceImpl implements DataService{

    private Map<String, Value> concurrentStore;
    private Map<String, Class> uniqueAttributeNameToClassMap;

    public ThreadSafeDataServiceImpl() {
        this.concurrentStore = new ConcurrentHashMap<>();
        this.uniqueAttributeNameToClassMap = new ConcurrentHashMap<>();
    }

    @Override
    public Value get(String key) {
        return concurrentStore.getOrDefault(key, null);
    }

    @Override
    public List<String> search(String attributeKey, String attributeValue) {
        if(!uniqueAttributeNameToClassMap.containsKey(attributeKey)){
            return Collections.emptyList();
        }

        List<String> keys = new ArrayList<>();
        for (String key: concurrentStore.keySet()) {
            Value attributes = concurrentStore.get(key);
            for (Map.Entry<String, Object> attribute:
                 attributes.getEntries()) {
                if(attribute.getKey().equals(attributeKey) && attribute.getValue().equals(Helper.parseString(attributeValue))){
                    keys.add(key);
                    break;
                }
            }
        }
        return keys;
    }

    @Override
    public void put(String key, List<Map.Entry<String, String>> listOfAttributePairs) throws Exception {
        Value value = new Value();
        for (Map.Entry<String, String> entry :
                listOfAttributePairs) {
            String attributeKey = entry.getKey();
            Object attributeValue = (entry.getValue());

            System.out.println("attribute : " + attributeKey + " : " + attributeValue + " ( " + attributeValue.getClass() + ")");
            Class existingAttributeKey = uniqueAttributeNameToClassMap.getOrDefault(attributeKey, null);
            if (existingAttributeKey != null && !existingAttributeKey.equals(attributeValue.getClass())) {
                throw new DataTypeException("Data Type Error");
            } else {
                // put the new unique attribute
                uniqueAttributeNameToClassMap.put(attributeKey, attributeValue.getClass());
                value.addEntry(attributeKey, attributeValue);
            }
        }
        concurrentStore.put(key, value);
    }

    @Override
    public void deleteKey(String key) {
        if (concurrentStore.get(key) != null) {
            // TODO: Should we delete attributes from the other map too?
            // If yes, how do we know which unique attributes to delete?
            // Bcz we dont want to delete attributes that were added via another key
            concurrentStore.remove(key);
        }
    }

    @Override
    public Set<String> keys() {
        return concurrentStore.keySet();
    }
}
