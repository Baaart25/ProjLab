package hu.grdg.projlab.model;

import hu.grdg.projlab.Skeleton;
import hu.grdg.projlab.SkeletonTester;

import java.util.HashMap;

/**
 * Base class for map tiles
 */
public abstract class Tile {
    private HashMap<Direction, Tile> neighbours;
    private Item frozenItem = null;

    public Tile(){
        neighbours=new HashMap<>();
    }

    /**
     * A player moves into this tile, the players current tile will be updated
     * @param player The new player
     */
    public void acceptPlayer(Player player) {
        SkeletonTester.call(this, player);
        player.setCurrentTile(this);
        SkeletonTester.creturn();
    }

    public void buildIgloo() {
        SkeletonTester.call(this, null);

        SkeletonTester.creturn();
    }

    public abstract int scanLimit();

    public void setNeighbour(Tile nb, Direction d){
        SkeletonTester.call(this, nb, d);
        neighbours.put(d, nb);
        SkeletonTester.creturn();
    }

    public Tile getNeighbour(Direction d){
        SkeletonTester.call(this, d);

        SkeletonTester.creturn(neighbours.get(d));
        return neighbours.get(d);

    }

    /**
     * Sets the frozen item to the tile
     * @param it The new frozen item
     */
    public void setFrozenItem(Item it) {
        SkeletonTester.call(this, it);

        frozenItem = it;

        SkeletonTester.creturn();
    }

    /**
     * Returns the frozen item from the tile
     * @return The frozen item or null if empty
     */
    public Item getFrozenItem() {
        SkeletonTester.call(this);
        SkeletonTester.creturn(frozenItem);
        return frozenItem;
    }
}
