package hu.grdg.projlab.model;

import hu.grdg.projlab.debug.DebugSettings;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Olyan Entity, amit a felhasználó tud irányítani
 */
public abstract class Player extends Entity{
    //tárolja a max életét
    protected int maxTemp;
    //tárolja a felvett item-eket
    private ArrayList<Item> inventory;
    //ismeri a controllert
    private Controller controller;
    //tárolja az aktuális életét
    private int currentTemp;
    //tudja, hogy vízben van-e vagy nem
    protected boolean isInWater;
    //tudja, hogy ő-e a soron levő játékos vagy nem
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

    /**
     * Returns the Entity's inventory
     * @return the Entity's inventory
     * @author Dorina
     */
    public List<Item> getInventory() {
        return inventory;
    }

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

    /**
     * Returns if the Player is the active player
     * @return true of the player is active
     * @author Barrow099
     */
    public boolean isActive() {
        return active;
    }


    private boolean wasInWater = false;

    /**
     * Sets the active attribute to the given value
     * @param a active value
     * @author Barrow099
     */
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
        JOptionPane.showMessageDialog(null,"Player damaged","Oops", JOptionPane.WARNING_MESSAGE);
        if(currentTemp<=0) die();
    }

    /**
     * The player dies
     * @author Dorina
     */
    @Override
    public void die() {
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
     * @author Dorina
     */
    public int getTemp() {
        return currentTemp;
    }

    /**
     * Returns the maximum temperature of the player
     * @return maxTemp attribute
     * @author Dorina
     */
    public int getMaxTemp(){
        return maxTemp;
    }

}
