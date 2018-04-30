/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link ClassAssert}.
 * @author Patrick
 */
public class ClassAssertTest {

    @Test
    public void testUniqueConstants() {
        ClassAssert.assertUniqueStringConstants(UniqueConstantsExample.class);
    }

    @SuppressWarnings("unused") // constants used for testing
    private static class UniqueConstantsExample
    {
        // unique public constants
        public static final String ALPHA = "alpha";
        public static final String BETA  = "beta";
        public static final String GAMMA = "gamma";

        // non-unique but acceptable for reasons
        private static final String ALPHA1 = "alpha"; // non-public
        public final String ALPHA2 = "alpha";         // non-static
        public static String ALPHA3 = "alpha";        // non-final
        public static final StringBuilder ALPHA4 = new StringBuilder("alpha"); // non-String
    }

    @Test
    public void testNonUniqueConstants() {
        AssertionError caught = null;
        try {
            ClassAssert.assertUniqueStringConstants(NonUniqueConstantsExample.class);
        }
        catch (AssertionError e) {
            caught = e;
        }
        String message = "Value at 3 equals value at 1. Actual: beta";
        Assert.assertEquals(message, caught.getMessage());
    }

    @SuppressWarnings("unused") // constants used for testing
    private static class NonUniqueConstantsExample
    {
        // unique public constants
        public static final String ALPHA = "alpha";
        public static final String BETA  = "beta";
        public static final String GAMMA = "gamma";

        // non-unique and not acceptable
        public static final String BETA2 = "beta";
    }
}
