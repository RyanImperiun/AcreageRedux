package FileManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reading {
	String	home	= System.getProperty("user.home");
	String	dir		= home + "/Desktop";
	
	String			fileName;
	String			line;
	int				i					= 0, ii = 0;
	public int[]	fileTileID			= new int[350 * 350];
	public int[]	fileTilePeripherals	= new int[350 * 350];

	public Reading(String filePath) {
		fileName = filePath;

		// The name of the file to open. --- C:/Users/Ryan/Desktop/temp.txt ---
		// This will reference one line at a time

		readAll();
	}

	private void readAll() {
		long beforeTime = System.currentTimeMillis();

		readWorld();
		readPeripherals();

		long afterTime = System.currentTimeMillis();
		long totalTime = afterTime - beforeTime;
		System.out.println("Total readtime: " + totalTime + " ms");
	}

	private void readPeripherals() {
		String readPath = dir + "/worldPeripherals.txt";
		long beforeTime = System.currentTimeMillis();
		
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(readPath);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				fileTilePeripherals[ii] = Integer.parseInt(line);
				ii++;
			}
			
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + readPath + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + readPath + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		long afterTime = System.currentTimeMillis();
		long totalTime = afterTime - beforeTime;
		System.out.println("Peripherals read in " + totalTime + " ms");
	}

	private void readWorld() {
		String readPath = dir + "/worldData.txt";
		long beforeTime = System.currentTimeMillis();

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(readPath);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				fileTileID[i] = Integer.parseInt(line);
				i++;
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + readPath + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + readPath + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		long afterTime = System.currentTimeMillis();
		long totalTime = afterTime - beforeTime;
		System.out.println("World read in " + totalTime + " ms");
	}
}