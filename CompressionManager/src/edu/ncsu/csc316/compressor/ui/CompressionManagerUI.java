/**
 * 
 */
package edu.ncsu.csc316.compressor.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.compressor.manager.CompressionManager;

/**
 * Manages client interactions with the program
 * 
 * @author Jason
 * @author Gianna Mastrandrea
 */
public class CompressionManagerUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompressionManager manager = new CompressionManager();
		Scanner sc = new Scanner(System.in);
		System.out.println("*************************");
		System.out.println(
				"This software system is designed to compress and decompress \nfiles to save on storage expenses.\n");
		System.out.println(
				"Begin by specifying which file you would like to compress, \ndecompress, or get the most frequent words of.\n");
		System.out.println(
				"The accepted file type for compression are .txt files. \nCompressed files will be exported as .316 files.\n");
		System.out.println(
				"The accepted file type for decompression are .316 files. \nDecompressed files will be exported as .txt files.\n");
		System.out.println("Enter Q to quit.\n");
		System.out.println("*************************");

		String filename = "";
		while (filename == "") {
			System.out.print("Enter file name: ");
			filename = sc.next();

			if (filename.equalsIgnoreCase("Q")) {
				System.exit(0);
			}

			File temp = new File(filename);
			if (!temp.exists()) {
				filename = "";
				System.out.println("Invalid file.");
			}
		}

		System.out.println("*************************");
		System.out.println("Please choose an operation:");
		System.out.println("c - Compress");
		System.out.println("d - Decompress");
		System.out.println("f - Most Frequent Words");
		System.out.println("q - Quit\n");
		System.out.println("*************************");

		String op = "";
		while (op == "") {
			System.out.print("Enter desired operation: ");
			op = sc.next();

			if (op.equalsIgnoreCase("Q")) {
				System.exit(0);
			}

			if (op.equalsIgnoreCase("c")) {
				System.out.println("*************************");
				System.out.print("Enter desired output directory: ");
				String dir = sc.next();
				try {
					for (String str : manager.processFile(filename, dir)) {
						System.out.println(str);
					}
				} catch (FileNotFoundException e) {
					// Since filename was checked earlier, an exception should never be found.
				}
			} else if (op.equalsIgnoreCase("d")) {
				System.out.println("*************************");
				System.out.print("Enter desired output directory: ");
				String dir = sc.next();
				try {
					for (String str : manager.processFile(filename, dir)) {
						System.out.println(str);
					}
				} catch (FileNotFoundException e) {
					// Since filename was checked earlier, an exception should never be found.
				}
			} else if (op.equalsIgnoreCase("f")) {
				System.out.println("*************************");
				int numWords = 0;
				while (numWords <= 0) {
					System.out.print("Enter number of words to be included in the report: ");
					numWords = sc.nextInt();
					if (numWords <= 0) {
						System.out.println("Invalid number of words.");
					}
				}
				try {
					System.out.println(manager.getMostFrequentWords(filename, numWords));
				} catch (FileNotFoundException e) {
					// Since filename was checked earlier, an exception should never be found.
				}
			} else {
				System.out.println("Invalid operation.");
				op = "";
			}
		}
		System.out.println("Operation successfully performed.\n");
		System.out.println("*************************");
		sc.close();
	}

}
