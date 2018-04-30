/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more.reflect;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link FieldAccessor}.
 * @author Patrick
 */
public class FieldAccessorTest {

    @Test
    public void testGetFieldValue() {
        MockChild child = new MockChild("Jimmy", 6);
        Assert.assertEquals("Jimmy", FieldAccessor.getValue(child, "name"));
    }

    @Test
    public void testSetFieldValue() {
        MockChild child = new MockChild("Jimmy", 6);
        FieldAccessor.setValue(child, "name", "James");
        Assert.assertEquals("James", child.getName());
    }

    @Test
    public void testGetStaticFieldValue() {
        MockChild.setNegativeAllowed(true);
        Assert.assertTrue((Boolean)FieldAccessor.getValue(MockChild.class, "allowNegative"));
    }

    @Test
    public void testSetStaticFieldValue() {
        MockChild.setNegativeAllowed(true);
        FieldAccessor.setValue(MockChild.class, "allowNegative", Boolean.FALSE);
        Assert.assertFalse(MockChild.isNegativeAllowed());
    }

    @Test
    public void testInvalidField() {
        MockParent parent = new MockParent("Charlie");

        AccessorException caughtException = null;
        try {
            FieldAccessor.getValue(parent, "zzz");
        }
        catch (AccessorException e) {
            caughtException = e;
        }
        Assert.assertNotNull("Should throw NoSuchFieldException", caughtException);
    }
}
