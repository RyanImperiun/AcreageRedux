package Level;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Core.Game;
import Core.GameResourceLoader;
import Core.Launcher;
import Entities.Player;

public class StoneTile extends Tile {
	public StoneTile(int x, int y, Game game) {
		tileID = GameResourceLoader.Stone;
		this.game = game;
		this.setoX(x);
		this.setoY(y);

		setTileBoundaries(x, y, getTileSize(), getTileSize());
		setTileID(2);

		generateRockOre();
	}

	private void generateRockOre() {
		switch (new Random().nextInt(2)) { // Switch a random int, 0 or 1 for a Rock
		case 0:
			hasRock = true;
			break;
		case 1:
			hasRock = false;
			break;
		}

		if (hasRock) {
			switch (new Random().nextInt(2)) { // Switch a random int, 0 or 1 for Ore
			case 0:
				hasOre = true;
				break;
			case 1:
				hasOre = false;
				break;
			}
		}

		if (hasOre) {
			oreAmount = new Random().nextInt(5);
		}
	} // End Generate RockOre

	public void tick(Game game) {
		setX(getoX() - game.xOffset); // Current x after movement, Offset, etc
		setY(getoY() - game.yOffset); // Current y after movement, Offset, etc
		getTileBoundaries().setBounds(getX(), getY(), getTileSize(), getTileSize());
		setTilePos();

		if (oreAmount <= 0) {
			hasOre = false;
		}

		// If tile contains mouse
		if (getTileBoundaries().contains(game.mouseP)) {
			setContainsMouse(true);
		} else {
			setContainsMouse(false);
		}

		Visibility();
	}

	@Override
	public void render(Graphics g) {
		if (hasRock && !hasOre) {
			g.drawImage(game.getRes().tiles[GameResourceLoader.Rock], getX(), getY(), game);
		} else if (hasRock && hasOre) {
			g.drawImage(game.getRes().tiles[GameResourceLoader.Metal], getX(), getY(), game);
		} else {
			g.drawImage(game.getRes().tiles[GameResourceLoader.Stone], getX(), getY(), game);
		}

		if (game.showGrid) { // If the player wants to draw grids
			g.setColor(Color.WHITE); // White color
			g.drawRect(getX(), getY(), getTileSize() - 1, getTileSize() - 1); // Draw a border around tile
		}

		if (isContainsMouse()) { // If it is allowed to show borders
			g.setColor(Color.BLACK); // Black color
			g.drawRect(getX(), getY(), getTileSize() - 1, getTileSize() - 1); // Draw a border around image
		}
	}

	@Override
	public void onLeftClick() {
		if (hasRock && Player.toolSelected == Player.Pickaxe && !hasOre) {
			game.getInv().addResource(game.getInv().stoneID, 1);
			hasRock = false;
		} else if (hasOre && Player.toolSelected == Player.Pickaxe) {
			game.getInv().addResource(game.getInv().oreID, 1);
			oreAmount--;
		}
	}
	
	@Override
	public void onRightClick() {
		if(Player.toolSelected == Player.Pickaxe){
			if(!hasRock)
				editTile(LevelClass.tiles, (tileY * Launcher.worldSize) + tileX, new DirtTile(oX, oY, game));
		}
	}
}