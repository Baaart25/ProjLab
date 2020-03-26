package hu.grdg.projlab.model;

import hu.grdg.projlab.Skeleton;
import hu.grdg.projlab.SkeletonTester;

/**
 * A normal ice tile with infinite capacity (represented by -1 when scanned)
 * Igloos can be build on this tile
 */
public class IceTile extends Tile{
    @Override
    public int scanLimit() {
        SkeletonTester.call(this, null);
        SkeletonTester.creturn();
        return -1;
    }
}
