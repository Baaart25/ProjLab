package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;

public class DivingSuit extends Item{


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
        return null;
    }
}
