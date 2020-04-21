package hu.grdg.projlab.model;

import hu.grdg.projlab.Proto;
import hu.grdg.projlab.ProtoIO;

public class HoleTile extends Tile{


    /**
     * Scans the limit of the tile
     * @return 0
     * @author Dorina
     */
    @Override
    public int scanLimit() {
        return 0;
    }

    /**
     * The entity fall in water
     * @param entity the Entity who steps on the Tile
     * @author Dorina
     */
    @Override
    public void acceptEntity(Entity entity) {
        super.acceptEntity(entity);
        entity.fallInWater();
        ProtoIO.output(ProtoIO.OutputMessages.STEP_OUT_HOLE);
        entity.setCurrentTile(this);
    }

    /**
     * Does nothing, can't have frozen item
     * @param item the item frozen in the Tie
     * @return false
     * @author Dorina
     */
    @Override
    public boolean setFrozenItem(Item item) {
        return false;
    }

    /**
     * Does nothing, can't build igloo on hole
     * @return the success of the building
     * @author Dorina
     */
    @Override
    public boolean buildIgloo() {
        return false;
    }
}
