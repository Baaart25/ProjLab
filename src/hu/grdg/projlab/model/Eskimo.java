package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;

public class Eskimo extends Player{

    /**
     * Sets the maximum temperature in player's constructor
     * @author Geri
     */
    public Eskimo(){
        super(5);
    }

    /**
     * Calls buildIgloo on currentTile
     * @return Succesfulness of operation
     * @author Geri
     */
    public boolean specialAbility(){

        boolean succesfulness = getCurrentTile().buildIgloo();
        if(succesfulness){
            ProtoIO.output(ProtoIO.OutputMessages.SPECAB_OUT_ESKIMO);
            return true;
        } else {
            ProtoIO.output(ProtoIO.OutputMessages.SPECAB_ERR_FAILURE);
            return false;
        }
    }
}
