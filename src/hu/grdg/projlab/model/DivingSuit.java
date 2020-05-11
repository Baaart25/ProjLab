package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.gui.render.DivingSuitRenderer;

public class DivingSuit extends Item{
    private static DivingSuitRenderer renderer = new DivingSuitRenderer();

    /**
     * calling the owners surviveWater function
     * @return true if the owner is in water
     * @author Boti
     */
    public boolean useItem(){
        return owner.surviveWater();
    }

    @Override
    public ItemRenderer getRenderer() {
        return renderer;
    }
}
