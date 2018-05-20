/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

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
        expectedList = Arrays.asList("Alpha", "Beta", "Gamma", "Delta");
        actualList = new ArrayList<String>(expectedList); // same
    }

    //
    // assertListEquals
    //

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
        actualList.add("Epsilon"); // different size
        String expectedMessage = "The sizes are different, expected:<4> but was:<5>";
        String header = null;
        testListEquals(expectedMessage, header, expectedList, actualList);
    }

    /**
     * Compare lists of different sizes should fail.
     */
    @Test
    public void testDifferentListSizes2() {
    	actualList.add("Epsilon"); // extra value => different size
        String expectedMessage = "Different sizes: The sizes are different, expected:<4> but was:<5>";
        String header = "Different sizes";
        testListEquals(expectedMessage, header, expectedList, actualList);
    }

    /**
     * Compare lists of same size, but with a difference should fail.
     */
    @Test
    public void testDifferentListElements1() {
        actualList.set(2, "Epsilon"); // different value
        String expectedMessage = "Different element at 2, expected:<[Gamma]> but was:<[Epsilon]>";
        String header = null;
        testListEquals(expectedMessage, header, expectedList, actualList);
    }

    /**
     * Compare lists of same size, but with a difference should fail.
     */
    @Test
    public void testDifferentListElements2() {
        actualList.set(2, "Epsilon"); // different value
        String expectedMessage = "Different elements: Different element at 2, expected:<[Gamma]> but was:<[Epsilon]>";
        String header = "Different elements";
        testListEquals(expectedMessage, header, expectedList, actualList);
    }

    /**
     * Test that these two items are equal and catch the AssertionError
     * when they are not equal.
     * @param expectedMessage the expected message
     * @param header the header to pass to assertEquals
     * @param expected the expected values
     * @param actual the actual values
     */
    private static void testListEquals(String expectedMessage, String header, List<String> expected, List<String> actual) {
    	AssertionError caught = null;
        try {
            UtilAssert.assertListEquals(header, expected, actual);
        }
        catch (AssertionError e) {
        	caught = e;
        }
        Assert.assertEquals(expectedMessage, caught.getMessage());
    }

    //
    // assertEnumerationEquals
    //

    @Test
    public void testEnumerationEqualsSame() {
    	Enumeration<String> actualEnum = toEnumeration(actualList);
    	Enumeration<String> expectedEnum = toEnumeration(expectedList);
    	UtilAssert.assertEnumerationEquals(expectedEnum, actualEnum);
    }

    @Test
    public void testEnumerationEquals1() {
    	actualList.set(actualList.size() - 1, "Epsilon");
    	String expectedMessage = "Different values 'Delta' and 'Epsilon' at 3.";
    	String header = null;
    	testEnumerationEquals(expectedMessage, header, expectedList, actualList);
    }

    @Test
    public void testEnumerationEquals2() {
    	actualList.add("Epislon");
    	String header = null;
    	testEnumerationEquals("Too many values", header, expectedList, actualList);
    }

    @Test
    public void testEnumerationEquals3() {
    	actualList.remove(actualList.size() - 1);
    	String header = null;
    	testEnumerationEquals("Missing some values", header, expectedList, actualList);
    }

    /**
     * Test that these items are equal and catch the AssertionError when they
     * are not equal.
     * @param expectedMessage the expected message
     * @param header the message to pass to assertEquals
     * @param expected the expected values
     * @param actual the actual values
     */
    private static void testEnumerationEquals(String expectedMessage, String header, List<String> expected, List<String> actual) {
    	AssertionError caught = null;
    	try {
        	Enumeration<String> actualEnum = toEnumeration(actual);
        	Enumeration<String> expectedEnum = toEnumeration(expected);
    		UtilAssert.assertEnumerationEquals(header, expectedEnum, actualEnum);
    	}
    	catch (AssertionError e) {
    		caught = e;
    	}
    	Assert.assertEquals(expectedMessage, caught.getMessage());
    }

    /**
     * Create an Enumeration from the values in the list.
     * @param someList the list
     * @return an Enumeration
     */
    private static <T> Enumeration<T> toEnumeration(List<T> someList) {
    	Vector<T> someVector = new Vector<T>();
    	someVector.addAll(someList);
    	return someVector.elements();
    }

    //
    // assertContains
    //

    @Test
    public void testContains1() {
    	UtilAssert.assertContains("Gamma", expectedList);
    }

    @Test
    public void testContains2() {
    	AssertionError caught = null;
    	try {
        	UtilAssert.assertContains("Epsilon", expectedList);
    	}
    	catch (AssertionError e) {
    		caught = e;
    	}
    	Assert.assertEquals("Expected object 'Epsilon' not contained in list '[Alpha, Beta, Gamma, Delta]'.", caught.getMessage());
    }
}
