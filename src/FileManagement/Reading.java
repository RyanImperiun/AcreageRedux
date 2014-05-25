package FileManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reading {
	String			fileName;
	String			line;
	int				i			= 0;
	public int[]	fileTileID	= new int[350 * 350];

	public Reading(String filePath) {
		fileName = filePath;

		// The name of the file to open. --- C:/Users/Ryan/Desktop/temp.txt ---
		// This will reference one line at a time
		long beforeTime = System.currentTimeMillis();

		readFile();

		long afterTime = System.currentTimeMillis();
		long totalTime = afterTime - beforeTime;
		System.out.println("World read in " + totalTime + " ms");
	}

	private void readFile() {
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				fileTileID[i] = Integer.parseInt(line);
				i++;
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
}