package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

public class BreakableShovel extends Shovel {

    int useCount = 3;

    @Override
    public boolean useItem() {
        SkeletonTester.call(this);
        if(useCount != 0) {
            boolean b = super.useItem();
            if(b) {
                useCount--;
                SkeletonTester.creturn(true);
                return true;
            }
        }

        SkeletonTester.creturn(false);
        return false;
    }
}
