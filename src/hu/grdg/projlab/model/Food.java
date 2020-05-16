package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.gui.render.FoodRenderer;

public class Food extends Item{
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
