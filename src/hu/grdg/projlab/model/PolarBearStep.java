package hu.grdg.projlab.model;

import java.util.ArrayList;
import java.util.Random;

public class PolarBearStep implements TurnBasedEvent{
    private ArrayList<PolarBear> bears = new ArrayList<>();
    private static PolarBearStep instance;


    public PolarBearStep(){
        instance = this;
    }

    /**
     * Add the given bear to the list
     * @param bear
     * @author Dorina
     */
    public void addPolarBear(PolarBear bear){
        bears.add(bear);
    }

    /**
     *Steps every bears to random direction
     * @param lvl the level of the game
     * @param fullTurn true if this event have to be happen
     * @author Dorina
     */
    @Override
    public void doEvent(Level lvl, boolean fullTurn) {
        if(fullTurn){
            for (PolarBear bear: bears) {
                bear.move(new Random().nextInt(3));
            }
        }
    }

    /**
     * Return the only intance of the PolarBearStep
     * @return the PolarBearStep instance
     * @author Dorina
     */
    public static PolarBearStep getInstance() {
        return instance;
    }
}
