package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * An item that adds an additional move to the player when in a hole so
 * they can step out of it (and not dying).
 */
public class DivingSuit extends Item{
    @Override
    public boolean useItem() {
        SkeletonTester.call(this);

        if(owner.surviveWater()) {
            SkeletonTester.creturn(true);
            return true;
        }else {
            SkeletonTester.creturn(false);
            return false;
        }

    }
}
