package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.debug.DebugSettings;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player extends Entity{
    protected int maxTemp;
    private ArrayList<Item> inventory;
    private Controller controller;
    private int currentTemp;
    protected boolean isInWater;
    private boolean active = false;


    /**
     * Sets maxTemp for player, init other attributes
     * @param _maxTemp Maximum temperature
     * @author Geri, Dorina
     */
    public Player(int _maxTemp, Controller controller){
        inventory = new ArrayList<Item>();
        maxTemp = _maxTemp;
        currentTemp = maxTemp;
        isInWater = false;
        this.controller = controller;
    }

    //----------------WARNING-----------------
    //NOT IN DOCS
    //TODO Fix the doc

    /**
     * Returns the Entity's inventory
     * @return the Entity's inventory
     * @author Dorina
     */
    public List<Item> getInventory() {
        return inventory;
    }

    //----------------WARNING----------------
    //CHANGED ARG FROM VOID TO INT
    //@returns The index of the added item
    //TODO Fix doc

    /**
     * Add new item to the inventory
     * @param item new Item
     * @return The index of the added item
     * @author Dorina
     */
    public int addItem(Item item) {
        inventory.add(item);
        return inventory.size();
    }

    public boolean isActive() {
        return active;
    }


    private boolean wasInWater = false;
    public void setActive(boolean a) {
        if(active == a)
            return;

        if(wasInWater && !a && isInWater)
            die();

        if(!a)
            wasInWater = isInWater;



        this.active = a;
        //Trigger tile redraw
        currentTile.updateEvent();
    }

    //----------------NOT IN DOC-----------
    /**
     * Sets the current temp
     * @param currentTemp The current temp
     */
    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public abstract boolean specialAbility();

    /**
     * Entity eats food
     * @param f the food that the Entity eats
     * @return if the eating was successful
     * @author Dorina
     */
    public boolean eat(Food f){
        if(maxTemp == currentTemp) return false;
        currentTemp++;
        inventory.remove(f);
        return true;
    }

    /**
     * Save player from near holes
     * @return if the saving was successful
     * @author Dorina
     */
    public boolean savingPlayers(){
        boolean succ = false;
        Collection<Tile> neighbours = currentTile.getNeighbours();
        for (Tile neighbour: neighbours) {
            ArrayList<Entity> entities = new ArrayList<>(neighbour.getEntities());
            for (Entity entity: entities) {
                if(entity.savedFromWater(currentTile)) succ = true;
            }
        }
        return succ;
    }

    /**
     * Entity moves at the given direction
     * @param direction the direction in which the Entity moves
     * @author Dorina
     */
    @Override
    public boolean move(int direction) {
        if(isInWater) return false;
        Tile newTile = currentTile.getNeighbour(direction);
        if(newTile == null)
            return false;
        currentTile.removeEntity(this);
        newTile.acceptEntity(this);
        return true;
    }

    /**
     * Decrease the Entity's current temp with the given amount
     * @param i the amount of temp the Entity's currentTemp decrease
     * @author Dorina
     */
    @Override
    public void damage(int i) {
        currentTemp -= i;
        ProtoIO.output(ProtoIO.OutputMessages.SNOWSTORM_OUT_PLAYERDAMAGE);
        JOptionPane.showMessageDialog(null,"Player damaged","Oops", JOptionPane.WARNING_MESSAGE);
        if(currentTemp<=0) die();
    }

    /**
     * The player dies
     * @author Dorina
     */
    @Override
    public void die() {

        //TODO REMOVE
        try {
            throw new RuntimeException("JEE");
        }catch (Exception e) {
            e.printStackTrace();
        }


        ProtoIO.output(ProtoIO.OutputMessages.SNOWSTORM_OUT_PLAYERDIE);
        controller.endGame(false);
    }

    /**
     * Sets the isInWater to true;
     * @author Dorina
     */
    @Override
    public void fallInWater() {

        //Disable water damage for debug purposes
        if(DebugSettings.DEBUG_NO_WATER_DAMAGE)
            return;

        isInWater = true;
        JOptionPane.showMessageDialog(null, "You fell in a hole","OOPS", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Sets the isInWater to false
     * @author Dorina
     */
    public boolean surviveWater(){
        if(isInWater) {
            isInWater = false;
            wasInWater = false;
            return true;
        }
        return false;
    }


    /**
     * Save the entity from drowning
     * @param tile the tile where the entity steps
     * @return if the saving was successful
     * @author Dorina
     */
    public boolean savedFromWater(Tile tile){
        if(isInWater){
            currentTile.removeEntity(this);
            tile.acceptEntity(this);
            isInWater = false;
            return true;
        }
        return false;
    }

    /**
     * Returns the temperature of the Player
     * @return currentTemp attribute
     */
    public int getTemp() {
        return currentTemp;
    }
}
