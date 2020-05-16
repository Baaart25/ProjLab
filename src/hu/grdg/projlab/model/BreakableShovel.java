package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.gui.render.BreakableShovelRenderer;
import hu.grdg.projlab.gui.render.ShovelRenderer;

/**
 * A Shovel item that breaks after a specified amount of usage
 */
public class BreakableShovel extends Shovel{
    private BreakableShovelRenderer renderer = new BreakableShovelRenderer(this);

    /**
     * Returns the renderer of the BrokenShovel
     * @return renderer attribute
     */
    @Override
    public ItemRenderer getRenderer() {
        return renderer;
    }

    private int useCount;

    public BreakableShovel(){
        this.useCount = 0;
    }

    /**
     * if usecount is below 3
     * it removes 2 layers of snow from the owners tile
     * @return true if it was succesful
     * @author Boti
     */
    public boolean useItem(){
        if(useCount<3) {
            boolean sikerult = super.useItem();
            ++useCount;
            return sikerult;
        }
        return false;
    }

    /**
     * Returns how many times have the shovel been used
     * @return useCount attribute
     */
    public int getUseCount(){
        return useCount;
    }

}
