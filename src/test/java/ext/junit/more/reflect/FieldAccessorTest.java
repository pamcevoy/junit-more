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
    /**
     * Able to get a member field.
     */
    @Test
    public void testGetFieldValue() {
        SomeTestSubclass someObject = new SomeTestSubclass("John", 6);
        Assert.assertEquals("John", FieldAccessor.getValue(someObject, "name"));
    }

    /**
     * Able to set member fields.
     */
    @Test
    public void testSetFieldValue() {
        SomeTestSubclass someObject = new SomeTestSubclass("John", 7);

        FieldAccessor.setValue(someObject, "name", "Quincy");
        Assert.assertEquals("Quincy", MethodAccessor.invoke(someObject, "getName"));

        FieldAccessor.setValue(someObject, "number", 8);
        Assert.assertEquals(8, FieldAccessor.getValue(someObject, "number"));
    }

    /**
     * Able to read a static field.
     */
    @Test
    public void testGetStaticFieldValue() {
        SomeTestSubclass.setNegativeAllowed(true);
        Assert.assertTrue((Boolean)FieldAccessor.getValue(SomeTestSubclass.class, "allowNegative"));
    }

    /**
     * Able to set a static field.
     */
    @Test
    public void testSetStaticFieldValue() {
        SomeTestSubclass.setNegativeAllowed(true);
        FieldAccessor.setValue(SomeTestSubclass.class, "allowNegative", Boolean.FALSE);
        Assert.assertFalse(SomeTestSubclass.isNegativeAllowed());
    }

    /**
     * An AccessorException is thrown if the field does not exist.
     */
    @Test
    public void testInvalidField() {
        AccessorException caughtException = null;
        try {
            SomeTestClass parent = new SomeTestClass("Charlie");
            FieldAccessor.getValue(parent, "zzz");
        }
        catch (AccessorException e) {
            caughtException = e;
        }
        Assert.assertNotNull("Should throw AccessorException", caughtException);
        Assert.assertNotNull("Should throw NoSuchFieldException", caughtException.getCause());
        Assert.assertEquals("Invalid field : zzz", caughtException.getCause().getMessage());
    }
}
