/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more.reflect;

/**
 * Helper class for testing accessing private fields and methods.
 * @author Patrick
 */
public class SomeTestSubclass extends SomeTestClass {
    // flag to allow or disallow negative numbers
    private static boolean allowNegative = false;

    // number
    private int number;

    /**
     * Constructor.
     * @param name the name
     * @param number the number
     */
    public SomeTestSubclass(String name, int number) {
        super(name);
        validateNumber(number);
        this.number = number;
    }

    /**
     * Get the number.
     * @return the number
     */
    @SuppressWarnings("unused") // called via reflection
    private int getNumber() {
        return this.number;
    }

    /**
     * Set the number.
     * @param number the number
     */
    private void setNumber(Integer number) {
        validateNumber(number);
        this.number = number.intValue();
    }

    /**
     * Validate the number.
     * @param number the number
     */
    private void validateNumber(Integer number) {
        if (number.intValue() < 0 && !allowNegative) {
            throw new IllegalArgumentException("Cannot be negative: " + number.intValue());
        }
    }

    /**
     * Set both name and number.
     * @param name the name
     * @param number the number
     */
    @SuppressWarnings("unused") // called via reflection
    private void setData(String name, Integer number) {
        setName(name);
        setNumber(number);
    }

    /**
     * Check if negative numbers are allowed.
     * @return true if negatives are allowed
     */
    public static boolean isNegativeAllowed() {
        return allowNegative;
    }

    /**
     * Set the flag to control if negative numbers are allowed.
     * @param allowNegative true to allow negative numbers
     */
    public static void setNegativeAllowed(boolean allowNegative) {
        SomeTestSubclass.allowNegative = allowNegative;
    }
}
