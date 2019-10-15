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
		uncompressedLines.addLast("A flea and a fly in a flue");
		uncompressedLines.addLast("Were imprisoned, so what could they do?");
		uncompressedLines.addLast("Said the fly, \"let us flee!\"");
		uncompressedLines.addLast("\"Let us fly!\" said the flea.");
		uncompressedLines.addLast("So they flew through a flaw in the flue. ");
		uncompressedLines.addLast("   - Ogden Nash");
		
		List<String> outputLines = tester.getCompressed(uncompressedLines);
		List<String> expectedLines = DSAFactory.getIndexedList();
		
		expectedLines.addLast("0");
		expectedLines.addLast("A flea and a fly in 4 flue");
		expectedLines.addLast("Were imprisoned, so what could they do?");
		expectedLines.addLast("Said the 5, \"let us flee!\"");
		expectedLines.addLast("\"Let 18 5!\" said 16 2.");
		expectedLines.addLast("So 13 flew through 4 flaw 6 16 7. ");
		expectedLines.addLast("   - Ogden Nash");
		
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
		List<String> compressedLines = DSAFactory.getIndexedList();
		compressedLines.addLast("0");
		compressedLines.addLast("A flea and a fly in 4 flue");
		compressedLines.addLast("Were imprisoned, so what could they do?");
		compressedLines.addLast("Said the 5, \"let us flee!\"");
		compressedLines.addLast("\"Let 18 5!\" said 16 2.");
		compressedLines.addLast("So 13 flew through 4 flaw 6 16 7. ");
		compressedLines.addLast("   - Ogden Nash");
		
		List<String> outputLines = tester.getDecompressed(compressedLines);
		List<String> expectedLines = DSAFactory.getIndexedList();
		
		expectedLines.addLast("A flea and a fly in a flue");
		expectedLines.addLast("Were imprisoned, so what could they do?");
		expectedLines.addLast("Said the fly, \"let us flee!\"");
		expectedLines.addLast("\"Let us fly!\" said the flea.");
		expectedLines.addLast("So they flew through a flaw in the flue. ");
		expectedLines.addLast("   - Ogden Nash");
		
		for(int i = 0; i < expectedLines.size(); i++) {
			assertEquals("Line " + i + " does not equal", expectedLines.get(i), outputLines.get(i));
		}
		assertEquals(expectedLines.size(), outputLines.size());
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
