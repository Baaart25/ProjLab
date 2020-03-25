package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * Base class for map tiles
 */
public abstract class Tile {
    public void acceptPlayer(Player player) {
        SkeletonTester.call(this, player);
        player.currentTile = this;
        SkeletonTester.creturn();
    }

    public void buildIgloo() {
        SkeletonTester.call(this, null);

        SkeletonTester.creturn();
    }

}
