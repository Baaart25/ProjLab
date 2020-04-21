package hu.grdg.projlab.model;

public class Shovel extends Item {
    /**
     * removes 2 layers of snow from owners tile
     * @return true if it was succesful
     * @author Boti
     */
    public boolean useItem(){
        Tile t = owner.getCurrentTile();
        return t.removeSnowLayer(2);
    }
}
