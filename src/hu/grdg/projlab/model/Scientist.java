package hu.grdg.projlab.model;

public class Scientist extends Player{

    /**
     * Sets the maximum temperature in player's constructor
     * @author Geri
     */
    public Scientist(){
        super(4);
    }

    //FIXME
    @Override
    public boolean specialAbility() {
        return false;
    }
}
