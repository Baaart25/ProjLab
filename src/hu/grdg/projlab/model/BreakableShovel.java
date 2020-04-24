package hu.grdg.projlab.model;

/**
 * A Shovel item that breaks after a specified amount of usage
 */
public class BreakableShovel extends Shovel{
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

}
