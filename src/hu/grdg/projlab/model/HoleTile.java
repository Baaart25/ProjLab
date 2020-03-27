package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * A Tile that represents a hole between the ice, when a Player
 * steps on it, they will fall in water
 * Igloo building is not possible in this tile
 */
public class HoleTile extends Tile{
    @Override
    public int scanLimit() {
        SkeletonTester.call(this, null);
        SkeletonTester.creturn(0);
        return 0;
    }

    @Override
    public void acceptPlayer(Player player) {
        SkeletonTester.call(this, player);

        player.setCurrentTile(this);
        player.fallInWater();

        SkeletonTester.creturn();
    }
}
