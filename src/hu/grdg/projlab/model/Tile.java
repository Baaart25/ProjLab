package hu.grdg.projlab.model;

import hu.grdg.projlab.Skeleton;

/**
 * Base class for map tiles
 */
public abstract class Tile {
    public void acceptPlayer(Player player) {
        Skeleton.call(this, player);
        player.currentTile = this;
        Skeleton.creturn();
    }

    public void buildIgloo() {
        Skeleton.call(this, null);

        Skeleton.creturn();
    }

}
