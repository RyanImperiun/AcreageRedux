package Level;

import java.awt.Graphics;
import java.awt.Rectangle;

import Core.Game;

public abstract class Tile {
	private Rectangle tileBoundaries;

	Game game;
	private int x, y, tileX, tileY;
	private int tileID;
	private int oX, oY; // Original x,y coordinates when created
	private final int tileSize = 32; // Size of tiles

	private boolean canAffect = true;

	// Plowed tile
	boolean hasPlants = false;
	private int plantGrowth = 0;
	private int growthTime = 4;

	// Used for other images
	private boolean hasRock = false; // Stone tile
	private boolean hasOre = false; // Stone tile
	private int oreAmount = 0; // Amount of Ore, if any within Vein
	private boolean hasTree = false; // Grass tile
	private boolean hasWall = false;

	// Misc
	public boolean isVisible; // If the tile is within the JFrame area
	protected boolean containsMouse; // If the tile has the mouse in it

	public abstract void tick(Game game);

	public abstract void render(Graphics g);

	protected void Visibility() { // Check if the tile is within the JFrame area
		if (getX() >= 0 - 32 && getX() <= game.getWidth() + 32 && getY() >= 0 - 32 && getY() <= game.getHeight() + 32) {
			isVisible = true;
		} else {
			isVisible = false;
		}
	}
	
	protected void setTilePos(){
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

	public boolean isCanAffect() {
		return canAffect;
	}

	public void setCanAffect(boolean canAffect) {
		this.canAffect = canAffect;
	}

	public int getPlantGrowth() {
		return plantGrowth;
	}

	public void setPlantGrowth(int plantGrowth) {
		this.plantGrowth = plantGrowth;
	}

	public int getGrowthTime() {
		return growthTime;
	}

	public void setGrowthTime(int growthTime) {
		this.growthTime = growthTime;
	}

	public boolean isHasRock() {
		return hasRock;
	}

	public void setHasRock(boolean hasRock) {
		this.hasRock = hasRock;
	}

	public boolean isHasOre() {
		return hasOre;
	}

	public void setHasOre(boolean hasOre) {
		this.hasOre = hasOre;
	}

	public int getOreAmount() {
		return oreAmount;
	}

	public void setOreAmount(int oreAmount) {
		this.oreAmount = oreAmount;
	}

	public boolean isHasTree() {
		return hasTree;
	}

	public void setHasTree(boolean hasTree) {
		this.hasTree = hasTree;
	}

	public boolean isHasWall() {
		return hasWall;
	}

	public void setHasWall(boolean hasWall) {
		this.hasWall = hasWall;
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