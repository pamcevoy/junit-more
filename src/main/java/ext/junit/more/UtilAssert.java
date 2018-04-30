/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

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
        String header = message == null ? "" : message + ": ";

        // quick check of sizes
        String failMessage = String.format("%sThe sizes are different,", header);
        Assert.assertEquals(failMessage, expected.size(), actual.size());

        // check each element
        for (int index = 0; index < expected.size(); index++) {
            failMessage = String.format("%sDifferent element at %d,", header, index);
            Assert.assertEquals(failMessage, expected.get(index), actual.get(index));
        }
    }
}
