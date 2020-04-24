package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;

public class UnstableIceTile extends IceTile{
    private int limit;

    public UnstableIceTile(int limit) {
        this.limit = limit;
    }

    /**
     * Returns the limit of the tile
     * @author Dorina
     */
    @Override
    public int scanLimit() {
        return limit;
    }

    /**
     * Accept the entity, if the number of entities is more than the limit the player died
     * @param entity the Entity who steps on the Tile
     * @auhtor Dorina
     */
    @Override
    public void acceptEntity(Entity entity) {
        super.acceptEntity(entity);
        if(entities.size()>limit) {
            //ProtoIO.output(ProtoIO.OutputMessages.STEP_OUT_UNSTABLE_DIE);
            entity.die();
        }
        else{
            ProtoIO.output(ProtoIO.OutputMessages.STEP_OUT_TILE);
        }
    }

}
