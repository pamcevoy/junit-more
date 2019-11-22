/**
 * (c) Copyright 2019 Patrick McEvoy
 */
package ext.junit.more;

import java.util.Random;

/**
 * A utility to generate random strings.
 * @author Patrick
 */
public class RandomString extends Random {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a Random generator.
     */
    public RandomString() {
        super();
    }

    /**
     * Constructs a Random generator for this seed.
     * @param seed the seed
     */
    public RandomString(long seed) {
        super(seed);
    }

    /**
     * Returns the next random byte.
     * Bytes are -128 to 127 but let's use just positive values.
     * @return the next random byte
     */
    public byte nextByte() {
        return (byte)nextInt(128);
    }

    /**
     * Returns the next random letter (a-z or A-Z).
     * @return the next random letter
     */
    public char nextLetter() {
        int i = nextInt(52);
        return (i < 26) ? (char)('a' + i) : (char)('A' + i - 26);
    }

    /**
     * Returns the next random String.
     * @return the next random String
     */
    public String nextString() {
        return nextString(8);
    }

    /**
     * Returns the next random String of this length.
     * @param length the length of the string to generate
     * @return the next random String of this length
     */
    public String nextString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(nextLetter());
        }
        return sb.toString();
    }
}
