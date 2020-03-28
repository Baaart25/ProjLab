package hu.grdg.projlab.model;

/**
 * A Player type with Igloo building as special ability
 */
public class Eskimo extends Player{
    /**
     * Eskimo build igloo on currentTile
     */
    @Override
    public void specialAbility() {
        currentTile.buildIgloo();
    }

    /**
     * @author Geri
     */
    public Eskimo(){
        super();
        maxTemp = 5;
    }
}
