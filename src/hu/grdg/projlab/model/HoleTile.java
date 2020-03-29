package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * A Tile that represents a hole between the ice, when a Player
 * steps on it, they will fall in water
 * Igloo building is not possible in this tile
 */
public class HoleTile extends Tile{
    /**
     * Scan the limit of the HoleTile
     * @return return the limit which is 0
     * @author Dorina
     */
    @Override
    public int scanLimit() {
        SkeletonTester.call(this);
        SkeletonTester.creturn(0);
        return 0;
    }

    /**
     * accepts the player
     * @param player The new player
     */
    @Override
    public void acceptPlayer(Player player) {
        SkeletonTester.call(this, player);

        player.fallInWater();
        player.setCurrentTile(this);
        players.add(player);


        SkeletonTester.creturn();
    }

    @Override
    public boolean removeSnowLayer(int n) {
        return false;
    }
}
