/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import org.junit.Assert;

/**
 * JUnit asserts related to {@link String}.
 * @author Patrick
 */
public class StringAssert {
	/** The line separator (/r, /n, or /r/n). */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Private constructor for utility class.
     */
    private StringAssert() {
        // nothing to do
    }

    /**
     * Assert that the actual String contains the expected String.
     * @param expectedSubstring the expected String
     * @param actualString the actual String
     */
    public static void assertContains(String expectedSubstring, String actualString) {
        assertContains(null, expectedSubstring, actualString);
    }

    /**
     * Assert that the actual String contains the expected String.
     * @param message the message for an error
     * @param expectedSubstring the expected String
     * @param actualString the actual String
     */
    public static void assertContains(String message, String expectedSubstring, String actualString) {
        if (!actualString.contains(expectedSubstring)) {
            String header = JUnitMoreUtil.formatHeader(message);
            Assert.fail(formatAssertContainsMessage(header, expectedSubstring, actualString));
        }
    }

    /**
     * Format the error message used by assertContains.
     * @param args the header, expectedSubstring, and actualString
     * @return the error message
     */
    private static String formatAssertContainsMessage(Object... args) {
        return String.format("%sString '%s' is not contained in '%s'.", args);
    }

    /**
     * Assert that the actual String starts with the expected String.
     * @param expectedPrefix the expected start
     * @param actualString the actual String
     */
    public static void assertStartsWith(String expectedPrefix, String actualString) {
        assertStartsWith(null, expectedPrefix, actualString);
    }

    /**
     * Assert that the actual String starts with the expected String.
     * @param message the message for an error
     * @param expectedPrefix the expected start
     * @param actualString the actual String
     */
    public static void assertStartsWith(String message, String expectedPrefix, String actualString) {
        if (expectedPrefix != null) {
            if (actualString == null || !actualString.startsWith(expectedPrefix)) {
                String header = JUnitMoreUtil.formatHeader(message);
                Assert.fail(formatStartsWithMessage(header, actualString, expectedPrefix));
            }
        }
    }

    /**
     * Format the error message used by assertStartsWith.
     * @param args the header, actualString, and expectedPrefix
     * @return the error message
     */
    private static String formatStartsWithMessage(Object... args) {
        return String.format("%sString '%s' does not start with '%s'.", args);
    }

    /**
     * Assert that the actual String ends with the expected String.
     * @param expectedSuffix the expected end
     * @param actualString the actual String
     */
    public static void assertEndsWith(String expectedSuffix, String actualString) {
        assertEndsWith(null, expectedSuffix, actualString);
    }

    /**
     * Assert that the actual String ends with the expected String.
     * @param message the message for an error
     * @param expectedSuffix the expected end
     * @param actualString the actual String
     */
    public static void assertEndsWith(String message, String expectedSuffix, String actualString) {
        if (expectedSuffix != null) {
            if (actualString == null || !actualString.endsWith(expectedSuffix)) {
                String header = JUnitMoreUtil.formatHeader(message);
                Assert.fail(formatEndsWithMessage(header, actualString, expectedSuffix));
            }
        }
    }

    /**
     * Format the error message used by assertEndsWith.
     * @param args the header, actualString, and expectedSuffix.
     * @return the error message
     */
    private static String formatEndsWithMessage(Object... args) {
        return String.format("%sString '%s' does not end with '%s'.", args);
    }

    /**
     * Assert that the values are unique.
     * @param values the values
     */
    public static void assertUnique(String[] values) {
        assertUnique(null, values);
    }
 
    /**
     * Assert that the values are unique.
     * @param message the message for an error
     * @param values the values
     */
    public static void assertUnique(String message, String[] values) {
        String header = JUnitMoreUtil.formatHeader(message);

        int length = (values == null) ? 0 : values.length;
        for (int index1 = 0; index1 < length; index1++) {
            for (int index2 = index1 + 1; index2 < length; index2++) {
                String failMessage = formatUniqueMessage(header, index2, index1);
                Assert.assertNotEquals(failMessage, values[index1], values[index2]);
            }
        }
    }

    /**
     * Format the error message used by assertUnique.
     * @param args the header, index2, and index1
     * @return the error message
     */
    private static String formatUniqueMessage(Object... args) {
        return String.format("%sValue at %d equals value at %d", args);
    }
 
    /**
     * Assert the Strings are equal while ignoring line separators.
     * @param expected the expected String
     * @param actual the actual String
     */
    public static void assertEqualsIgnoreLineSeparator(String expected, String actual) {
    	assertEqualsIgnoreLineSeparator(null, expected, actual);
    }

    /**
     * Assert the Strings are equal while ignoring line separators.
     * @param message the message for an error
     * @param expected the expected String
     * @param actual the actual String
     */
    public static void assertEqualsIgnoreLineSeparator(String message, String expected, String actual) {
        Assert.assertEquals(message, stripLineSeparator(expected), stripLineSeparator(actual));
    }

    /**
     * Strip the line separator out of this String.
     * @param str the String
     * @return the String without any line separators
     */
    private static String stripLineSeparator(String str) {
        return (str == null) ? null : str.replace(LINE_SEPARATOR, "");
    }
}
