package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.gui.EntityRenderer;
import hu.grdg.projlab.gui.render.ScientistRenderer;

public class Scientist extends Player{

    private static EntityRenderer renderer = new ScientistRenderer();

    /**
     * Sets the maximum temperature in player's constructor
     * @author Geri
     */
    public Scientist(Controller controller){
        super(4,controller);
    }

    /**
     * Scientist scan a tile at a direction
     * @return if the scanning was successful
     * @author Dorina
     */
    @Override
    public boolean specialAbility() {
        Tile t = currentTile.getNeighbour(Direction.direction);
        int limit = t.scanLimit();
        ProtoIO.outputf(ProtoIO.OutputMessages.SPECAB_OUT_SCI, limit);
        return true;
    }

    @Override
    public EntityRenderer getRenderer() {
        return renderer;
    }
}
