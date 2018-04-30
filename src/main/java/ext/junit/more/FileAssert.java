/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import java.io.File;

import org.junit.Assert;

/**
 * JUnit asserts related to {@link File}.
 * @author Patrick
 */
public class FileAssert {
    /**
     * Assert that the given file exists.
     * @param file the file
     */
    public static void assertExists(File file) {
        assertExists(null, file);
    }

    /**
     * Assert that the given file exists.
     * @param message the user error message
     * @param file the file
     */
    public static void assertExists(String message, File file) {
        if (!file.exists()) {
            String header = JUnitMoreUtil.formatHeader(message);
            Assert.fail(String.format("%sFile does not exist: %s", header, file));
        }
    }
}
