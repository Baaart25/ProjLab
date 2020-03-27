package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * Base class for player usable items
 */
public abstract class Item {
    protected Player owner;
    private boolean isFrozen = true;

    /**
     * Sets the frozen state to false if it was frozen
     * @return True if it was frozen, else false
     */
    public boolean unfreeze() {
        SkeletonTester.call(this);

        if(!isFrozen) {
            SkeletonTester.creturn(false);
            return false;
        }
        isFrozen = false;

        SkeletonTester.creturn(true);
        return true;
    }

    /**
     * Set the owner of the Item
     * @param p The owner player
     * @author Geri
     */
    public void setOwner(Player p){
        owner = p;
    }

    /**
     *
     * @return Succesfullness of the action
     * @author Geri
     */
    public abstract boolean useItem();
}
