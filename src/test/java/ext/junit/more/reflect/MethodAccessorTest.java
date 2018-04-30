/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more.reflect;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link MethodAccessor}.
 * <p>
 * I found this years ago but the project disappeared from sourceforge/googlecode,
 * so I added it here and made modifications.  Originally by Charlie Hubbard and
 * Prashant Dhokte.
 *
 * @author Patrick McEvoy
 */
public class MethodAccessorTest {
    /**
     * Pass in some objects and get back their types.
     */
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

    /**
     * Pass in a null object and get back null type.
     */
    @Test
    public void testGetArgTypesNullEntry() {
        Class<?>[] expectedTypes = new Class<?>[] {
            StringBuilder.class,
            null,
            Integer.class,
        };
        Object[] args = {
            new StringBuilder("SomeString"),
            null,
            Integer.valueOf(123),
        };
        Assert.assertArrayEquals(expectedTypes, getArgTypes(new Object[] { args }));
    }

    /**
     * Pass in empty array and get back empty array.
     */
    @Test
    public void testGetArgTypesEmptyArray() {
        Class<?>[] expectedTypes = new Class<?>[] {}; // empty array
        Object[] args = new Object[] {}; // empty array
        Assert.assertArrayEquals(expectedTypes, getArgTypes(new Object[] { args }));
    }

    /**
     * Pass in null instead of array and get back empty array.
     * Why not get back null?  An empty array is usually safer.
     */
    @Test
    public void testGetArgTypesNullArray() {
        Class<?>[] expectedTypes = new Class<?>[] {}; // empty array
        Object[] args = null; // null
        Assert.assertArrayEquals(expectedTypes, getArgTypes(new Object[] { args }));
    }

    /**
     * A helper to call the real getArgTypes method.
     * @param args the arguments for which to get types
     * @return the types of the arguments
     */
    private static Class<?>[] getArgTypes(Object[] args) {
        Class<?>[] argTypes = new Class<?>[] { Object[].class };
        return (Class<?>[])MethodAccessor.invoke(MethodAccessor.class, "getArgTypes", args, argTypes);
    }

    /**
     * Call a private method with no args.
     */
    @Test
    public void testGetNumber() {
        SomeTestSubclass someObject = new SomeTestSubclass("John", 29);
        Assert.assertEquals(29, MethodAccessor.invoke(someObject, "getNumber"));
    }

    /**
     * Invoke a private method.
     */
    @Test
    public void testSetName() {
        SomeTestClass someObject = new SomeTestClass("John");
        Assert.assertEquals(FieldAccessor.getValue(someObject, "name"), "John");

        // update value and verify
        MethodAccessor.invoke(someObject, "setName", "Abigail");
        Assert.assertEquals(FieldAccessor.getValue(someObject, "name"), "Abigail");
    }

    /**
     * Invoke private methods in sub-class and parent class.
     */
    @Test
    public void testSetNameAndNumber() {
        SomeTestSubclass someObject = new SomeTestSubclass("John", 30);
        Assert.assertEquals(FieldAccessor.getValue(someObject, "name"), "John");
        Assert.assertEquals(FieldAccessor.getValue(someObject, "number"), 30);

        // update values and verify
        MethodAccessor.invoke(someObject, "setName", "Abigail");
        MethodAccessor.invoke(someObject, "setNumber", Integer.valueOf(20));
        Assert.assertEquals(FieldAccessor.getValue(someObject,"name"), "Abigail");
        Assert.assertEquals(FieldAccessor.getValue(someObject, "number"), 20);
    }

    /**
     * Invoke a multi-argument method.
     */
    @Test
    public void testChildWithParentReference() {
        SomeTestClass someObject = new SomeTestSubclass("John", 31);
        Assert.assertEquals(FieldAccessor.getValue(someObject, "name"), "John");
        Assert.assertEquals(FieldAccessor.getValue(someObject, "number"), 31);

        // update values and verify
        Object args[] = { "Abigail", 21 };
        MethodAccessor.invoke(someObject, "setData", args);
        Assert.assertEquals(FieldAccessor.getValue(someObject,"name"), "Abigail");
        Assert.assertEquals(FieldAccessor.getValue(someObject, "number"), 21);
    }

    /**
     * Calling a method that does not exist throws an AccessorException.
     */
    @Test
    public void testInvalidMethodName() {
        AccessorException caughtException = null;
        try {
            SomeTestSubclass someObject = new SomeTestSubclass("John", 32);
            MethodAccessor.invoke(someObject, "zzz", "Quincy");
        }
        catch (AccessorException e) {
            caughtException = e;
        }
        Assert.assertNotNull("Should throw AccessorException", caughtException);
        Assert.assertNotNull("Should wrap NoSuchMethodException", caughtException.getCause());
        Assert.assertEquals("Invalid method : zzz(java.lang.String)", caughtException.getCause().getMessage());
    }

    /**
     * Calling a method with invalid argument throws an AccessorException.
     */
    @Test
    public void testInvalidArguments() {
        AccessorException caughtException = null;
        try {
            SomeTestSubclass someObject = new SomeTestSubclass("John", 33);
            MethodAccessor.invoke(someObject, "setData", "Quincy"); // should be { name, number }
        }
        catch (AccessorException e) {
            caughtException = e;
        }
        Assert.assertNotNull("Should throw AccessorException", caughtException);
        Assert.assertNotNull("Should wrap NoSuchMethodException", caughtException.getCause());
        Assert.assertEquals("Invalid method : setData(java.lang.String)", caughtException.getCause().getMessage());
    }
}
