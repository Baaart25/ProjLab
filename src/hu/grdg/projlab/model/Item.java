package hu.grdg.projlab.model;

public abstract class Item {
    protected boolean isFrozen = true;
    protected Player owner;



    //----------WARNING-------------
    //NOT IN DOCS
    //TODO Add to docs
    //FIXME
    public boolean isFrozen() {
        return false;
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

    //---------------WARNING-----------------
    //NOT IN DOCS
    //TODO Add docs
    /**
     * Sets the isFrozen property. Used for saved game loading
     * @param isForzen The new state
     */
    public void setIsFrozen(boolean isForzen) {
        this.isFrozen = isForzen;
    }

    //----------NOT IN FUCKIN DOCS-----------------
    /**
     * Sets the owner of the item
     * @param player The new owner
     */
    public void setFuckinOwner(Player player) {
        this.owner = player;
    }
}
