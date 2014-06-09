package Level;

import java.awt.Color;
import java.awt.Graphics;

import Core.Game;

public class PlowedTile extends Tile {

	boolean	hasPlants		= false;
	int		plantGrowth		= 0;
	int		growthTime		= 4;
	int		plantGrowTicks	= 0;

	public PlowedTile(int x, int y, Game game) {
		this.game = game;
		this.setoX(x);
		this.setoY(y);
		setTileID(1);

		setTileBoundaries(x, y, getTileSize(), getTileSize());
	}

	public void tick(Game game) {
		setX(getoX() - game.xOffset); // Current x after movement, Offset, etc
		setY(getoY() - game.yOffset); // Current y after movement, Offset, etc
		getTileBoundaries().setBounds(getX(), getY(), getTileSize(), getTileSize());
		setTilePos();

		plantGrowth();

		// If tile contains mouse
		if (getTileBoundaries().contains(game.mouseP)) {
			setContainsMouse(true);
		} else {
			setContainsMouse(false);
		}

		Visibility();
	}

	private void plantGrowth() {
		if (hasPlants && plantGrowth < growthTime) {
			if (plantGrowTicks / 120 == 1) {
				plantGrowth++;
				System.out.println("Plant Ticked");
			}
			plantGrowTicks++;
		} else if (!hasPlants) {
			plantGrowth = 0;
			plantGrowTicks = 0;
		}
	}

	public void render(Graphics g) {
		if (hasPlants) {
			g.drawImage(game.getRes().plants[plantGrowth], getX(), getY(), game);
		} else if (!hasPlants) {
			g.drawImage(game.getRes().tiles[getTileID()], getX(), getY(), game);
		}

		if (game.showGrid) { // If the player wants to draw grids
			g.setColor(Color.WHITE); // White color
			g.drawRect(getX(), getY(), getTileSize() - 1, getTileSize() - 1); // Draw a border around tile
		}
	}

	@Override
	public void onLeftClick() {
		if (!hasPlants) {
			hasPlants = true;
			game.getInv().resourceAmounts[game.getInv().seedID] -= 1;
		} else if (hasPlants && plantGrowth == growthTime) {
			game.getInv().addResource(game.getInv().wheatID, 1);
			hasPlants = false;
			plantGrowth = 0;
		}
	}
}