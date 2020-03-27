package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

import java.util.ArrayList;

/**
 * An environmental event that damages players in a specific area and adds layers
 * of snow to the target Tiles
 */
public class SnowStorm implements EnvironmentEvent{
    /**
     *
     * @param lvl The level where the storm should damage
     * @author Dorina
     */
    @Override
    public void doEvent(Level lvl) {
        SkeletonTester.call(this, lvl);
        ArrayList<Tile> tiles = lvl.getTiles();
        Tile tile = tiles.get(0);
        SkeletonTester.addNamedReference(tile, "tile");
        tile.addSnowLayer(2);
        tile.stormDamage();
        SkeletonTester.creturn();
    }
}
