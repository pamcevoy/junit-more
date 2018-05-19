/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link StringAssert}.
 * @author Patrick
 */
public class StringAssertTest {

    //
    // assertContains
    //
    @Test
    public void testContains() {
        StringAssert.assertContains("alpha", "alphabet");
    }

    @Test
    public void testContainsFail() {
        AssertionError caught = null;
        try {
            StringAssert.assertContains("beta", "alphabet");
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("String 'beta' is not contained in 'alphabet'.", caught.getMessage());
    }

    //
    // assertStartsWith
    //

    /**
     * Test a String starts with a prefix.
     */
    @Test
    public void testStartsWithPrefixPass() {
        StringAssert.assertStartsWith("SomePrefix", "SomePrefixAndMore");
    }

    /**
     * Test a String does not start with a prefix.
     */
    @Test
    public void testStartsWithPrefixFail() {
        AssertionError caught = null;
        try {
            StringAssert.assertStartsWith("SomePrefix", "SomeString");
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("String 'SomeString' does not start with 'SomePrefix'.", caught.getMessage());
    }

    /**
     * Test a String does not start with a prefix.
     */
    @Test
    public void testStartsWithPrefixFailWithMessage() {
        AssertionError caught = null;
        try {
            StringAssert.assertStartsWith("Header", "SomePrefix", "SomeString");
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("Header: String 'SomeString' does not start with 'SomePrefix'.", caught.getMessage());
    }

    /**
     * Null String fails for a non-null prefix.
     */
    @Test
    public void testNullStartsWithPrefix() {
        AssertionError caught = null;
        try {
            StringAssert.assertStartsWith("SomePrefix", null);
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("String 'null' does not start with 'SomePrefix'.", caught.getMessage());
    }

    /**
     * Null prefix is okay for a non-null String.
     */
    @Test
    public void testStartsWithNullPrefix() {
        StringAssert.assertStartsWith(null, "SomeString");
    }

    /**
     * Null prefix is okay even for null String.
     */
    @Test
    public void testNullStringStartsWithNullPrefix() {
        StringAssert.assertStartsWith(null, null);
    }

    //
    // assertEndsWith
    //

    /**
     * Test a String ends with a suffix.
     */
    @Test
    public void testEndsWithSuffixPass() {
        StringAssert.assertEndsWith("SomeSuffix", "MoreWithSomeSuffix");
    }

    /**
     * Test a String does not end with a prefix.
     */
    @Test
    public void testEndsWithSuffixFail() {
        AssertionError caught = null;
        try {
            StringAssert.assertStartsWith("SomePrefix", "SomeString");
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("String 'SomeString' does not start with 'SomePrefix'.", caught.getMessage());
    }

    /**
     * Test a String does not end with a prefix.
     */
    @Test
    public void testEndsWithSuffixFailWithMessage() {
        AssertionError caught = null;
        try {
            StringAssert.assertEndsWith("Header", "SomeSuffix", "SomeString");
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("Header: String 'SomeString' does not end with 'SomeSuffix'.", caught.getMessage());
    }

    /**
     * Null String fails for a non-null suffix.
     */
    @Test
    public void testNullEndsWithSuffix() {
        AssertionError caught = null;
        try {
            StringAssert.assertEndsWith("SomeSuffix", null);
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("String 'null' does not end with 'SomeSuffix'.", caught.getMessage());
    }

    /**
     * Null suffix is okay for a non-null String.
     */
    @Test
    public void testEndsWithNullSuffix() {
        StringAssert.assertEndsWith(null, "SomeString");
    }

    /**
     * Null suffix is okay even for null String.
     */
    @Test
    public void testNullStringEndsWithNullSuffix() {
        StringAssert.assertEndsWith(null, null);
    }

    //
    // assertUnique
    //

    /**
     * Test that a null array passes.
     */
    @Test
    public void testUniqueNull() {
        String[] values = null;
        StringAssert.assertUnique(values);
    }

    /**
     * Test that an empty array passes.
     */
    @Test
    public void testUniqueEmpty() {
        String[] values = {};
        StringAssert.assertUnique(values);
    }

    /**
     * Test that an array of unique Strings passes.
     */
    @Test
    public void testUniqueStrings() {
        String[] values = { "Alpha", "Beta", "Gamma", "Delta", "Epsilon" };
        StringAssert.assertUnique(values);
    }

    /**
     * Test that an array of non-unique Strings fails.
     */
    @Test
    public void testNonUniqueStrings() {
        AssertionError caught = null;
        try {
            // fails because "Beta" is duplicated
            String[] values = { "Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Beta" };
            StringAssert.assertUnique(values);
        }
        catch (AssertionError e) {
            caught = e;
        }
        String expectedMessage = "Value at 5 equals value at 1. Actual: Beta";
        Assert.assertEquals(expectedMessage, caught.getMessage());
    }

    /**
     * Test that an array of non-unique Strings fails.
     */
    @Test
    public void testNonUniqueStringsWithMessage() {
        AssertionError caught = null;
        try {
            // fails because "Beta" is duplicated
            String[] values = { "Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Beta" };
            StringAssert.assertUnique("Greek letters", values);
        }
        catch (AssertionError e) {
            caught = e;
        }
        String expectedMessage = "Greek letters: Value at 5 equals value at 1. Actual: Beta";
        Assert.assertEquals(expectedMessage, caught.getMessage());
    }

    //
    // assertEqualsIgnoreLineSeparator
    //

    @Test
    public void testEqualsIgnoreLineSeparatorNull() {
    	AssertionError caught = null;
    	try {
        	StringAssert.assertEqualsIgnoreLineSeparator(null, "xxx");
    	}
    	catch (AssertionError e) {
    		caught = e;
    	}
    	String expectedMessage = "expected:<null> but was:<xxx>";
    	Assert.assertEquals(expectedMessage, caught.getMessage());
    }

    @Test
    public void testEqualsIgnoreLineSeparatorEmpty() {
    	AssertionError caught = null;
    	try {
        	StringAssert.assertEqualsIgnoreLineSeparator("", "xxx");
    	}
    	catch (AssertionError e) {
    		caught = e;
    	}
    	String expectedMessage = "expected:<[]> but was:<[xxx]>";
    	Assert.assertEquals(expectedMessage, caught.getMessage());
    }

    @Test
    public void testEqualsIgnoreLineSeparator1() {
    	String str = "able baker charlie";
    	testEqualsIgnoreLineSeparator(str, str);
    }

    @Test
    public void testEqualsIgnoreLineSeparator2() {
    	String str = "able baker charlie" + StringAssert.LINE_SEPARATOR;
    	testEqualsIgnoreLineSeparator(str, str);
    }

    @Test
    public void testEqualsIgnoreLineSeparator3() {
    	String str1 = "able baker charlie";
    	String str2 = str1 + StringAssert.LINE_SEPARATOR;
    	testEqualsIgnoreLineSeparator(str1, str2);
    }

    @Test
    public void testEqualsIgnoreLineSeparator4() {
    	String str1 = "able baker charlie";
    	String str2 = "able " + StringAssert.LINE_SEPARATOR + "baker " + StringAssert.LINE_SEPARATOR + "charlie";
    	testEqualsIgnoreLineSeparator(str1, str2);
    }

    /**
     * Test that these Strings are equal.
     * @param str1 the first String
     * @param str2 the second String
     */
    private void testEqualsIgnoreLineSeparator(String str1, String str2) {
    	AssertionError caught = null;
    	try {
        	StringAssert.assertEqualsIgnoreLineSeparator(str1, str2);
    	}
    	catch (AssertionError e) {
    		caught = e;
    	}
    	Assert.assertNull(caught);
    }
}
