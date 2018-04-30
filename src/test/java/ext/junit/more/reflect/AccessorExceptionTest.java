/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more.reflect;

import org.junit.Assert;
import org.junit.Test;

import ext.junit.more.reflect.AccessorException;

/**
 * Tests for {@link AccessorException}.
 * @author Patrick
 */
public class AccessorExceptionTest {
    /**
     * Test constructor that wraps a Throwable.
     */
    @Test
    public void testConstructor() {
        Throwable t = new Throwable();
        AccessorException e = new AccessorException(t);
        Assert.assertSame(t, e.getCause());
    }
}
