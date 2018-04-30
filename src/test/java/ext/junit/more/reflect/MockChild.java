/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more.reflect;

/**
 * Helper class for testing accessing private fields and methods.
 * @author Patrick
 */
public class MockChild extends MockParent
{
    private static boolean allowNegative = false;

    private int number;

    public MockChild(String name, int number) {
        super(name);
        validateNumber(number);
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    private void setNumber(Integer number) {
        validateNumber(number);
        this.number = number.intValue();
    }

    private void validateNumber(Integer number) {
        if (number.intValue() < 0 && !allowNegative) {
            throw new IllegalArgumentException("Cannot be negative: " + number.intValue());
        }
    }

    @SuppressWarnings("unused") // called via reflection
    private void setData(String name, Integer number) {
        setName(name);
        setNumber(number);
    }

    public static boolean isNegativeAllowed() {
        return allowNegative;
    }

    public static void setNegativeAllowed(boolean allowNegative) {
        MockChild.allowNegative = allowNegative;
    }
}