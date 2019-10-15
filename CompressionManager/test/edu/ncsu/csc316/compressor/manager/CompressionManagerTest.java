/**
 * 
 */
package edu.ncsu.csc316.compressor.manager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests CompressionManager
 * @author Jason
 *
 */
public class CompressionManagerTest {
	
	public String validUncompressed1 = "input/fleaFly.txt";
	public String validCompressed1 = "input/fleaFly.316";
	public CompressionManager tester;

	/**
	 * Sets up for the tests
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tester = new CompressionManager();
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.manager.CompressionManager#processFile(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testProcessFile() {
		try {
			tester.processFile(validUncompressed1, "output");
			checkFiles("output/fleaFlyExpected.316", "output/fleaFly.316");
		} catch(FileNotFoundException e) {
			fail();
		}
		
		try {
			tester.processFile(validCompressed1, "output");
			checkFiles("output/fleaFlyExpected.txt", "output/fleaFly.txt");
		} catch(FileNotFoundException e) {
			fail();
		}
		
		try {
			tester.processFile("imaginaryfile/input.txt", "output");
			fail();
		} catch(FileNotFoundException e) {}
		
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.manager.CompressionManager#getCompressed(edu.ncsu.csc316.dsa.list.List)}.
	 */
	@Test
	public void testGetCompressed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.manager.CompressionManager#getDecompressed(edu.ncsu.csc316.dsa.list.List)}.
	 */
	@Test
	public void testGetDecompressed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.manager.CompressionManager#getMostFrequentWords(java.lang.String, int)}.
	 */
	@Test
	public void testGetMostFrequentWordsStringInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.manager.CompressionManager#getMostFrequentWords(edu.ncsu.csc316.dsa.list.List, int)}.
	 */
	@Test
	public void testGetMostFrequentWordsListOfStringInt() {
		fail("Not yet implemented");
	}
	
	/**
     * Helper method to compare two files for the same contents
     *
     * @param expFile expected output
     * @param actFile actual output
     */
    private void checkFiles(String expFile, String actFile) {
        try {
            Scanner expScanner = new Scanner(new File(expFile));
            Scanner actScanner = new Scanner(new File(actFile));

            while (expScanner.hasNextLine()) {
                assertEquals(expScanner.nextLine(), actScanner.nextLine());
            }

            expScanner.close();
            actScanner.close();
        } catch (IOException e) {
            fail("Error reading files.");
        }
    }

}
