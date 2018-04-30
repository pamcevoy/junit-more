/**
 * (c) Copyright 2018 Patrick McEvoy
 * Well ISS.net or Charlie and Prashant probably have copyright.
 */
package ext.junit.more.reflect;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link MethodAccessor}.
 * <p>
 * Same thing - I found this years ago but the project disappeared from
 * sourceforge/googlecode, so I added it here and made modifications.
 *
 * @author Charlie Hubbard (chubbard@iss.net)
 * @author Prashant Dhokte (pdhokte@iss.net)
 */
public class MethodAccessorTest {
    @Test
    public void testGetArgTypes() {
        Class<?>[] expectedTypes = {
            String.class,
            Integer.class,
            Object.class,
        };
        Object[] args = {
            "String",
            Integer.valueOf(123),
            new Object(),
        };
        Assert.assertArrayEquals(expectedTypes, getArgTypes(new Object[] { args }));
    }

    @Test
    public void testGetArgTypesNullEntry() {
        Class<?>[] expectedTypes = new Class<?>[] {
            String.class,
            null,
            Integer.class,
        };
        Object[] args = {
            "String",
            null,
            Integer.valueOf(123),
        };
        Assert.assertArrayEquals(expectedTypes, getArgTypes(new Object[] { args }));
    }

    @Test
    public void testGetArgTypesNullArray() {
        Class<?>[] expectedTypes = new Class<?>[] {};
        Object[] args = null;
        Assert.assertArrayEquals(expectedTypes, getArgTypes(new Object[] { args }));
    }

    // helper
    private static Class<?>[] getArgTypes(Object[] args) {
        Class<?>[] argTypes = new Class<?>[] { Object[].class };
        return (Class<?>[])MethodAccessor.invoke(MethodAccessor.class, "getArgTypes", args, argTypes);
    }

    @Test
    public void testParent() {
        // create an object
        MockParent parent = new MockParent("Charlie");
        Assert.assertEquals(FieldAccessor.getValue(parent, "name"), "Charlie");

        // setting a field
        MethodAccessor.invoke(parent, "setName", "Timmah!");
        Assert.assertEquals(FieldAccessor.getValue(parent,"name"), "Timmah!");
    }

    @Test
    public void testChild() {
        // create objects
        MockChild child = new MockChild("Charlie", 8);
        Assert.assertEquals(FieldAccessor.getValue(child, "name"), "Charlie");
        Assert.assertEquals(FieldAccessor.getValue(child, "number"), Integer.valueOf(8));

        // set fields
        MethodAccessor.invoke(child, "setName", "Timmah!");
        MethodAccessor.invoke(child, "setNumber", Integer.valueOf(3));

        Assert.assertEquals(FieldAccessor.getValue(child,"name"), "Timmah!");
        Assert.assertEquals(FieldAccessor.getValue(child, "number"), Integer.valueOf(3));
    }

    @Test
    public void testChildWithParentReference() {
        MockParent parent = new MockChild("Charlie", 8);
        Assert.assertEquals(FieldAccessor.getValue(parent, "name"), "Charlie");
        Assert.assertEquals(FieldAccessor.getValue(parent, "number"), Integer.valueOf(8));

        Object args[] = { "Timmah!", Integer.valueOf(3) };
        MethodAccessor.invoke(parent, "setData", args);

        Assert.assertEquals(FieldAccessor.getValue(parent,"name"), "Timmah!");
        Assert.assertEquals(FieldAccessor.getValue(parent, "number"), Integer.valueOf(3));

        MethodAccessor.invoke(parent, "setName", "prashant");
        Assert.assertEquals(FieldAccessor.getValue(parent,"name"), "prashant");
    }

    @Test
    public void testInvalidMethodName() {
        MockChild child = new MockChild("Charlie", 8);

        AccessorException caughtException = null;
        try {
            MethodAccessor.invoke(child, "zzz", "Timmah!");
        }
        catch (AccessorException e) {
            caughtException = e;
        }
        Assert.assertNotNull("Should throw NoSuchMethodException", caughtException);
    }

    @Test
    public void testInvalidArguments() {
        MockChild child = new MockChild("Charlie", 8);

        AccessorException caughtException = null;
        try {
            MethodAccessor.invoke(child, "setData", "Timmah!");
        }
        catch (AccessorException e) {
            caughtException = e;
        }
        Assert.assertNotNull("Should throw NoSuchMethodException", caughtException);
    }
}
