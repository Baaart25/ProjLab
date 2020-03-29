package hu.grdg.projlab.model;

import hu.grdg.projlab.Skeleton;
import hu.grdg.projlab.SkeletonTester;

/**
 * An item that will have three instances, when used, win conditions will be checked
 */
public class RocketPart extends Item{
    private Controller controller;

    /**
     * @author Dani
     */
    @Override
    public boolean useItem() {
        SkeletonTester.call(this);
        controller.checkWin();
        SkeletonTester.creturn();
        return false;
    }

    /**
     * Beállítható vele a kontroller
     * @param c controller
     * @author Dani
     */
    public void setController(Controller c) {
        SkeletonTester.call(this, c);
        controller = c;
        SkeletonTester.creturn();
    }

}
