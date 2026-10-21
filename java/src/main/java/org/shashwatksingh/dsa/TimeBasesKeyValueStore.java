package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasesKeyValueStore {
    Map<String, List<ValueTimeStamp>> store;

    public TimeBasesKeyValueStore() {
        store = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        ValueTimeStamp val = new ValueTimeStamp(value, timestamp);
        store.computeIfAbsent(key, k -> new ArrayList<>()).add(val);
    }

    public String get(String key, int timestamp) {
        if (!store.containsKey(key)) return "";
        for (ValueTimeStamp val : store.get(key)) {
            if (val.getTimeStamp() <= timestamp) return val.getValue();
        }
        return "";
    }
}

class ValueTimeStamp {
    private String value;
    private int timestamp;

    public ValueTimeStamp(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public int getTimeStamp() {
        return this.timestamp;
    }

    public String getValue() {
        return this.value;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
