package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * An Item that increases the players body temp when used
 */
public class Food extends Item{


    public void eat() {
        //////////
        /////////
    }

    /**
     * Calls the player's eat function with itself as parameter
     * @return Succesfulness of action
     * @author Geri
     */
    @Override
    public boolean useItem() {
        SkeletonTester.call(this);
        boolean b = owner.eat(this);
        SkeletonTester.creturn(b);
        return b;
    }
}
