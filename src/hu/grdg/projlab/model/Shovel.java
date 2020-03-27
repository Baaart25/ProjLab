package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * An item that removes 2 layers of snow from the Tile
 */
public class Shovel extends Item {


    @Override
    public boolean useItem() {
        SkeletonTester.call(this);

        Tile currentTile = owner.getCurrentTile();
        boolean res = currentTile.removeSnowLayer(2);


        SkeletonTester.creturn(res);
        return res;
    }
}
