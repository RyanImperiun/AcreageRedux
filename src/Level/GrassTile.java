package Level;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Core.Game;
import Core.GameResourceLoader;
import Entities.Player;

public class GrassTile extends Tile {

	public GrassTile(int x, int y, Game game) {
		this.game = game;
		setoX(x);
		setoY(y);

		setTileBoundaries(x, y, getTileSize(), getTileSize());
		setTileID(3);

		switch (new Random().nextInt(2)) {
		case 0:
			setHasTree(true);
			break;
		case 1:
			setHasTree(false);
			break;
		}
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
		g.drawImage(game.getRes().tiles[GameResourceLoader.Grass], getX(), getY(), game);
		if (isHasTree()) {
			g.drawImage(game.getRes().tiles[GameResourceLoader.Tree], getX(), getY(), game);
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

	@Override
	public void onLeftClick() {
		if (isHasTree() && Player.toolSelected == Player.Hand) {
			game.getInv().addResource(game.getInv().stickID, 1);
		} else if (isHasTree() && Player.toolSelected == Player.Axe) {
			game.getInv().addResource(game.getInv().lumberID, 1);
			setHasTree(false);
		}
	}
}