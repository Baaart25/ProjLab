package hu.grdg.projlab.model;

public class Food extends Item{


    /**
     * Calls the owner player's eat function
     * @return Succesfulness of operation
     * @author Geri
     */
    public boolean useItem(){
        return owner.eat(this);
    }
}
