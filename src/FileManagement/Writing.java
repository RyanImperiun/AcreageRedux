package FileManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Core.Game;
import Core.GameResourceLoader;
import Core.Launcher;

public class Writing {
	String	home	= System.getProperty("user.home");
	String	dir		= home + "/Desktop";
	Game	g;

	public Writing(Game game) {
		g = game;

		// The name of the file to open. --- C:/Users/Ryan/Desktop/temp2.txt --
	}

	public void saveAllFiles() {
		saveResources();
		saveWorldPeripherals();
		saveWorld();
	}

	private void saveResources() {
		String writePath = dir + "/playerResources.txt";
		long beforeTime = System.currentTimeMillis();

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(writePath);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < g.inv.resourceAmounts.length; i++) {
				String toWrite = Integer.toString(g.inv.resourceAmounts[i]);
				bufferedWriter.write(toWrite);
				bufferedWriter.newLine();
			}

			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file " + writePath);
		}

		long afterTime = System.currentTimeMillis();
		long totalTime = afterTime - beforeTime;
		System.out.println("Resources saved in " + totalTime + " ms");
	}

	private void saveWorldPeripherals() {
		String writePath = dir + "/worldPeripherals.txt";
		long beforeTime = System.currentTimeMillis();

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(writePath);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String toWrite = "";
			for (int i = 0; i < g.level.tiles.length; i++) {
				if (g.level.tiles[i].getTileID() == GameResourceLoader.Stone && g.level.tiles[i].hasRock) {
					if (!g.level.tiles[i].hasOre) {
						toWrite = "1";
					} else {
						toWrite = "2";
					}
				}else if(g.level.tiles[i].getTileID() == GameResourceLoader.Grass && g.level.tiles[i].hasTree){
					toWrite = "3";
				}else{
					toWrite="0";
				}
				
				bufferedWriter.write(toWrite);
				bufferedWriter.newLine();
			}

			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file " + writePath);
		}

		long afterTime = System.currentTimeMillis();
		long totalTime = afterTime - beforeTime;
		System.out.println("Peripherals saved in " + totalTime + " ms");
	}

	private void saveWorld() {
		String writePath = dir + "/worldData.txt";
		long beforeTime = System.currentTimeMillis();

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(writePath);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(Integer.toString(Launcher.worldSize));
			bufferedWriter.newLine();

			for (int i = 0; i < g.level.tiles.length; i++) {
				String toWrite = Integer.toString(g.level.tiles[i].getTileID());
				bufferedWriter.write(toWrite);
				bufferedWriter.newLine();
			}

			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file " + writePath);
		}

		long afterTime = System.currentTimeMillis();
		long totalTime = afterTime - beforeTime;
		System.out.println("World saved in " + totalTime + " ms");
	}
}