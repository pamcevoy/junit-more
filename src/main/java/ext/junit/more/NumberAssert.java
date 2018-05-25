/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import org.junit.Assert;

/**
 * Asserts relatd to numbers.
 * @author Patrick
 */
public class NumberAssert
{
    /**
     * Assert that the actual value is greater than the expected minimum.
     * @param expectedMin the expected minimum
     * @param actual the actual value
     */
    public static void assertGreaterThan(long expectedMin, long actual) {
        if (actual < expectedMin) {
            Assert.fail("Actual '" + actual + "' less than expected minimum '" + expectedMin + "'.");
        }
    }

    /**
     * Assert that the actual value is less than the expected maximum.
     * @param expectedMax the expected minimum
     * @param actual the actual value
     */
    public static void assertLessThan(long expectedMax, long actual) {
        if (actual > expectedMax) {
            Assert.fail("Actual '" + actual + "' greater than expected maximum '" + expectedMax + "'.");
        }
    }

    /**
     * Assert that the value is between these two values (inclusive).
     * @param expectedMin the expected min
     * @param actual the actual value
     * @param expectedMax the expected max
     */
    public static void assertBetween(long expectedMin, long actual, long expectedMax) {
        assertGreaterThan(expectedMin, actual);
        assertLessThan(expectedMax, actual);
    }
}
