package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * An item that can be used to save adjacent players from holes
 */
public class Rope extends Item{

    @Override
    public boolean useItem() {
        SkeletonTester.call(this);

        boolean res = owner.savingPlayers();

        SkeletonTester.creturn(res);
        return res;
    }
}
