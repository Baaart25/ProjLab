package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.gui.render.ShovelRenderer;

public class Shovel extends Item {
    private static ShovelRenderer renderer = new ShovelRenderer();

    /**
     * removes 2 layers of snow from owners tile
     * @return true if it was succesful
     * @author Boti
     */
    public boolean useItem(){
        Tile t = owner.getCurrentTile();
        return t.removeSnowLayer(2);
    }

    /**
     * Returns the renderer of the Shovel
     * @return renderer attribute
     */
    @Override
    public ItemRenderer getRenderer() {
        return renderer;
    }
}
