package io.relfadel.springboot_security_demo.utils;

import java.io.File;
import java.util.*;

/**
 * CSV files can be read using this util class
 *
 * Several examples of reading CSV files were provided by Baeldung.
 * @see <a href="https://www.baeldung.com/java-csv-file-array">Reading a CSV File into an Array</a>
 * Thank you, Baeldung!
 */
public final class CSVReader {
	private static final String COMMA_DELIMITER = ",";

	public static Map<Integer, List<String>> getRecords(final String path) {
		Map<Integer, List<String>> records = new HashMap<>();
		int index = 0;
		try (Scanner scanner = new Scanner(new File(path));) {
			while (scanner.hasNextLine()) {
				records.put(++index,  getRecordFromLine(scanner.nextLine()));
			}
		} catch (Exception ex) {
			System.out.println("File not found!");
		}
		return records;
	}

	private static List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return values;
	}
}
