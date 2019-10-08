package edu.ncsu.csc316.compressor.manager;

import java.io.FileNotFoundException;
import edu.ncsu.csc316.dsa.list.List;

/**
 * The CompressionManager class handles behaviors associated with compressing an
 * input file, decompressing an input file, and generating a report of the most
 * frequently used words in an input file.
 * 
 * @author Dr. King
 * @author <YOUR NAME HERE>
 * @author <YOUR NAME HERE>
 *
 */
public class CompressionManager {

	/**
	 * Initializes CompressionMananger
	 */
	public CompressionManager() {
		//TODO: complete this constructor as needed
	}

	/**
	 * Determines whether to compress or decompress the specified input file;
	 * creates the output file in the specified output directory; then returns a
	 * list of strings that represent each line of the processed output.
	 * 
	 * For example, if the input file is:
	 * 
	 *    Baby shark
	 *    I do not know the rest of this song
	 * 
	 * Then the returned list would be
	 * 
	 *    at index 0: Baby shark
	 *    at index 1: I do not know the rest of this song
	 * 
	 * @param pathToInputFile the path to the input file to be processed
	 * @param outputDirectory the directory where the processed file should be saved
	 * @return a list of strings that represent the lines of processed output
	 * @throws FileNotFoundException
	 */
	public List<String> processFile(String pathToInputFile, String outputDirectory) throws FileNotFoundException {
		//TODO: complete this method
		// Consider calling the getCompressed or getDecompressed helper methods
	}

	/**
	 * Compresses the input list that represents the specified input file, then
	 * returns a list of strings that represent each line of the compressed output.
	 * 
	 * For example, if the file being compressed contains the text:
	 * 
	 *    Baby shark
	 *    I do not know the rest of this song
	 * 
	 * Then the input list would be
	 * 
	 *    at index 0: Baby shark
	 *    at index 1: I do not know the rest of this song
	 * 
	 * @param fileLines the list of lines of text in the input file
	 * @return a list of strings that represent the compressed output
	 */
	public List<String> getCompressed(List<String> fileLines) {
		//TODO: complete this method
	}

	
	/**
	 * Deompresses the input list that represents the specified input file, then
	 * returns a list of strings that represent each line of the decompressed output.
	 * 
	 * For example, if the file being decompressed contains the text:
	 * 
	 *    Baby shark
	 *    I do not know the rest of this song
	 * 
	 * Then the input list would be:
	 * 
	 *    at index 0: Baby shark
	 *    at index 1: I do not know the rest of this song
	 * 
	 * @param fileLines the list of lines of text in the input file
	 * @return a list of strings that represent the compressed output
	 */	
	public List<String> getDecompressed(List<String> fileLines) {
		//TODO: complete this method
	}

	/**
	 * Generates a report of the most frequently appearing words
	 * in the input file.
	 * 
	 * @param pathToInputFile the path to the input file to process
	 * @param numberOfWords the number of words to include in the report
	 * @return a report of the most frequently appearing words in the input file
	 * @throws FileNotFoundException
	 */
	public String getMostFrequentWords(String pathToInputFile, int numberOfWords) throws FileNotFoundException {
		//TODO: complete this method
		// Consider calling the getMostFrequentWords(List, Integer) helper method
	}
	
	/**
	 * Returns a list of words that appear most frequently in the input list
	 * 
	 * @param inputList the list of words to process
	 * @param numberOfWords the number of words to include in the output list of most frequent words
	 * @return a list of words that appear most frequently in the input list
	 */
	public List<String> getMostFrequentWords(List<String> inputList, int numberOfWords) {
		//TODO: complete this method
	}

	//TODO: complete any other private helper methods as needed

}