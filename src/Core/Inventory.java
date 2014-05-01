package Core;

import java.awt.Color;
import java.awt.Graphics;

public class Inventory {
	Game game;
	public byte itemSelected = 0;

	public byte seedID = 0;
	public byte wheatID = 1;
	public byte stoneID = 2;
	public byte stickID = 3;
	public byte lumberID = 4;
	public byte oreID = 5;

	public short resourceAmounts[] = { 100, 0, 0, 0, 0, 0 };
	String resourceNames[] = { "Seed", "Wheat", "Stone", "Sticks", "Lumber", "Ore" };

	public Inventory(Game game) {
		this.game = game;
	}

	public void tick() {
	}

	public void addResource(int resourceID, int amount) {
		resourceAmounts[resourceID] += amount;
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(game.getWidth() - 100, game.getHeight() - 123, 100, 135);

		g.setColor(Color.BLACK);
		for (byte i = 0; i < resourceNames.length; i++) {
			g.drawString(resourceNames[i] + ": " + resourceAmounts[i], game.getWidth() - 100, i * 10 + game.getHeight() - 134 + 21);
		}

		g.drawRect(game.getWidth() - 100, itemSelected * 10 + game.getHeight() - 134 + 11, 100, 10);
	}
}