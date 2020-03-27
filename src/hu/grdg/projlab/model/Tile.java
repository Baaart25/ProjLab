package hu.grdg.projlab.model;

import hu.grdg.projlab.Skeleton;
import hu.grdg.projlab.SkeletonTester;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Base class for map tiles
 */
public abstract class Tile {
    private HashMap<Direction, Tile> neighbours;
    private Item frozenItem = null;
    private int snowLayers;
    private ArrayList<Player> players;
    private boolean hasIgloo;

    public Tile(){
        players = new ArrayList<>();
        neighbours=new HashMap<>();
    }

    /**
     * A player moves into this tile, the players current tile will be updated
     * @param player The new player
     */
    public void acceptPlayer(Player player) {
        SkeletonTester.call(this, player);
        player.setCurrentTile(this);
        players.add(player);
        SkeletonTester.creturn();
    }

    public abstract boolean removeSnowLayer(int n);
    public boolean buildIgloo() {
        SkeletonTester.call(this);
        if(hasIgloo){
            SkeletonTester.creturn(false);
            return false;
        }else {
            hasIgloo = true;
            SkeletonTester.creturn(true);
            return true;
        }
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

    /**
     *
     * @param n Add n snowlayers to the tile
     * @author Dorina
     */
    public void addSnowLayer(int n) {
        SkeletonTester.call(this);
        snowLayers += n;
        SkeletonTester.creturn();
    }

    /**
     * Damage all the players on the tile
     * @author Dorina
     */
    public void stormDamage() {
        SkeletonTester.call(this);
        if(!hasIgloo){
            for (Player p: players) {
                p.damage(1);
            }
        }
        SkeletonTester.creturn();
    }

    /**
     * Sets the number of snow layers on this tile
     * @param snowLayers The number of snow layers
     * @author Barrow099
     */
    public void setSnowLayers(int snowLayers) {
        SkeletonTester.call(this,snowLayers);
        this.snowLayers = snowLayers;
        SkeletonTester.creturn();
    }

    /**
     * The parameter Player is trying to pick up the item from the tile
     * If there is at least one snow layer on us, this will fail
     * @param p The player
     */
    public boolean pickupItem(Player p) {
        SkeletonTester.call(this, p);

        //We have snow on us
        if(snowLayers > 0){
            SkeletonTester.creturn(false);
            return false;
        }

        //We dont have an item :(
        if(frozenItem == null) {
            SkeletonTester.creturn(false);
            return false;
        }

        if(frozenItem.pickedUp(p)) {
            SkeletonTester.creturn(true);
            return true;
        }else {
            SkeletonTester.creturn(false);
            return false;
        }
    }
}
