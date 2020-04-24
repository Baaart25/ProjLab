package hu.grdg.projlab.model;

import java.util.ArrayList;

public class RemoveTent implements TurnBasedEvent{
    private static RemoveTent singleton;
    private ArrayList<Tent> tents;

    private RemoveTent(){
        singleton = this;
    }

    /**
     * Értesít minden sátrat, hogy csökkentse a számlálóját (meghívja a decreaseLife() függvényüket)
     * @param lvl az adott leve (csak az automatizált hóviharhoz kell)
     * @param fullTurn akkor true, ha egy teljes kör telt le (ehhez lényegtelen)
     */
    public void doEvent(Level lvl, boolean fullTurn) {
        for (Tent tent : tents) {
            tent.decreaseLife();
        }
    }

    /**
     * Visszaadja az egyetlen RemoveTent példányt amit statikus attribútumként tárol.
     * @return egyetlen RemoveTent példány
     * @author Dani
     */
    public static RemoveTent getInstance() {
        if (singleton == null)
            singleton = new RemoveTent();
        return singleton;
    }

    /**
     * Hozzáadja a paraméterként kapott sátrat a tárolt tents listához.
     * @param tent a hozzáadandó sátor
     * @author Dani
     */
    void addTent(Tent tent) {
        singleton.tents.add(tent);
        tent.setLife(3); //FIXME: meg kéne kapnia a controller-től a játékosok számát!
    }

    /**
     * Eltávolítja a paraméterként kapott sátrat a tárolt tents listához.
     * @param tent a listából kiveendő sátor
     * @author Dani
     */
    void removeTent(Tent tent) {
        singleton.tents.remove(tent);
    }
}
