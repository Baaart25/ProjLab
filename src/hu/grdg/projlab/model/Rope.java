package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;

public class Rope extends Item{

    /**
     * Saves the players from the surrounding tiles
     * @return true if it saved someone
     * @author Boti
     */
    public boolean useItem() {
        return owner.savingPlayers();
    }

    @Override
    public ItemRenderer getRenderer() {
        return null;
    }
}
