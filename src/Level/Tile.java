package Level;

import java.awt.Graphics;
import java.awt.Rectangle;

import Core.Game;

public abstract class Tile {
	/*
	 * Math to find tile in a single-dimensional array
	 * 
	 * tileArray[tileY*worldWidth] + tileX
	 */

	private Rectangle	tileBoundaries;

	Game				game;
	protected int		x, y, tileX, tileY;
	protected int		tileID;
	protected int		oX, oY;				// Original x,y coordinates when created
	protected final int	tileSize	= 32;		// Size of tiles

	public boolean		hasTree		= false;	// Grass tile
	public boolean		hasRock		= false;	// Stone tile
	public boolean		hasOre		= false;	// Stone tile
	protected int		oreAmount	= 0;		// Amount of Ore, if any within Vein

	// Misc
	public boolean		isVisible;				// If the tile is within the JFrame area
	protected boolean	containsMouse;			// If the tile has the mouse in it

	public abstract void tick(Game game);

	public abstract void render(Graphics g);

	public abstract void onLeftClick();
	public abstract void onRightClick();

	protected void Visibility() { // Check if the tile is within the JFrame area
		if (getX() >= 0 - 32 && getX() <= game.getWidth() + 32 && getY() >= 0 - 32 && getY() <= game.getHeight() + 32) {
			isVisible = true;
		} else {
			isVisible = false;
		}
	}

	public void editTile(Tile[] tileArray, int tilePositionInArray, Tile newTile) {
		tileArray[tilePositionInArray] = newTile;
	}

	protected void setTilePos() {
		setTileX(x / 32);
		setTileY(y / 32);
	}

	// Getters and Setters

	public int getTileID() {
		return tileID;
	}

	public void setTileID(int tileID) {
		this.tileID = tileID;
	}

	public int getoX() {
		return oX;
	}

	public void setoX(int oX) {
		this.oX = oX;
	}

	public int getoY() {
		return oY;
	}

	public void setoY(int oY) {
		this.oY = oY;
	}

	public boolean isContainsMouse() {
		return containsMouse;
	}

	public void setContainsMouse(boolean containsMouse) {
		this.containsMouse = containsMouse;
	}

	public int getTileSize() {
		return tileSize;
	}

	public Rectangle getTileBoundaries() {
		return tileBoundaries;
	}

	public void setTileBoundaries(int x, int y, int width, int height) {
		this.tileBoundaries = new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}
}