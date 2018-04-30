/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more.reflect;

import java.lang.reflect.Field;

/**
 * A utility to access private fields for use in testing.
 * @author Patrick
 */
public class FieldAccessor {

    /**
     * Get the value of the named static field.
     *
     * @param type the class
     * @param fieldName the name of the field
     * @return the value of the field
     * @throws AccessorException if there is a problem
     */
    public static Object getValue(Class<?> type, String fieldName) {
        try {
            return getField(type, fieldName).get(null);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new AccessorException(e);
        }
    }

    /**
     * Gets the value of the named field and returns it as an object.
     *
     * @param instance the object instance
     * @param fieldName the name of the field
     * @return the value of the field
     * @throws AccessorException if there is a problem
     */
    public static Object getValue(Object instance, String fieldName) {
        try {
            return getField(instance.getClass(), fieldName).get(instance);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new AccessorException(e);
        }
    }

    /**
     * Set the value of the named field on the class.
     *
     * @param type the class
     * @param fieldName the field name
     * @param value the value to set
     * @throws AccessorException if there is a problem
     */
    public static void setValue(Class<?> type, String fieldName, Object value) {
        try {
            getField(type, fieldName).set(null, value);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new AccessorException(e);
        }
    }

    /**
     * Set the value of the named field on the instance.
     *
     * @param instance the instance
     * @param fieldName the field name
     * @param value the value to set
     * @throws AccessorException if there is a problem
     */
    public static void setValue(Object instance, String fieldName, Object value) {
        try {
            getField(instance.getClass(), fieldName).set(instance, value);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new AccessorException(e);
        }
    }

    /**
     * Get the named Field from the given class.
     *
     * @param type the class
     * @param fieldName the field name
     * @return the Field
     * @throws NoSuchFieldException if the field is not found
     */
    private static Field getField(Class<?> type, String fieldName)
            throws NoSuchFieldException {
        if (type == null) {
            throw new NoSuchFieldException("Invalid field : " + fieldName);
        }

        Field field = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);
        }
        catch (NoSuchFieldException e) {
            field = getField(type.getSuperclass(), fieldName); // recurse
        }
        return field;
    }
}
