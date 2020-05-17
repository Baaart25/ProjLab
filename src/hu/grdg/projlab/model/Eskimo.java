package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.EntityRenderer;
import hu.grdg.projlab.gui.render.EskimoRenderer;

/**
 * Eskimo osztály, playerből származik
 */
public class Eskimo extends Player{
    //tárolja a hozzátartozó renderert
    private EntityRenderer renderer = new EskimoRenderer(this);
    /**
     * Sets the maximum temperature in player's constructor
     * @author Geri
     */
    public Eskimo(Controller controller){
        super(5, controller);
    }

    /**
     * Calls buildIgloo on currentTile
     * @return Succesfulness of operation
     * @author Geri
     */
    public boolean specialAbility(){
        if(isInWater)
            return false;
        boolean succesfulness = getCurrentTile().buildIgloo();
        if(succesfulness){
            return true;
        }
        return false;
    }

    /**
     * Returns the renderer of the Eskimo
     * @return renderer attribute
     */
    @Override
    public EntityRenderer getRenderer() {
        return renderer;
    }
}
