/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more.reflect;

/**
 * Helper class for testing accessing private fields and methods.
 * @author Patrick
 */
public class SomeTestClass {
    // name
    private String name;

    /**
     * Constructor.
     * @param name the name
     */
    public SomeTestClass(String name) {
        this.name = name;
    }

    /**
     * Get the name.
     * @return the name
     */
    protected String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * @param name the name
     */
    protected void setName(String name) {
        this.name = name;
    }
}
