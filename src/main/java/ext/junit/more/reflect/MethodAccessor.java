/**
 * (c) Copyright 2018 Patrick McEvoy
 * Well ISS.net or Charlie and Prashant probably have copyright.
 */
package ext.junit.more.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A helper to read and write private fields in classes.
 * <p>
 * I found this years ago and found it useful, but the project it was in
 * disappeared from sourceforge/googlecode, so I put it here and modified
 * it a little bit.
 * <p>
 * This class is used to access a method or field of an object no
 * matter what the access modifier of the method or field.  The syntax
 * for accessing fields and methods is out of the ordinary because this
 * class uses reflection to peel away protection.
 * <p>
 * Here is an example of using this to access a private member.
 * <code>resolveName</code> is a private method of <code>Class</code>.
 *
 * <pre>
 *  Object args = new Object[] { arg0, arg1, arg2 };
 *  PrivilegedAccessor.invokeMethod(Something.class, "someMethod", args);
 * </pre>
 *
 * @author Charlie Hubbard (chubbard@iss.net)
 * @author Prashant Dhokte (pdhokte@iss.net)
 */
public class MethodAccessor {

    /**
     * Invoke this method on this instance.
     *
     * @param instance the instance
     * @param methodName the method name
     * @return the result of invoking the method
     * @throws AccessorException if there is a problem
     */
    public static Object invoke(Object instance, String methodName) {
        Object[] args = {};
        Class<?>[] argTypes = {};
        return invoke(instance, methodName, args, argTypes);
    }

    /**
     * Calls a method on the given object instance with the given argument.
     *
     * @param instance the object instance
     * @param methodName the name of the method to invoke
     * @param arg the argument to pass to the method
     * @return the result of invoking the method
     * @throws AccessorException if there is a problem
     */
    public static Object invoke(Object instance, String methodName, Object arg) {
        Object[] args = { arg };
        Class<?>[] argTypes = getArgTypes(args);
        return invoke(instance, methodName, args, argTypes);
    }

    /**
     * Calls a method on the given object instance with the given arguments.
     *
     * @param instance the object instance
     * @param methodName the name of the method to invoke
     * @param args an array of objects to pass as arguments
     * @return the result of invoking the method
     * @throws AccessorException if there is a problem
     */
    public static Object invoke(Object instance, String methodName, Object[] args) {
        Class<?>[] argTypes = getArgTypes(args);
        return invoke(instance, methodName, args, argTypes);
    }

    /**
     * Invoke the method on this instance with these arguments.
     *
     * @param instance the instance
     * @param methodName the method name
     * @param args the arguments
     * @param argTypes the argument types
     * @return the result of invoking the method
     * @throws AccessorException if there is a problem
     */
    public static Object invoke(Object instance, String methodName, Object[] args, Class<?>[] argTypes) {
        try {
            return getMethod(instance.getClass(), methodName, argTypes).invoke(instance, args);
        }
        catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new AccessorException(e);
        }
    }

    /**
     * Invoke a class method.
     *
     * @param type the class
     * @param methodName the method name
     * @param arg the argument
     * @return the result of invoking the method
     * @throws AccessorException if there is a problem
     */
    public static Object invoke(Class<?> type, String methodName, Object arg) {
        Object[] args = { arg };
        Class<?>[] argTypes = getArgTypes(args);
        return invoke(type, methodName, args, argTypes);
    }

    /**
     * Invoke the method on this class.
     *
     * @param type the class
     * @param methodName the method name
     * @param arg the argument
     * @param argType the argument type
     * @return the result of invoking the object
     * @throws AccessorException if there is a problem
     */
    public static Object invoke(Class<?> type, String methodName, Object arg, Class<?> argType) {
        Object[] args = { arg };
        Class<?>[] argTypes = { argType };
        return invoke(type, methodName, args, argTypes);
    }

    /**
     * Invoke the method on this class.
     *
     * @param type the class
     * @param methodName the method name
     * @param args the arguments
     * @return the result of invoking the method
     * @throws AccessorException if there is a problem
     */
    public static Object invoke(Class<?> type, String methodName, Object[] args) {
        Class<?>[] argTypes = getArgTypes(args);
        return invoke(type, methodName, args, argTypes);
    }

    /**
     * Invoke the method on the class.
     *
     * @param type the class
     * @param methodName the method name
     * @param args the arguments
     * @param argTypes the argument types
     * @return the result of invoking the method
     * @throws AccessorException if there is a problem
     */
    public static Object invoke(Class<?> type, String methodName, Object[] args, Class<?>[] argTypes) {
        try {
            return getMethod(type, methodName, argTypes).invoke(null, args);
        }
        catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new AccessorException(e);
        }
    }

    /**
     * Get the arg types for the args.
     *
     * @param args the arg Objects
     * @return the arg types
     */
    private static Class<?>[] getArgTypes(Object[] args) {
        int length = (args == null) ? 0 : args.length;
        Class<?>[] argTypes = new Class<?>[length];
        for (int i = 0; i < length; i++) {
            argTypes[i] = (args[i] == null) ? null : args[i].getClass();
        }
        return argTypes;
    }

    /**
     * Format the argTypes as a method signature.
     * E.g. (java.lang.String, int.class)
     * @param argTypes the argument types
     * @return a String of the method signature
     */
    private static String formatArgTypes(Class<?>[] argTypes) {
        String[] names = getTypeNames(argTypes);
        return "(" + String.join(", ", names) + ")";
    }

    /**
     * Get the names of these argument types.
     * @param argTypes the argument types
     * @return an array of class names
     */
    private static String[] getTypeNames(Class<?>[] argTypes) {
        int size = (argTypes == null) ? 0 : argTypes.length;
        String[] names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = (argTypes[i] == null) ? "" : argTypes[i].getTypeName();
        }
        return names;
    }

    /**
     * Return the named method with a method signature matching classTypes
     * from the given class.
     *
     * @param type the class
     * @param methodName the method name
     * @param argTypes the parameter types
     * @return the Method
     * @throws NoSuchMethodException if the method is not found
     */
    private static Method getMethod(Class<?> type, String methodName, Class<?>[] argTypes)
            throws NoSuchMethodException {
        if (type == null) {
            throw new NoSuchMethodException("Invalid method : " + methodName + formatArgTypes(argTypes));
        }

        Method method = null;
        try {
            method = type.getDeclaredMethod(methodName, argTypes);
            method.setAccessible(true);
        }
        catch (NoSuchMethodException e) {
            method = getMethod(type.getSuperclass(), methodName, argTypes); // recurse
        }
        return method;
    }
}
