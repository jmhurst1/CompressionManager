package edu.ncsu.csc316.compressor.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

import edu.ncsu.csc316.compressor.factory.DSAFactory;
import edu.ncsu.csc316.compressor.io.TextFileIO;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.map.SkipListMap;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * The CompressionManager class handles behaviors associated with compressing an
 * input file, decompressing an input file, and generating a report of the most
 * frequently used words in an input file.
 * 
 * @author Dr. King
 * @author Gianna Mastrandrea
 * @author Jason Hurst
 *
 */
public class CompressionManager {

	/**
	 * Initializes CompressionMananger
	 */
	public CompressionManager() {
		// TODO: complete this constructor as needed
	}

	/**
	 * Determines whether to compress or decompress the specified input file;
	 * creates the output file in the specified output directory; then returns a
	 * list of strings that represent each line of the processed output.
	 * 
	 * For example, if the input file is:
	 * 
	 * Baby shark | I do not know the rest of this song
	 * 
	 * Then the returned list would be
	 * 
	 * at index 0: Baby shark at index 1: I do not know the rest of this song
	 * 
	 * @param pathToInputFile the path to the input file to be processed
	 * @param outputDirectory the directory where the processed file should be saved
	 * @return a list of strings that represent the lines of processed output
	 * @throws FileNotFoundException
	 */
	public List<String> processFile(String pathToInputFile, String outputDirectory) throws FileNotFoundException {
		// TODO: complete this method
		// Consider calling the getCompressed or getDecompressed helper methods
	}

	/**
	 * Compresses the input list that represents the specified input file, then
	 * returns a list of strings that represent each line of the compressed output.
	 * 
	 * For example, if the file being compressed contains the text:
	 * 
	 * Baby shark | I do not know the rest of this song
	 * 
	 * Then the input list would be
	 * 
	 * at index 0: Baby shark at index 1: I do not know the rest of this song
	 * 
	 * @param fileLines the list of lines of text in the input file
	 * @return a list of strings that represent the compressed output
	 */
	public List<String> getCompressed(List<String> fileLines) {
		Map<String, Integer> wordMap = DSAFactory.getOrderedMap();
		List<String> compressed = DSAFactory.getIndexedList();
		int wordCount = 1;

		for (int i = 0; i < fileLines.size(); i++) {
			Scanner wordScanner = new Scanner(fileLines.get(i));
			wordScanner.useDelimiter("[ |'|-|]");

			// Gets a word from line of text
			while (wordScanner.hasNext()) {
				String current = wordScanner.next();
				// Strips the word of punctuation
				String bareWord = current.replaceAll("[^a-zA-Z]", "");

				// Only inserted on first time word is encountered
				if (wordMap.get(bareWord) == null) {
					wordMap.put(bareWord, wordCount);
					wordCount++;
				}
			}
			for (Entry<String, Integer> e : wordMap.entrySet()) {
				// Replaces all instances of a word with its key
				String temp = fileLines.get(i).replaceAll(e.getKey(), Integer.toString(e.getValue()));
				// Replaces the first instance of each key with its word
				temp = temp.replaceFirst(Integer.toString(e.getValue()), e.getKey());
				compressed.addLast(temp);
			}
			wordScanner.close();
		}
		return compressed;
	}

	/**
	 * Deompresses the input list that represents the specified input file, then
	 * returns a list of strings that represent each line of the decompressed
	 * output.
	 * 
	 * For example, if the file being decompressed contains the text:
	 * 
	 * Baby shark | I do not know the rest of this song
	 * 
	 * Then the input list would be:
	 * 
	 * at index 0: Baby shark at index 1: I do not know the rest of this song
	 * 
	 * @param fileLines the list of lines of text in the input file
	 * @return a list of strings that represent the compressed output
	 */
	public List<String> getDecompressed(List<String> fileLines) {
		Map<Integer, String> wordMap = DSAFactory.getOrderedMap();
		List<String> uncompressed = DSAFactory.getIndexedList();
		int wordCount = 1;

		for (int i = 0; i < fileLines.size(); i++) {
			Scanner wordScanner = new Scanner(fileLines.get(i));
			wordScanner.useDelimiter("[ |'|-|]");

			// Gets a word from line of text
			while (wordScanner.hasNext()) {
				String currentWord = wordScanner.next();
				// Strips the word or number of punctuation
				String bareWord = currentWord.replaceAll("[^a-zA-Z0-9]", "");
				// If bare word is not a number, add it to wordMap
				try {
					Integer.parseInt(bareWord);
				} catch (Exception e) {
					wordMap.put(wordCount, bareWord);
					wordCount++;
				}
			}
			for (Entry<Integer, String> e : wordMap.entrySet()) {
				// Replaces all instances of a number with its word
				String temp = fileLines.get(i).replaceAll(Integer.toString(e.getKey()), e.getValue());
				uncompressed.addLast(temp);
			}
			wordScanner.close();
		}
		return uncompressed;
	}

	/**
	 * Generates a report of the most frequently appearing words in the input file.
	 * 
	 * @param pathToInputFile the path to the input file to process
	 * @param numberOfWords   the number of words to include in the report
	 * @return a report of the most frequently appearing words in the input file
	 * @throws FileNotFoundException
	 */
	public String getMostFrequentWords(String pathToInputFile, int numberOfWords) throws FileNotFoundException {
		Scanner s = new Scanner(new File(pathToInputFile));
		List<String> words = DSAFactory.getIndexedList();

		while (s.hasNextLine()) {
			for (String str : TextFileIO.processLine(s.nextLine())) {
				words.addLast(str);
			}
		}
		s.close();

		List<String> sortedWords = getMostFrequentWords(words, numberOfWords);

		StringBuilder sb = new StringBuilder();
		sb.append("Most Frequent Words Report [\n");
		for (int i = 0; i < numberOfWords; i++) {
			sb.append(String.format("%3" + sortedWords.get(i) + "\n"));
		}
		sb.append("]\n");

		return sb.toString();
	}

	/**
	 * Returns a list of words that appear most frequently in the input list
	 * 
	 * @param inputList     the list of words to process
	 * @param numberOfWords the number of words to include in the output list of
	 *                      most frequent words
	 * @return a list of words that appear most frequently in the input list
	 */
	public List<String> getMostFrequentWords(List<String> inputList, int numberOfWords) {
		Map<String, Integer> frequencies = DSAFactory.getOrderedMap();

		for (int i = 0; i < inputList.size(); i++) {
			if (frequencies.get(inputList.get(i).toLowerCase()) != null) {
				// Increase frequency of word
				frequencies.put(inputList.get(i).toLowerCase(), frequencies.get(inputList.get(i).toLowerCase() + 1));
			} else {
				// First instance of word, add to map
				frequencies.put(inputList.get(i).toLowerCase(), 1);
			}
		}

		// Custom comparator that will sort by values (descending), then by keys
		// alphabetically in the case of identical frequencies
		Comparator c = new FreqComparator(); // hmmm

		List<Entry<String, Integer>> sortedFreq = DSAFactory.getIndexedList();
		for (Entry<String, Integer> e : frequencies.entrySet()) {
			sortedFreq.addLast(e);
		}

		Sorter s = DSAFactory.getComparisonSorter();
		// sort them somehow??

		List<String> sortedWords = DSAFactory.getIndexedList();
		for (int i = 0; i < numberOfWords; i++) {
			// Adds words to list with frequencies highest to lowest
			sortedWords.addLast(sortedFreq.get(i).getKey());
		}

		return sortedWords;
	}

}
