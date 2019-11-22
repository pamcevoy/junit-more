/**
 * (c) Copyright 2019 Patrick McEvoy
 */
package ext.junit.more;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Count the items.
 * @author Patrick
 */
public class Counter {

    private Map<Object,ObjectCount> countMap;

    public Counter() {
        countMap = new HashMap<Object,ObjectCount>();
    }

    /**
     * Clear all the entries in the counter.
     */
    public void clear() {
        countMap.clear();
    }

    /**
     * Get the number of keys in the counter.
     * @return the number of keys
     */
    public int size() {
        return countMap.size();
    }

    /**
     * Get the keys in the counter.
     * @return the keys
     */
    public Set<Object> keySet() {
        return countMap.keySet();
    }

    /**
     * Increment the count for this object.
     * @param value the object
     */
    public void increment(Object value) {
        get(value).count++;
    }

    /**
     * Decrement the count for this object.
     * @param value the object
     */
    public void decrement(Object value) {
        get(value).count--;
    }

    /**
     * Returns the count for this object.
     * @param value the object
     * @return the count for this object
     */
    public int count(Object value) {
        return get(value).count;
    }

    /**
     * Get the ObjectCount for this key.
     * @param key the key
     * @return the ObjectCount for this key
     */
    private ObjectCount get(Object key) {
        ObjectCount objCount = countMap.get(key);
        if (objCount == null) {
            objCount = new ObjectCount(key);
            countMap.put(key, objCount);
        }
        return objCount;
    }

    /**
     * Returns the object with the lowest count.
     * @return the object with the lowest count
     */
    public Object min() {
        ObjectCount min = null;
        for (ObjectCount objCount : countMap.values()) {
            if (min == null || objCount.count < min.count) {
                min = objCount;
            }
        }
        return min.value;
    }

    /**
     * Returns the object with the greatest count.
     * @return the object with the greatest count
     */
    public Object max() {
        ObjectCount max = null;
        for (ObjectCount objCount : countMap.values()) {
            if (max == null || objCount.count > max.count) {
                max = objCount;
            }
        }
        return max.value;
    }

    /**
     * A object that holds an object and its count.
     * @author Patrick
     */
    private static class ObjectCount
    {
        /** The object. */
        private final Object value;

        /** The count. */
        private int count = 0;

        /**
         * Constructs an ObjectCount for this object.
         * @param value the object
         */
        public ObjectCount(Object value) {
            this.value = value;
        }
    }
}
