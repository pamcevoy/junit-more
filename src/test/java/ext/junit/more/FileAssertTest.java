/**
 * (c) Copyright 2018 Patrick McEvoy
 */
package ext.junit.more;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link FileAssert}.
 * @author Patrick
 */
public class FileAssertTest {

    private static File javaDir;

    /**
     * Get a known directory so tests can assert it exists.
     */
    @BeforeClass
    public static void setUpClass() {
        // Pick a directory known to exist.
        // I could have used user.home or java.io.tmpdir instead.
        javaDir = new File(System.getProperty("java.home"));
    }

    /**
     * Clear out the variable.
     */
    @AfterClass
    public static void tearDownClass() {
        javaDir = null;
    }

    /**
     * Verify the directory exists.
     */
    @Test
    public void testDirExists() {
        FileAssert.assertExists(javaDir);
    }

    /**
     * Verify the file exists.
     */
    @Test
    public void testFileExists() {
        FileAssert.assertExists(new File(javaDir, "README.html"));
    }

    /**
     * Verify the file does not exist.
     */
    @Test
    public void testFileNotExists() {
        AssertionError caught = null;
        try {
            FileAssert.assertExists(new File(javaDir, "This_file_does_not_exist.txt"));
        }
        catch (AssertionError e) {
            caught = e;
        }
        String expectedMessage = "File does not exist: " + javaDir + File.separator + "This_file_does_not_exist.txt";
        Assert.assertEquals(expectedMessage, caught.getMessage());
    }
}
