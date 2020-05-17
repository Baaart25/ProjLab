package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.EntityRenderer;
import hu.grdg.projlab.gui.ItemRenderer;

/**
 * Absztrakt osztály, a player-ek tudják őket tárolni és használni
 */
public abstract class Item {
    //ismeri, hogy be van e fagyva vagy nem
    protected boolean isFrozen = true;
    //ismeri a tulajdonosát
    protected Player owner;

    /**
     * Return if the Item is frozen
     * @return true if the Item is frozen
     */
    public boolean isFrozen() {
        return isFrozen;
    }

    /**
     * Adds itself to the player inventory if isFrozen==false
     * @param byPlayer the player who wants to pick it up
     * @return false is isFrozen==true, true if isFrozen==false
     * @author Boti
     */
    public boolean pickedUp(Player byPlayer){
        if(isFrozen){
            return false;
        }
        owner = byPlayer;
        byPlayer.addItem(this);
        return true;
    }

    /**
     * set the isFrozen field to false if it was true
     * @return true if isFrozen was true
     * @author Boti
     */
    public boolean unfreeze(){
        if(isFrozen){
            isFrozen = false;
            return true;
        }
        return false;
    }

    /**
     * abstract useItem function, the derived classes will implement it
     * @return true if it was succesful
     * @author Boti
     */
    public abstract boolean useItem();

    /**
     * returns the owner of the item
     * @return the owner
     * @author Boti
     */
    public Player getOwner(){
        return owner;
    }

    /**
     * Returns the item renderer for this item
     * @return The items renderer
     */
    public abstract ItemRenderer getRenderer();

}
