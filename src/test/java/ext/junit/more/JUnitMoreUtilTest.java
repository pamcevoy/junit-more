/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link JUnitMoreUtil}.
 * @author Patrick
 */
public class JUnitMoreUtilTest {
    /**
     * Test that null is formatted as an empty String.
     */
    @Test
    public void testFormatHeaderNull() {
        Assert.assertEquals("", JUnitMoreUtil.formatHeader(null));
    }

    @Test
    public void testFormatHeader() {
        String message = "Testing";
        Assert.assertEquals("Testing: ", JUnitMoreUtil.formatHeader(message));
    }
}
