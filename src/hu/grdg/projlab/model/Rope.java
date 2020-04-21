package hu.grdg.projlab.model;

public class Rope extends Item{

    /**
     * Saves the players from the surrounding tiles
     * @return true if it saved someone
     * @author Boti
     */
    public boolean useItem() {
        return owner.savingPlayers();
    }
}
