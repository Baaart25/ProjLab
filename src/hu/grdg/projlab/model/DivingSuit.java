package hu.grdg.projlab.model;

public class DivingSuit extends Item{


    /**
     * calling the owners surviveWater function
     * @return true if the owner is in water
     * @author Boti
     */
    public boolean useItem(){
        return owner.surviveWater();
    }
}
