package edu.ncsu.csc316.compressor.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
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
	 * @throws IOException
	 */
	public List<String> processFile(String pathToInputFile, String outputDirectory) throws IOException {
		List<String> fileLines = TextFileIO.readFileByLine(pathToInputFile);
		String ending;
		if (fileLines.get(0).equals("0")) { // Decompress
			fileLines = getDecompressed(fileLines);
			ending = ".txt";
		} else { // Compress
			fileLines = getCompressed(fileLines);
			ending = ".316";
		}
		String fileName = pathToInputFile.substring(pathToInputFile.lastIndexOf('/'), pathToInputFile.lastIndexOf('.'));

		TextFileIO.writeFile(fileLines, outputDirectory + fileName + ending);
		return fileLines;
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
		compressed.addLast("0");

		for (int i = 0; i < fileLines.size(); i++) {

			// Replaces all known words in file line with their keys
			String temp = fileLines.get(i);
			for (Entry<String, Integer> e : wordMap.entrySet()) {
				// Replaces all instances of a word with its key
				temp = temp.replaceAll("\\b" + e.getKey() + "\\b", Integer.toString(e.getValue()));
			}

			Scanner wordScanner = new Scanner(temp);
			wordScanner.useDelimiter("[ |'|-|]");

			// Gets a word from line of text
			while (wordScanner.hasNext()) {
				String current = wordScanner.next();
				// Strips the word of punctuation
				String bareWord = current.replaceAll("[^a-zA-Z]", "");

				if (!bareWord.equals("")) {
					// Only inserted on first time word is encountered
					if (wordMap.get(bareWord) == null) {
						wordMap.put(bareWord, wordCount);
						wordCount++;
					} else {
						// Otherwise replace all instances after first of the word with its key
						temp = temp.replaceAll("\\b" + bareWord + "\\b", Integer.toString(wordMap.get(bareWord)));
						temp = temp.replaceFirst(Integer.toString(wordMap.get(bareWord)), bareWord);
					}
				}
			}

			compressed.addLast(temp);
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
		fileLines.remove(0); // Remove 0 from compressed file

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
					if (!bareWord.equals("")) {
						wordMap.put(wordCount, bareWord);
						wordCount++;
					}
				}
			}
			String temp = fileLines.get(i);
			for (Entry<Integer, String> e : wordMap.entrySet()) {
				// Replaces all instances of a number with its word
				temp = temp.replaceAll("\\b" + Integer.toString(e.getKey()) + "\\b", e.getValue());
			}
			uncompressed.addLast(temp);
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
			sb.append("   ");
			sb.append(sortedWords.get(i));
			sb.append("\n");
		}
		sb.append("]");

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

		// Finds frequencies of all words in input list
		for (int i = 0; i < inputList.size(); i++) {
			if (!inputList.get(i).equals("")) {
				if (frequencies.get(inputList.get(i).toLowerCase()) != null) {
					// Increase frequency of word
					frequencies.put(inputList.get(i).toLowerCase(),
							frequencies.get(inputList.get(i).toLowerCase()) + 1);
				} else {
					// First instance of word, add to map
					frequencies.put(inputList.get(i).toLowerCase(), 1);
				}
			}
		}

		// Sort integer frequencies using merge sort (lowest to highest)
		Integer[] freqsForSorting = new Integer[frequencies.size()];
		Iterator<Integer> it = frequencies.values().iterator();
		int i = 0;
		while (it.hasNext()) {
			freqsForSorting[i] = it.next();
			i++;
		}
		Sorter<Integer> s = DSAFactory.getComparisonSorter();
		s.sort(freqsForSorting);

		// Matches sorted frequencies to matching keys in alphabetical order
		List<String> sortedWords = DSAFactory.getIndexedList();
		for (int j = freqsForSorting.length - 1; j >= Math.max(freqsForSorting.length - numberOfWords, 0); j--) {
			for (Entry<String, Integer> e : frequencies.entrySet()) {
				if (e.getValue() == freqsForSorting[j]) {
					sortedWords.addLast(e.getKey());
					frequencies.remove(e.getKey());
					break;
				}
			}
		}
		return sortedWords;
	}

}
