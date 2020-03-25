package hu.grdg.projlab.model;

import hu.grdg.projlab.Skeleton;
import hu.grdg.projlab.SkeletonTester;

/**
 * An IceTile that will kill all players if a certain limit is reached
 */
public class UnstableIceTile extends IceTile{


    /**
     * How many people can stand without dieing
     */
    private int limit;

    /**
     * How many players are standing on it currently
     * It will be replaced with a list after testing
     */
    private int playerCount;

    /**
     * Creates a new Unstable Ice Tile
     * It asks the user for the limit and current player count
     * @author Barrow099
     */
    public UnstableIceTile() {
        this.limit = SkeletonTester.askNumber("UnstableIceTile limit:");
        this.playerCount = SkeletonTester.askNumber("UnstableIceTile player count:");
    }

    /**
     * Accepts a stepping player
     * Checks if its under the limit, if not, kill the Player
     * @param player The stepping player
     */
    @Override
    public void acceptPlayer(Player player) {
        //Start method call, the first parameter is the current instance, followed by all method parameters
        SkeletonTester.call(this, player);

        if(playerCount + 1 > limit) {
            player.die();
        }else {
            player.setCurrentTile(this);
            playerCount++;
        }

        //Return from a method call
        SkeletonTester.creturn();
    }
}
