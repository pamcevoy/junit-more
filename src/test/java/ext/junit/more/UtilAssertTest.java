/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link UtilAssert}.
 * @author Patrick
 */
public class UtilAssertTest {

    private List<String> expectedList;

    private List<String> actualList;

    @Before
    public void setUp() {
        expectedList = Arrays.asList("Alpha", "Beta", "Gamma");
        actualList = new ArrayList<String>(expectedList); // same
    }

    /**
     * Compare identical lists should pass.
     */
    @Test
    public void testMatchingLists1() {
        UtilAssert.assertListEquals(expectedList, actualList);
    }

    /**
     * Compare identical lists should pass.
     */
    @Test
    public void testMatchingLists2() {
        UtilAssert.assertListEquals("Identical", expectedList, actualList);
    }

    /**
     * Compare lists of different sizes should fail.
     */
    @Test
    public void testDifferentListSizes1() {
        actualList.add("Delta"); // different size
        try {
            UtilAssert.assertListEquals(expectedList, actualList);
        }
        catch (AssertionError e) {
            String expectedMessage = "The sizes are different, expected:<3> but was:<4>";
            Assert.assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Compare lists of different sizes should fail.
     */
    @Test
    public void testDifferentListSizes2() {
        try {
            UtilAssert.assertListEquals("Different sizes", expectedList, actualList);
        }
        catch (AssertionError e) {
            String expectedMessage = "Different sizes: The sizes are different, expected:<3> but was:<4>";
            Assert.assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Compare lists of same size, but with a difference should fail.
     */
    @Test
    public void testDifferentListElements1() {
        try {
            actualList.set(2, "Delta"); // different value
            UtilAssert.assertListEquals(expectedList, actualList);
        }
        catch (AssertionError e) {
            String expectedMessage = "Different element at 2, expected:<[Gamm]a> but was:<[Delt]a>";
            Assert.assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Compare lists of same size, but with a difference should fail.
     */
    @Test
    public void testDifferentListElements2() {
        try {
            actualList.set(2, "Delta"); // different value
            UtilAssert.assertListEquals("Different elements", expectedList, actualList);
        }
        catch (AssertionError e) {
            String expectedMessage = "Different elements: Different element at 2, expected:<[Gamm]a> but was:<[Delt]a>";
            Assert.assertEquals(expectedMessage, e.getMessage());
        }
    }
}
