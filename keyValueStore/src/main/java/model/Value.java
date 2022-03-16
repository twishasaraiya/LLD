package model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Value {
    private List<Map.Entry<String, Object>> entries;

    public List<Map.Entry<String, Object>> getEntries() {
        return entries;
    }

    public Value() {
        entries = new ArrayList<>();
    }

    public Value(List<Map.Entry<String, Object>> entries) {
        this.entries = entries;
    }

    public void addEntry(String key, Object value){
        entries.add(new AbstractMap.SimpleEntry<>(key, value));
    }

    @Override
    public String toString() {
        // TODO: Paste diff between using string buffer and string builder. String buffer is thread safe
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> attributeSet:
                entries) {
            stringBuffer.append(attributeSet.getKey() + " : " + attributeSet.getValue() + ",");
        }
        return stringBuffer.toString();
    }
}
