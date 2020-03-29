package hu.grdg.projlab.model;

import hu.grdg.projlab.Skeleton;
import hu.grdg.projlab.SkeletonTester;

import java.util.ArrayList;

/**
 * Base class for user controller
 */
public abstract class Player {
    protected Tile currentTile;
    protected boolean isInWater;
    protected Controller controller;
    protected int currentTemp;
    private ArrayList<Item> inventory;
    protected int maxTemp;

    public abstract void specialAbility();

    /**
     * Kills the player and ends the game
     * @author Barrow099
     */
    public void die() {
        SkeletonTester.call(this);

        controller.endGame(false);

        SkeletonTester.creturn();
    }
    public Tile getCurrentTile(){
        return this.currentTile;
    }
    public void setCurrentTile(Tile newTile) {
        SkeletonTester.call(this, newTile);

        this.currentTile = newTile;

        SkeletonTester.creturn();
    }

    /**
     * Tries to unfreeze the item on the current tile
     * @return If the unfreeze was successful, used for work counting
     * @author Barrow099
     */
    public boolean unfreezeItem() {
        SkeletonTester.call(this);

        Item sh = currentTile.getFrozenItem();

        if(sh != null) {
            boolean succ =  sh.unfreeze();
            SkeletonTester.creturn(succ);
            return succ;
        }

        SkeletonTester.creturn(false);
        return false;
    }

    /**
     * sets the isInWater boolean variable to true;
     */
    public void fallInWater() {
        SkeletonTester.call(this);

        isInWater=true;

        SkeletonTester.creturn();
    }

    /**
     * moves the player to the given tile
     * @param tile
     */
    public void move(Tile tile){
        SkeletonTester.call(this,tile);

        if(!isInWater){
            currentTile.remove(this);
            tile.acceptPlayer(this);
        }

        SkeletonTester.creturn();
    }


    /**
     * the player steps to the given tile
     * @param tile the tile to step
     * @return if the player is on holetile
     */
    public boolean savedFromWater(Tile tile){
        SkeletonTester.call(this,tile);

        boolean res = false;
        if(isInWater){
            res = true;
            isInWater = false;
            move(tile);
        }

        SkeletonTester.creturn(res);
        return res;
    }



    /**
     * saves players from the neighbouring tiles
     * @return boolean the succesfullnes of saving players
     */
    public boolean savingPlayers(){
        SkeletonTester.call(this);

        boolean res = false;
        Tile tile = currentTile.getNeighbour(Direction.EAST);
        ArrayList<Player> p = new ArrayList<Player>(tile.getPlayers());
        for (Player player : p) {
            if (player.savedFromWater(currentTile))
                res = true;
        }

        SkeletonTester.creturn(res);
        return res;
    }
    /**
     * Sets the game controller reference of the player
     * @param controller The game controller instance
     * @author Baaart35
     */
    public void setController(Controller controller){
        SkeletonTester.call(this,controller);

        this.controller = controller;

        SkeletonTester.creturn();
    }

    /**
     *
     * @param i Decrease the player's temperature with i
     * @author Dorina
     */
    public void damage(int i) {
        SkeletonTester.call(this, i);
        currentTemp-=i;
        if(currentTemp == 0){
            controller.endGame(false);
        }
        SkeletonTester.creturn();
    }

    /**
     * Increases the player's temperature by 1 and removes food from inventory if max temperature not reached
     * @param food The item to be deleted from inventory
     * @return succesfulness of action
     * @author Geri
     */
    public boolean eat(Item food){
        SkeletonTester.call(this, food);
        if (currentTemp == maxTemp){
            SkeletonTester.creturn(false);
            return false;
        } else {
            currentTemp++;
            inventory.remove(food);
            SkeletonTester.creturn(true);
            return true;
        }
    }

    /**
     * Sets the player's isInWater status
     * @param isInWater The new value
     * @author Barrow099
     */
    public void setIsInWater(boolean isInWater) {
        SkeletonTester.call(this, isInWater);
        this.isInWater = isInWater;
        SkeletonTester.creturn();
    }

    /**
     * Enable the player to step out of a Hole if called.
     * This is done by setting the isInWater property to false
     * @return If the player was in water
     */
    public boolean surviveWater() {
        SkeletonTester.call(this);
        SkeletonTester.creturn(isInWater);
        boolean wasInWater = isInWater;
        isInWater = false;
        return wasInWater;
    }

    //TODO Máténak vagy valakinek ezt megnézni/jóváhagyni

    /**
     * Adds an item to the inventory
     * @param i Item to be added to the inventory
     * @author Geri
     */
    public void addItem(Item i){
        SkeletonTester.call(this, i);
        inventory.add(i);
        SkeletonTester.creturn();
    }

    /**
     * Sets reference to an ArrayList
     * @author Geri
     */
    public Player(int maxTemp){
        inventory = new ArrayList<Item>();
        this.maxTemp = maxTemp;
    }
}
