package hu.grdg.projlab.model;

import hu.grdg.projlab.Skeleton;
import hu.grdg.projlab.SkeletonTester;

/**
 * A normal ice tile with infinite capacity (represented by -1 when scanned)
 * Igloos can be build on this tile
 */
public class IceTile extends Tile{
    private int snowLayer;
    @Override
    public int scanLimit() {
        SkeletonTester.call(this, null);
        SkeletonTester.creturn(-1);
        return -1;
    }

    /**
     * Removes n layers of snow from the tile if possible
     * @param n layers to be removed
     * @return succesfulness of action
     * @author Geri
     */
    public boolean removeSnowLayer(int n){
        SkeletonTester.call(this,n);
        if (snowLayer - n < 0){
            SkeletonTester.creturn(false);
            return false;
        } else {
            SkeletonTester.creturn(true);
            return true;
        }
    }

    /**
     * Default konstruktor ahol nem kellenek az attribútumok
     * @author Geri
     */
    public IceTile(){
    }

    /**
     * Konstruktor with setting layers
     * @param n Number of layers to be
     * @author Geri
     */
    public IceTile(int n){
        snowLayer = n;
    }
}
