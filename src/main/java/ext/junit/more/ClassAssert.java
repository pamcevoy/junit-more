/**
 * (c) Copyright 2016 Patrick McEvoy
 */
package ext.junit.more;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit asserts related to {@link Class}.
 * @author Patrick
 */
public class ClassAssert {

    /**
     * Assert that the public String constants in this class have unique values.
     * Constants are the "public static final String" fields.
     * @param cls the class
     */
    public static void assertUniqueStringConstants(Class<?> cls) {
        String[] values = getConstantValues(cls);
        StringAssert.assertUnique(values);
    }

    /**
     * Get the values for the String constants in this class.
     * @param cls the class
     * @return the values for the String constants
     */
    public static String[] getConstantValues(Class<?> cls) {
        Field[] fields = cls.getDeclaredFields();
        List<String> values = new ArrayList<String>();
        for (Field field : fields) {
            if (isPublicStaticFinalString(field)) {
                values.add(getValue(field));
            }
        }
        return values.toArray(new String[values.size()]);
    }

    /**
     * Check if this field is "public static final String".
     * @param field the field
     * @return true if the field is "public static final String"
     */
    private static boolean isPublicStaticFinalString(final Field field) {
        final int mods = field.getModifiers();
        return Modifier.isPublic(mods)
                && Modifier.isStatic(mods)
                && Modifier.isFinal(mods)
                && isString(field);
    }

    /**
     * Returns true if the field is a String.
     * @param field the field
     * @return true if the field is a String
     */
    private static boolean isString(final Field field) {
        return String.class.equals(field.getType());
    }

    /**
     * Get the String value of the field.
     * @param field the field
     * @return the value
     */
    private static String getValue(final Field field) {
        String value = null;
        try {
            Object obj = field.get(null);
            value = obj.toString();
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            value = null;
        }
        return value;
    }
}
