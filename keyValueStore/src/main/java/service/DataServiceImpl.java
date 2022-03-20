package service;

import exception.DataTypeException;
import model.Value;
import util.Helper;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataServiceImpl implements DataService {

    // store consists of KEY -> VALUE
    // VALUE is List<Attributes>
    // Attributes is nothing but a key, value pair
    private Map<String, Value> store;

    // unique attributes map is used to keep track of original class of the attribute
    // this map helps to verify the data type when a same attribute is added to a different key
    // attributeName -> (Original store key name, Expected Class of the attribute value)
    private Map<String, Map.Entry<String, Class>> uniqueAttributeNameToClassMap;

    public DataServiceImpl() {
        this.store = new HashMap<>();
        uniqueAttributeNameToClassMap = new HashMap<>();
    }
    @Override
    public Value get(String key) {
        return store.getOrDefault(key, null);
    }

    @Override
    public List<String> search(String attributeKey, String attributeValue) {
        List<String> keys = new ArrayList<>();

        for (String key: store.keySet()) {
            Value attributes = store.get(key);
            for (Map.Entry<String, Object> attribute:
                 attributes.getEntries().entrySet()) {
//                System.out.println("Checking for " + attribute.getKey() + " = " + attributeKey + " & " + attribute.getValue() + " = " + attributeValue);
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

//            System.out.println("attribute : " + attributeKey + " : " + attributeValue + " ( " + attributeValue.getClass() + ")");
            Map.Entry<String, Class> existingAttribute = uniqueAttributeNameToClassMap.getOrDefault(attributeKey, null);
            if (existingAttribute != null && !existingAttribute.getValue().equals(attributeValue.getClass())) {
                throw new DataTypeException("Data Type Error");
            } else {
                // put the new unique attribute
                uniqueAttributeNameToClassMap.put(attributeKey, new AbstractMap.SimpleEntry<>(key, attributeValue.getClass()));
                value.addEntry(attributeKey, attributeValue);
            }
        }
        store.put(key, value);
    }

    @Override
    public void deleteKey(String key) {
        if (store.get(key) != null) {
            // Question: Should we delete attributes from the other map too?
            // If yes, how do we know which unique attributes to delete?
            // Bcz we dont want to delete attributes that were added via another key
            Value attributes = store.get(key);

            // DELETE only the unique attributes that were added by this key
            // same check is added in the if condition
            for (Map.Entry<String, Object> attribute :
                 attributes.getEntries().entrySet()) {
                if(uniqueAttributeNameToClassMap.containsKey(attribute.getKey()) && uniqueAttributeNameToClassMap.get(attribute.getKey()).getKey().equals(key)){
                    uniqueAttributeNameToClassMap.remove(attribute.getKey());
                }
            }
            store.remove(key);
        }
    }

    @Override
    public Set<String> keys() {
        return store.keySet();
    }
}
