package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.gui.render.RopeRenderer;

public class Rope extends Item{
    private static RopeRenderer renderer = new RopeRenderer();
    /**
     * Saves the players from the surrounding tiles
     * @return true if it saved someone
     * @author Boti
     */
    public boolean useItem() {
        return owner.savingPlayers();
    }

    /**
     * Returns the renderer of the Rope
     * @return renderer attribute
     */
    @Override
    public ItemRenderer getRenderer() {
        return renderer;
    }
}
