package hu.grdg.projlab.model;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity{
    protected int maxTemp;

    /**
     * Sets maxTemp for player
     * @param _maxTemp Maximum temperature
     * @author Geri
     */
    public Player(int _maxTemp){
        maxTemp = _maxTemp;
    }

    //----------------WARNING-----------------
    //NOT IN DOCS
    //TODO Fix the doc
    //FIXME
    public List<Item> getInventory() {
        return new ArrayList<>();
    }

    //----------------WARNING----------------
    //CHANGED ARG FROM VOID TO INT
    //@returns The index of the added item
    //TODO Fix doc
    //FIXME
    public int addItem(Item itm) {
        return 0;
    }


    //----------WARNING-------------
    //IMPLEMENTATION HAS TO PRINT THE OUTPUT MESSAGE
    //FIXME
    public boolean specialAbility() {
        return false;
    }

    public boolean eat(Food f){return false;}

    public boolean savingPlayers(){return false;}
}
