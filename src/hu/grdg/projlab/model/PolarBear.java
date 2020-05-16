package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.EntityRenderer;
import hu.grdg.projlab.gui.render.PolarBearRenderer;

public class PolarBear extends Entity{
    private PolarBearRenderer renderer = new PolarBearRenderer();

    /**
     * Steps in given direction
     * @param direction Direction of step
     * @author Geri
     */
    @Override
    public boolean move(int direction) {
        Tile stepTile = currentTile.getNeighbour(direction);
        if(stepTile==null) return false;
        currentTile.removeEntity(this);
        stepTile.acceptEntity(this);
        stepTile.bearAttack();
        return true;
    }

    /**
     * Does nothing, can't be damaged
     * @param i Amount of damage
     * @author Dorina, Geri
     */
    @Override
    public void damage(int i) {

    }

    /**
     * Does nothing, cannot die
     * @author Dorina, Geri
     */
    @Override
    public void die() {

    }

    /**
     * Does nothing, cannot suffocate
     * @author Dorina, Geri
     */
    @Override
    public void fallInWater() {

    }

    /**
     * Does nothing, cannot be saved
     * @param t Not relevant
     * @return false
     * @author Dorina, Geri
     */
    @Override
    public boolean savedFromWater(Tile t) {
        return false;
    }

    /**
     * Returns the renderer of the polarbear
     * @return the renderer
     * @author Dorina
     */
    @Override
    public EntityRenderer getRenderer() {
        return renderer;
    }
}
