/**
 * (c) Copyright 2019 Patrick McEvoy
 */
package ext.junit.more;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link RandomString}.
 * @author Patrick
 */
public class RandomStringTest {
    private static final int NUM_TESTS = 10000;

    private final RandomString random = new RandomString();

    private Counter counter;

    @Before
    public void setUp() {
        counter = new Counter();
    }

    @Test
    public void testConstructor() {
        final long seed = 1234L;
        RandomString random1 = new RandomString(seed);
        RandomString random2 = new RandomString(seed);
        for (int i = 0; i < NUM_TESTS; i++) {
            assertEquals(random1.nextInt(), random2.nextInt());
        }
    }

    /**
     * Test getting a byte.
     */
    @Test
    public void testNextByte() {
        for (int i = 0; i < NUM_TESTS; i++) {
            byte b = random.nextByte();
            counter.increment(Byte.valueOf(b));
        }

        // bytes are 8 bits so there should be 2^8 = 256 keys
        NumberAssert.assertGreaterThan(howMany(256), counter.size());
    }

    /**
     * Test getting a letter.
     */
    @Test
    public void testNextLetter() {
        for (int i = 0; i < NUM_TESTS; i++) {
            char c = random.nextLetter();
            counter.increment(Character.valueOf(c));
        }

        // nextChar generates a-z and A-Z so 52 possible values
        NumberAssert.assertGreaterThan(howMany(52), counter.size());
    }

    /**
     * How many keys are expected given the max possible values?
     * @param maxValuesForType max value count
     * @return expectedMin
     */
    private static int howMany(int maxValuesForType) {
        // We expect the value to be evenly distributed over the space
        // but if the space is greater than the number of tests,
        // then take the min of space or number of tests.
        // Then scale a little bit because it won't be totally evenly distributed.
        return (int)(0.9 * Math.min(maxValuesForType, NUM_TESTS));
    }

    /**
     * Test getting a string.
     */
    @Test
    public void testNextString() {
        // default is of length 8
        assertEquals(8, random.nextString().length());

        // these are of a requested length
        for (int i = 0; i < NUM_TESTS; i++) {
            assertEquals(i, random.nextString(i).length());
        }
    }
}
