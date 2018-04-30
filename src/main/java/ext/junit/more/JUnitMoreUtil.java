/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

/**
 * @author Patrick
 *
 */
public class JUnitMoreUtil {
    /**
     * Private constructor for utility class.
     */
    private JUnitMoreUtil() {
        // nothing to do
    }

    /**
     * Format the user error message as a header for the actual error message.
     * @param message the user error message
     * @return the header for the error message
     */
    static String formatHeader(String message) {
        return (message == null) ? "" : message + ": ";
    }
}
