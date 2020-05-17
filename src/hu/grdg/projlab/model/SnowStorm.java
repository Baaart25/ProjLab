package hu.grdg.projlab.model;

import java.util.ArrayList;
import java.util.Random;

public class SnowStorm implements TurnBasedEvent{
    private static SnowStorm instance = null;
    public static SnowStorm getInstance() {
        return instance;
    }

    public SnowStorm() {
        instance = this;
    }


    /**
     * Choose the tiles where the SnowStorm happens and do the storm
     * @param lvl the level of the game
     * @param fullTurn true if the storm has to be happen
     * @author Dorina
     */
    @Override
    public void doEvent(Level lvl, boolean fullTurn) {
        if(fullTurn) {
            ArrayList<Tile> tiles = lvl.getTiles();
            Random rnd = new Random();
            int x = rnd.nextInt(7), y = rnd.nextInt(7);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    doStorm(tiles.get(x + i + (y + j) * 10), rnd.nextInt(3));
                }
            }
        }
    }

    /**
     * Do the storm on the tile. It adds the snow and damages the players.
     * @param tile Target tile
     * @param amount Amount of snow to add
     * @author Barrow099
     */
    public void doStorm(Tile tile, int amount) {
        tile.addSnowLayer(amount);
        tile.stormDamage();
    }
}
