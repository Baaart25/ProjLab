package hu.grdg.projlab.model;

public class Eskimo extends Player{

    /**
     * Calls buildIgloo on currentTile
     * @return Succesfulness of operation
     * @author Geri
     */
    public boolean specialAbility(){
        return getCurrentTile().buildIgloo();
    }
}
