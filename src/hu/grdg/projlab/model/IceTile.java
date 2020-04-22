package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;

public class IceTile extends Tile{

    /**
     * Scans the limit of the Tile
     * @return -1 (the IceTile is unlimited)
     * @author Dorina
     */
    @Override
    public int scanLimit() {
        return -1;
    }

    @Override
    public void acceptEntity(Entity entity) {
        super.acceptEntity(entity);
        ProtoIO.output(ProtoIO.OutputMessages.STEP_OUT_TILE);
    }
}
