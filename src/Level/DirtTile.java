package Level;

import java.awt.Color;
import java.awt.Graphics;

import Core.Game;
import Core.GameResourceLoader;
import Core.Launcher;
import Entities.Player;

public class DirtTile extends Tile {

	public DirtTile(int x, int y, Game game) {
		tileID = GameResourceLoader.Dirt;
		this.game = game;
		this.setoX(x);
		this.setoY(y);

		setTileBoundaries(x, y, getTileSize(), getTileSize());
		setTileID(0);
	}

	public void tick(Game game) {
		setX(getoX() - game.xOffset); // Current x after movement, Offset, etc
		setY(getoY() - game.yOffset); // Current y after movement, Offset, etc
		getTileBoundaries().setBounds(getX(), getY(), getTileSize(), getTileSize());
		setTilePos();

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
		g.drawImage(game.getRes().tiles[getTileID()], getX(), getY(), game);

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
	}

	@Override
	public void onRightClick() {
		if (Player.toolSelected == Player.Hoe)
			editTile(LevelClass.tiles, (tileY * Launcher.worldSize) + tileX, new PlowedTile(oX, oY, game));
	}
}