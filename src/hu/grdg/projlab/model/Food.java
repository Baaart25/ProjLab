package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;

public class Food extends Item{


    /**
     * Calls the owner player's eat function
     * @return Succesfulness of operation
     * @author Geri
     */
    public boolean useItem(){
        return owner.eat(this);
    }

    @Override
    public ItemRenderer getRenderer() {
        return null;
    }
}
