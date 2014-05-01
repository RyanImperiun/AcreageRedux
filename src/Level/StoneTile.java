package Level;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Core.Game;
import Core.GameResourceLoader;

public class StoneTile extends Tile {

	public StoneTile(int x, int y, Game game) {
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
			setHasRock(true);
			break;
		case 1:
			setHasRock(false);
			break;
		}

		if (isHasRock()) {
			switch (new Random().nextInt(2)) { // Switch a random int, 0 or 1 for Ore
			case 0:
				setHasOre(true);
				break;
			case 1:
				setHasOre(false);
				break;
			}
		}

		if (isHasOre()) {
			setOreAmount(new Random().nextInt(5));
		}
	} // End Generate RockOre

	public void tick(Game game) {
		this.game = game;

		setX(getoX() - game.xOffset); // Current x after movement, Offset, etc
		setY(getoY() - game.yOffset); // Current y after movement, Offset, etc
		getTileBoundaries().setBounds(getX(), getY(), getTileSize(), getTileSize());
		setTilePos();

		if (getOreAmount() <= 0) {
			setHasOre(false);
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
		if (!isHasRock() && !isHasWall()) {
			g.drawImage(game.getRes().tiles[getTileID()], getX(), getY(), game);
		} else if (isHasRock() && !isHasOre()) {
			g.drawImage(game.getRes().tiles[GameResourceLoader.Rock], getX(), getY(), game);
		} else if (isHasRock() && isHasOre()) {
			g.drawImage(game.getRes().tiles[GameResourceLoader.Metal], getX(), getY(), game);
		} else if (isHasWall()) {
			g.drawImage(game.getRes().tiles[GameResourceLoader.logWall], getX(), getY(), game);
		}

		if (game.showGrid) { // If the player wants to draw grids
			g.setColor(Color.WHITE); // White color
			g.drawRect(getX(), getY(), getTileSize() - 1, getTileSize() - 1); // Draw a border around tile
		}

		if (isContainsMouse() && isCanAffect()) { // If it is allowed to show borders
			g.setColor(Color.BLACK); // Black color
			g.drawRect(getX(), getY(), getTileSize() - 1, getTileSize() - 1); // Draw a border around image
		}
	}
}