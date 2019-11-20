/**
 * (c) Copyright 2019 Patrick McEvoy
 */
package ext.junit.more;

/**
 * Count the items.
 * @author Patrick
 */
public class Counter {

    private int count;

    public void increment(Object obj) {
        count++;
    }

    public int size() {
        return count;
    }
}
