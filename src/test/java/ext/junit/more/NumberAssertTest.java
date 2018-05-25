/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link NumberAssert}.
 * @author Patrick
 */
public class NumberAssertTest
{
    //
    // assertGreaterThan
    //

    @Test
    public void testGreaterThanPass() {
        NumberAssert.assertGreaterThan(100, 123);
    }

    @Test
    public void testGreaterThanFail() {
        AssertionError caught = null;
        try {
            NumberAssert.assertGreaterThan(123, 99);
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("Actual '99' less than expected minimum '123'.", caught.getMessage());
    }

    //
    // assertLessThan
    //

    @Test
    public void testLessThanPass() {
        NumberAssert.assertLessThan(99, 88);
    }

    @Test
    public void testLessThanFail() {
        AssertionError caught = null;
        try {
            NumberAssert.assertLessThan(66, 77);
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("Actual '77' greater than expected maximum '66'.", caught.getMessage());
    }

    //
    // assertBetween
    //

    @Test
    public void testBetweenPass() {
        NumberAssert.assertBetween(125, 150, 175);
    }

    @Test
    public void testBetweenFail1() {
        AssertionError caught = null;
        try {
            NumberAssert.assertBetween(125, 175, 150);
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("Actual '175' greater than expected maximum '150'.", caught.getMessage());
    }

    @Test
    public void testBetweenFail2() {
        AssertionError caught = null;
        try {
            NumberAssert.assertBetween(125, 115, 150);
        }
        catch (AssertionError e) {
            caught = e;
        }
        Assert.assertEquals("Actual '115' less than expected minimum '125'.", caught.getMessage());
    }
}
