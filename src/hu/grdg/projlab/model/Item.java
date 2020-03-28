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
        SkeletonTester.call(this, p);
        owner = p;
        SkeletonTester.creturn();
    }

    /**
     *
     * @return Succesfullness of the action
     * @author Geri
     */
    public abstract boolean useItem();

    /**
     * Sets if he item is frozen or not
     * @param isFrozen Is frozen or not
     * @author Barrow099
     */
    public void setIsFrozen(boolean isFrozen) {
        SkeletonTester.call(this, isFrozen);
        this.isFrozen = isFrozen;
        SkeletonTester.creturn();
    }

    /**
     * The player tries to pick up the item
     * If the item is frozen, the player cant do that so we return false
     * However if the player can pick it up, we set the owner to them and return true
     * @param p The player
     * @return True if success, else false
     * @author Barrow099
     */
    public boolean pickedUp(Player p) {
        SkeletonTester.call(this, p);
        if(isFrozen) {
            SkeletonTester.creturn(false);
            return false;
        }else {
            owner = p;
            p.addItem(this);                        // @author Geri
            SkeletonTester.creturn(true);
            return true;
        }
    }
}
