package model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Value {
    private Map<String, Object> entries;

    public Map<String, Object> getEntries() {
        return entries;
    }

    public Value() {
        entries = new ConcurrentHashMap<>();
    }

    public Value(Map<String, Object> entries) {
        this.entries = entries;
    }

    public void addEntry(String key, Object value){
        entries.put(key, value);
    }

    @Override
    public String toString() {
        // TODO: Paste diff between using string buffer and string builder. String buffer is thread safe
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> attributeSet:
                entries.entrySet()) {
            stringBuffer.append(attributeSet.getKey() + " : " + attributeSet.getValue() + ",");
        }
        return stringBuffer.toString();
    }
}
