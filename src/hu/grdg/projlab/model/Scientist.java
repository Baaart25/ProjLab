package hu.grdg.projlab.model;

import hu.grdg.projlab.Skeleton;

/**
 * A player type that can neighbour tiles for their capacities
 */
public class Scientist extends Player{
    @Override
    public void specialAbility() {
        Skeleton.call(this);
        currentTile.buildIgloo();
        Skeleton.creturn();
    }
}
