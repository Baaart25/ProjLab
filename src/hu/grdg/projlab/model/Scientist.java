package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * A player type that can neighbour tiles for their capacities
 */
public class Scientist extends Player{
    @Override
    public void specialAbility() {
        SkeletonTester.call(this);
        //currentTile.buildIgloo(); ???????
        Tile t = currentTile.getNeighbour(Direction.EAST);
        SkeletonTester.addNamedReference(t, "t");
        int limit = t.scanLimit();

        SkeletonTester.creturn();
    }
}
