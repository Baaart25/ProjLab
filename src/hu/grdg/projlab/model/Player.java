package hu.grdg.projlab.model;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity{

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
}
