package Level;

import java.awt.Graphics;
import java.util.Random;

import Core.Game;
import Core.GameResourceLoader;
import Entities.Player;

public class LevelClass {
	Game		game;
	public Tile	tiles[];

	public LevelClass(Game game) {
		this.game = game;
		tiles = new Tile[game.worldWidth * game.worldHeight];
	} // End constructor

	public void generateLevel(int[] tileIDs) {
		int x = 0, y = 0;

		for (int i = 0; i < 350 * 350; i++) {
			switch (tileIDs[i]) { // Random int from 0 - 2, use as tileID
			case 0:
				tiles[i] = new DirtTile(x * 32, y * 32, game); // Place dirt
				break;
			case 1:
				tiles[i] = new StoneTile(x * 32, y * 32, game); // Place stone
				break;
			case 2:
				tiles[i] = new GrassTile(x * 32, y * 32, game); // Place grass
				break;
			default:
				tiles[i] = new GrassTile(x * 32, y * 32, game); // Place grass
				break;
			}
			x++;
			if (x == game.worldWidth) {
				x = 0;
				y++;
			}
		}
	}

	public void generateLevel() {
		// Tile generator

		long beginTime = System.currentTimeMillis();

		int x = 0, y = 0;

		for (int i = 0; i < game.worldWidth * game.worldHeight; i++) {
			switch (new Random().nextInt(3)) { // Random int from 0 - 2, use as tileID
			case 0:
				tiles[i] = new DirtTile(x * 32, y * 32, game); // Place dirt
				break;
			case 1:
				tiles[i] = new StoneTile(x * 32, y * 32, game); // Place stone
				break;
			case 2:
				tiles[i] = new GrassTile(x * 32, y * 32, game); // Place grass
				break;
			default:
				tiles[i] = new GrassTile(x * 32, y * 32, game); // Place grass
				break;
			}
			x++;
			if (x == game.worldWidth) {
				x = 0;
				y++;
			}
		}

		long endTime = System.currentTimeMillis();
		long resultTime = endTime - beginTime;
		System.out.println("It took " + resultTime + "ms to generate the level!");
	} // End generateLevel

	public void updateLevel(Game game) {
		this.game = game;

		for (Tile t : tiles) {
			t.tick(game);

			if (game.getInput().rightButton && t.isContainsMouse())
				rightClick(t);

			if (game.getInput().leftButton && t.isContainsMouse())
				leftClick(t);
		}
	} // End update

	private void rightClick(Tile tile) { // if Right click
		switch (Player.toolSelected) {
		case 1: // Axe
			if (tile.isHasWall()) {
				tile.setHasWall(false);
			}
			break;
		case 2: // Pickaxe
			if (tile.getTileID() == GameResourceLoader.Stone && !tile.isHasRock()) {
				tile.setTileID(GameResourceLoader.Dirt);
				tile = new DirtTile(tile.getTileX(), tile.getTileY(), game);
			}
			break;
		case 3: // Hoe
			if (!tile.isHasTree()) {
				if (tile.getTileID() == GameResourceLoader.Grass || tile.getTileID() == GameResourceLoader.Dirt) {
					tile.setTileID(GameResourceLoader.Plowed);
					tile = new PlowedTile(tile.getTileX(), tile.getTileY(), game);
				}
			}
			break;
		case 4: // Shovel
			if (!tile.isHasTree() && tile.getTileID() == GameResourceLoader.Grass) {
				tile.setTileID(GameResourceLoader.Dirt);
				tile = new DirtTile(tile.getTileX(), tile.getTileY(), game);
			}
			break;
		case 5: // Hand
			if (game.getInv().itemSelected == game.getInv().stoneID && game.getInv().resourceAmounts[game.getInv().stoneID] > 0) {
				if (!tile.isHasRock() && !tile.isHasTree()) { // If the tile is not stone and does not have a tree
					game.getInv().addResource(game.getInv().stoneID, -1);
					tile.setTileID(GameResourceLoader.Stone);
					tile = new RoadTile(tile.getTileX(), tile.getTileY(), game);
					tile.setHasRock(false);
					tile.setHasOre(false);
				}
			}
			if (game.getInv().itemSelected == game.getInv().lumberID && game.getInv().resourceAmounts[game.getInv().lumberID] > 0 && !tile.isHasWall()) {
				if (!tile.isHasRock() && !tile.isHasTree() && tile.getTileID() != GameResourceLoader.Plowed) { // If the tile is not stone and does not have a tree
					tile.setHasWall(true);
					game.getInv().addResource(game.getInv().lumberID, -1);
				}
			}

		}
		game.getInput().rightButton = false;
	}

	private void leftClick(Tile tile) { // If Left click
		switch (tile.getTileID()) {
		case 0: // Dirt
			break;
		case 1: // Plowed
			if (!tile.hasPlants) {
				tile.hasPlants = true;
				game.getInv().resourceAmounts[game.getInv().seedID] -= 1;
			} else if (tile.hasPlants && tile.getPlantGrowth() == tile.getGrowthTime()) {
				game.getInv().addResource(game.getInv().wheatID, 1);
				tile.hasPlants = false;
				tile.setPlantGrowth(0);
			}
			break;
		case 2: // Stone
			if (tile.isHasRock() && Player.toolSelected == Player.Pickaxe && !tile.isHasOre()) {
				game.getInv().addResource(game.getInv().stoneID, 1);
				tile.setHasRock(false);
			} else if (tile.isHasOre() && Player.toolSelected == Player.Pickaxe) {
				game.getInv().addResource(game.getInv().oreID, 1);
				tile.setOreAmount(tile.getOreAmount() - 1);
			}
			break;
		case 3: // Grass
			if (tile.isHasTree() && Player.toolSelected != Player.Axe) {
				game.getInv().addResource(game.getInv().stickID, 1);
			} else if (tile.isHasTree() && Player.toolSelected == Player.Axe) {
				game.getInv().addResource(game.getInv().lumberID, 1);
				tile.setHasTree(false);
			}
		} // End switch
		game.getInput().leftButton = false;
	} // End left-click

	public void renderLevel(Graphics g) {
		// Tile loops

		for (Tile t : tiles) {
			if (t.isVisible)
				t.render(g);
		}
	} // End render
}