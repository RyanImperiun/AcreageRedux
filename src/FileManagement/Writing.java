package FileManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Core.Game;

public class Writing {
	Game	g;

	public Writing(String filePath, Game game) {
		g = game;

		// The name of the file to open. --- C:/Users/Ryan/Desktop/temp.txt --
		long beforeTime = System.currentTimeMillis();

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(filePath);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < g.level.tiles.length; i++) {
				String toWrite = Integer.toString(g.level.tiles[i].getTileID());
				bufferedWriter.write(toWrite);
				bufferedWriter.newLine();
			}

			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file " + filePath);
		}

		long afterTime = System.currentTimeMillis();
		long totalTime = afterTime - beforeTime;
		System.out.println("World saved in " + totalTime + " ms");
	}
}