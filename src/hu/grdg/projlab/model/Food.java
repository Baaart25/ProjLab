package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.gui.render.FoodRenderer;

/**
 * Food, ha használják, akkor életet ad az ownerjének
 * Itemből származik
 */
public class Food extends Item{
    //tárolja a hozzátartozó renderert
    private static FoodRenderer renderer = new FoodRenderer();

    /**
     * Calls the owner player's eat function
     * @return Succesfulness of operation
     * @author Geri
     */
    public boolean useItem(){
        return owner.eat(this);
    }

    /**
     * Returns the renderer of the Food
     * @return renderer attribute
     */
    @Override
    public ItemRenderer getRenderer() {
        return renderer;
    }
}
