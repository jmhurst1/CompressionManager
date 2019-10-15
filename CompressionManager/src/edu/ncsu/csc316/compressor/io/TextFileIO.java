/**
 * 
 */
package edu.ncsu.csc316.compressor.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc316.compressor.factory.DSAFactory;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * TextFileIO provides methods that help to read and write to given files using
 * Lists of strings and given file names.
 * 
 * @author Jason Hurst
 *
 */
public class TextFileIO {

	/**
	 * Returns a list of strings representing the lines of a given file.
	 * 
	 * @param fileName the file to read from
	 * @return a list of strings representing the lines of a given file.
	 * @throws FileNotFoundException if the file cannot be read
	 */
	public static List<String> readFileByLine(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		List<String> lines = DSAFactory.getIndexedList();

		while (s.hasNextLine()) {
			lines.addLast(s.nextLine());
		}
		s.close();
		return lines;
	}

	/**
	 * Returns a list of strings representing the words of a given file.
	 * 
	 * @param fileName the file to read from
	 * @return a list of strings representing the words of a given file.
	 * @throws FileNotFoundException if the file cannot be read
	 */
	public static List<String> readFileByWord(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		List<String> words = DSAFactory.getIndexedList();

		while (s.hasNextLine()) {
			for (String str : processLine(s.nextLine())) {
				words.addLast(str);
			}
		}
		s.close();
		return words;
	}

	public static List<String> processLine(String line) {
		Scanner s = new Scanner(line);
		s.useDelimiter(" ");
		List<String> words = DSAFactory.getIndexedList();

		while (s.hasNext()) {
			String currentWord = s.next();
			String bareWord = currentWord.replaceAll("[^a-zA-Z]", "");
			words.addLast(bareWord);
		}
		s.close();
		return words;
	}

	/**
	 * Writes a list of string lines to a file
	 * 
	 * @param lineList the given list of strings
	 * @param fileName the file to write to
	 * @throws IOException if unable to write to the file
	 */
	public static void writeFile(List<String> lineList, String fileName) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < lineList.size(); i++) {
			fileWriter.println(lineList.get(i));
		}
		fileWriter.close();
	}
}
