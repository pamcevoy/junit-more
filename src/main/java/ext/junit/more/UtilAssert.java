/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import java.util.Enumeration;
import java.util.List;

import org.junit.Assert;

/**
 * JUnit asserts related to java.util collections.
 * @author Patrick
 */
public class UtilAssert {
    /**
     * Assert that these Lists are equal.
     * @param expected the expected values
     * @param actual the actual values
     */
    public static void assertListEquals(final List<?> expected, final List<?> actual) {
        assertListEquals(null, expected, actual);
    }

    /**
     * Assert that these Lists are equal.
     * @param message the message if not equal
     * @param expected the expected values
     * @param actual the actual values
     */
    public static void assertListEquals(final String message, final List<?> expected, final List<?> actual) {
        String header = JUnitMoreUtil.formatHeader(message);

        // quick check of sizes
        String failMessage = String.format("%sThe sizes are different,", header);
        Assert.assertEquals(failMessage, expected.size(), actual.size());

        // check each element
        for (int index = 0; index < expected.size(); index++) {
            failMessage = String.format("%sDifferent element at %d,", header, index);
            Assert.assertEquals(failMessage, expected.get(index), actual.get(index));
        }
    }

    /**
     * Assert that these Enumerations are equal.
     * @param expected the expected Enumeration
     * @param actual the actual Enumeration
     */
    public static <T> void assertEnumerationEquals(Enumeration<T> expected, Enumeration<T> actual) {
    	assertEnumerationEquals(null, expected, actual);
    }

    /**
     * Assert that these Enumerations are equal.
     * @param message the message if not equal
     * @param expected the expected Enumeration
     * @param actual the actual Enumeration
     */
    public static <T> void assertEnumerationEquals(final String message, final Enumeration<T> expected, final Enumeration<T> actual) {
        String header = JUnitMoreUtil.formatHeader(message);

        int index = 0;
        while (expected.hasMoreElements() && actual.hasMoreElements()) {
            T expectedObj = expected.nextElement();
            T actualObj = actual.nextElement();
            if (!expectedObj.equals(actualObj)) {
            	String messageFmt = "%sDifferent values '%s' and '%s' at %d.";
            	Assert.fail(String.format(messageFmt, header, expectedObj, actualObj, index));
            }
            index++;
        }

        Assert.assertFalse("Missing some values", expected.hasMoreElements());
        Assert.assertFalse("Too many values", actual.hasMoreElements());
    }

    /**
     * Assert that this list contains this item.
     *
     * @param <T> the type of the expected item and the list (e.g. String)
     * @param expected the item
     * @param actual the list
     */
    public static <T> void assertContains(T expected, List<T> actual) {
        if (!actual.contains(expected)) {
            String messageFmt = "Expected object '%s' not contained in list '%s'.";
            Assert.fail(String.format(messageFmt, expected, actual));
        }
    }
}
