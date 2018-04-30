/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more.reflect;

/**
 * Helper class for testing accessing private fields and methods.
 * @author Patrick
 */
public class MockParent
{
    private String name;

    public MockParent(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String newName) {
        this.name = newName;
    }
}