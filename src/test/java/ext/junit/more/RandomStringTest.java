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
    private static final int NUM_TESTS = 10_000;

    private static final float VARIANCE_FACTOR = 0.5f;

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
        int expectedMin = getMinCount(256);
        int expectedMax = getMaxCount(256);
        for (byte b = 0; b < 256; b++) {
            NumberAssert.assertBetween(expectedMin, counter.count(b), expectedMax);
        }
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
        int expectedMin = getMinCount(52);
        int expectedMax = getMaxCount(52);
        for (int i = 0; i < 26; i++) {
            char c1 = (char)('a' + i);
            NumberAssert.assertBetween(expectedMin, counter.count(c1), expectedMax);

            char c2 = (char)('A' + i);
            NumberAssert.assertBetween(expectedMin, counter.count(c2), expectedMax);
        }
    }

    /**
     * How many keys are expected given the max possible values?
     * @param holeCount number of holes for pigeons
     * @return expectedMin
     */
    private static int getMinCount(int holeCount) {
        int expectedCount = NUM_TESTS / holeCount;
        return (int)((1 - VARIANCE_FACTOR) * expectedCount);
    }

    /**
     * How many keys are expected given the max possible values?
     * @param holeCount number of holes for pigeons
     * @return expectedMax
     */
    private static int getMaxCount(int holeCount) {
        int expectedCount = NUM_TESTS / holeCount;
        return (int)((1 + VARIANCE_FACTOR) * expectedCount);
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
