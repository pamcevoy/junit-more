/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more.reflect;

/**
 * An Exception to be thrown if there is a problem accessing a field or method.
 * @author Patrick
 */
public class AccessorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccessorException(Throwable t) {
        super(t);
    }
}
