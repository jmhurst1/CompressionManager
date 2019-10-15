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

import edu.ncsu.csc316.compressor.factory.DSAFactory;
import edu.ncsu.csc316.dsa.list.List;

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
		} catch(IOException e) {
			fail();
		}
		
		try {
			tester.processFile(validCompressed1, "output");
			checkFiles("output/fleaFlyExpected.txt", "output/fleaFly.txt");
		} catch(IOException e) {
			fail();
		}
		
		try {
			tester.processFile("imaginaryfile/input.txt", "output");
			fail();
		} catch(IOException e) {}
		
		//TODO Add more tests
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.manager.CompressionManager#getCompressed(edu.ncsu.csc316.dsa.list.List)}.
	 */
	@Test
	public void testGetCompressed() {
		List<String> uncompressedLines = DSAFactory.getIndexedList();
		uncompressedLines.add(0, "A flea and a fly in a flue");
		uncompressedLines.add(1, "Were imprisoned, so what could they do?");
		uncompressedLines.add(2, "Said the fly, \"let us flee!\"");
		uncompressedLines.add(3, "\"Let us fly!\" said the flea.");
		uncompressedLines.add(4, "So they flew through a flaw in the flue. ");
		uncompressedLines.add(5, "   - Ogden Nash");
		
		List<String> outputLines = tester.getCompressed(uncompressedLines);
		List<String> expectedLines = DSAFactory.getIndexedList();
		
		expectedLines.add(0, "0");
		expectedLines.add(1, "A flea and a fly in 4 flue");
		expectedLines.add(2, "Were imprisoned, so what could they do?");
		expectedLines.add(3, "Said the 5, \"let us flee!\"");
		expectedLines.add(4, "\"Let 18 5!\" said 16 2.");
		expectedLines.add(5, "So 13 flew through 4 flaw 6 16 7. ");
		expectedLines.add(6, "   - Ogden Nash");
		
		for(int i = 0; i < expectedLines.size(); i++) {
			assertEquals("Line " + i + " does not equal", expectedLines.get(i), outputLines.get(i));
		}
		assertEquals(expectedLines.size(), outputLines.size());
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
