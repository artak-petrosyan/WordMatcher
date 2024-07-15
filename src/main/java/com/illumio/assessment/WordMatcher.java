package com.illumio.assessment;

import java.io.*;
import java.util.*;

import com.illumio.assessment.dictionary.Trie;

/**
 * WordMatcher is a utility class for finding occurrences of predefined words
 * within a text input. It utilizes a Trie data structure for efficient word
 * lookups.
 */
public class WordMatcher {

	/**
	 * Loads predefined words from a text file into a Trie data structure.
	 * 
	 * @param filename The path to the file containing the predefined words.
	 * @return A Trie instance containing the loaded words.
	 * @throws IOException If an error occurs during file reading.
	 */
	public static Trie loadPredefinedWords(String filename) throws IOException {
		long startTime = System.nanoTime();
		int wordsCounter = 0;
		Trie predefinedWords = new Trie();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				predefinedWords.add(line.trim().toLowerCase());
				wordsCounter++;
			}
		}
		long duration = System.nanoTime() - startTime;
		System.out.format("Loaded %d predefined words in %f milliseconds.%n", wordsCounter,
				(double) duration / 1000000);
		return predefinedWords;
	}

	/**
	 * Finds and counts matches of predefined words within a text input.
	 * 
	 * @param inputFile       The path to the file containing the text input.
	 * @param predefinedWords The Trie instance containing the predefined words.
	 * @return A map of matched words to their respective counts.
	 * @throws IOException If an error occurs during file reading.
	 */
	public static Map<String, Integer> findMatches(String inputFile, Trie predefinedWords) throws IOException {
		long startTime = System.nanoTime();
		int wordsCounter = 0;
		Map<String, Integer> matchCounts = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				for (String word : line.trim().split("\\W+")) {
					word = word.toLowerCase();
					if (predefinedWords.search(word)) {
						matchCounts.put(word, matchCounts.getOrDefault(word, 0) + 1);
					}
					wordsCounter++;
				}
			}
		}
		long duration = System.nanoTime() - startTime;
		System.out.format("Processed %d words in %f milliseconds.%n", wordsCounter, (double) duration / 1000000);
		return matchCounts;
	}

	/**
	 * Pads a string to the right with spaces to reach a specified length. This is a
	 * helper method for formatting output.
	 *
	 * @param s The string to pad.
	 * @param n The desired total length of the padded string.
	 * @return The padded string.
	 */
	private static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

	/**
	 * Writes data from a map into a CSV (Comma-Separated Values) file with a unique filename and returns the generated filename.
	 *
	 * <p>This method creates a CSV file with a unique filename, appending a numeric suffix
	 * if the original filename already exists. It includes an optional header row and
	 * writes each key-value pair (word and its count) as a comma-separated line in the file.
	 * The generated unique filename is then returned.</p>
	 *
	 * @param header    The header row for the CSV file (can be null for no header).
	 * @param data      A map containing the data to be written, where keys are strings
	 *                  and values are integers. If null, the method will return null and not write to a file.
	 * @param fileName  The base name of the CSV file to be created. A numeric suffix will
	 *                  be added if necessary to ensure uniqueness.
	 * @return          The actual filename of the created CSV file, including any numeric suffixes. Returns null if data is null.
	 * @throws IOException If an I/O error occurs during file writing.
	 */
	private static String writeToCSVFile(String header, Map<String, Integer> data, String fileName) throws IOException {
		if (data == null)
			return null;
		int num = 0;
		String newFileName = fileName + ".csv";
		File file = new File(newFileName);
		while (file.exists()) {
			num++;
			newFileName =  String.format("%s(%d).csv", fileName, num);
			file = new File(newFileName);
		}
		FileWriter fileWriter = new FileWriter(file);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		if (header != null) {
			printWriter.println(header);
		}
		data.forEach((k, v) -> {
			printWriter.printf("%s,%d%n", k, v);
		});
		printWriter.close();
		return newFileName;
	}

	/**
	 * The main entry point of the application. This method handles command-line
	 * arguments, loads predefined words, finds matches in the input file, and
	 * prints the results.
	 *
	 * @param args Command-line arguments: 
	 * 				- args[0]: The path to the input text file. 
	 * 				- args[1]: The path to the file containing predefined words. 
	 * 				- args[2]: Optional flag for saving result in CSV format ("result.csv"). 
	 * 							Flag will be true if lower case value is followed: [ y, yes, true]
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println(
					"Usage: java WordMatcher <input_file> <predefined_words_file> <optional_save_result_csv[Y/N]>");
			return;
		}
		boolean saveCSV = false;
		if (args.length > 2) {
			String choice = args[2].trim().toLowerCase();
			saveCSV = choice.matches("^(yes|y|true)$");
		}

		try {
			Trie predefinedWords = loadPredefinedWords(args[1]);
			Map<String, Integer> matchCounts = findMatches(args[0], predefinedWords);
			System.out.println("====================================================");
			System.out.println(padRight("Predefined word", 40) + " Match count");
			System.out.println("----------------------------------------------------");
			for (Map.Entry<String, Integer> entry : matchCounts.entrySet()) {
				System.out.println(padRight(entry.getKey(), 40) + " " +entry.getValue());
			}
			System.out.println("====================================================");
			if (saveCSV) {
				String fileName = writeToCSVFile("Word,count", matchCounts, "result");
				if(fileName != null)
					System.out.format("Results saved to \"%s\" file.%n", fileName);
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}