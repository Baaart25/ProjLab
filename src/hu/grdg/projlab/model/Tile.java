package hu.grdg.projlab.model;

import hu.grdg.projlab.debug.DebugSettings;
import hu.grdg.projlab.gui.TileRenderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public abstract class Tile {
    private boolean hasIgloo;
    private boolean hasTent;
    private int snowLayers;
    private Item frozenItem;
    private HashMap<Integer,Tile> neighbours;
    protected ArrayList<Entity> entities;
    private ArrayList<Runnable> tileUpdateListeners = new ArrayList<>();

    public Tile(){
        neighbours= new HashMap<Integer, Tile>();
        entities = new ArrayList<Entity>();
    }

    /**
     *Returns the Tile at the given direction
     * @param direction the direction the Tile at
     * @return the Tile at direction
     * @author Dorina
     */
    public Tile getNeighbour(int direction) {
        return neighbours.get(direction);
    }

    /**
     *Set the neighbor at the given direction
     * @param tile the Tile at the direction
     * @param direction the direction the tile at
     * @author Dorina
     */
    public void setNeighbour(Tile tile, int direction) {
        neighbours.put(direction,tile);
    }

    /**
     * Returns the frozenItem
     * @return the item frozen in the Tile
     * @author Dorina
     */
    public Item getFrozenItem() {
        if(snowLayers == 0 || DebugSettings.DEBUG_SHOW_ALL_ITEMS)
            return frozenItem;
        return null;
    }

    /**
     *Removes the given amount of snow layers from the Tile
     * @param amount the amount snow wanted to remove
     * @return if removing snow layer was successful
     * @author Dorina
     */
    public boolean removeSnowLayer(int amount) {
        if(snowLayers==0) return false;
        snowLayers = (snowLayers<=amount) ? 0 : (snowLayers-amount);
        updateEvent();
        return true;
    }

    /**
     * Adds the given entity to the list
     * @param entity the Entity who steps on the Tile
     * @author Dorina
     */
    public void acceptEntity(Entity entity) {
        entity.setCurrentTile(this);
        entities.add(entity);
        updateEvent();
    }

    /**
     *Put the frozen Item into the Tile
     * @param item the item frozen in the Tile
     * @author Dorina
     */
    public boolean setFrozenItem(Item item) {
        if(frozenItem==null){
            frozenItem = item;
            updateEvent();
            return true;
        }
        if(item == null) {
            frozenItem = null;
            updateEvent();
            return true;
        }
        return false;
    }

    /**
     *Add the given amount of  snow layer to the Tile
     * @param amount the amount of snow layer added
     * @author Dorina
     */
    public void addSnowLayer(int amount) {
        snowLayers += amount;
        updateEvent();
    }

    /**
     * Removes the given entity from the Tile
     * @param entity the entity who steps away from the Tile
     * @author Dorina
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
        updateEvent();
    }

    /**
     * Build igloo on the Tile
     * @return the if the building was successful
     * @author Dorina
     */
    public boolean buildIgloo(){
        if(hasIgloo) return false;
        hasIgloo=true;
        updateEvent();
        return true;
    }

    /**
     * Player pick up Item from the Tile
     * @param player the Player who want to pick up the item
     * @return if the pickup was successful
     * @author Dorina
     */
    public boolean pickupItem(Player player){
        if(snowLayers>0 || frozenItem==null) return false;
        updateEvent();
        return frozenItem.pickedUp(player);

    }

    /**
     *Damages all entities on the tile if there is no igloo or tent
     * @author Dorina
     */
    public void stormDamage(){
        if(!hasIgloo && !hasTent) {
            for (Entity e : entities) {
                e.damage(1);
            }
        }
    }

    /**
     * Bear attacks the entities on the Tile if there is no igloo
     * @author Dorina
     */
    public void bearAttack(){
        if(!hasIgloo){
            for (Entity e: entities) {
                    e.die();
            }
        }
    }

    public abstract int scanLimit();

    /**
     *Return the entitites
     * @return the entities on the Tile
     * @author Dorina
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Return the neighbours of the Tile
     * @return all the neighbours
     * @author Dorina
     */
    public Collection<Tile> getNeighbours() {
        return neighbours.values();
    }

    /**
     *Build tent on the Tile
     * @return the if the building was successful
     * @author Dorina
     */
    public boolean buildTent(){
        if(hasTent) return false;
        hasTent=true;
        return true;
    }

    /**
     * Removes the tent from the Tile
     * @author Dorina
     */
    public void removeTent(){
        hasTent=false;
    }

    /**
     * Returns the associated tile renderer for this tile
     * @return The renderer
     */
    public abstract TileRenderer getRenderer();

    /**
     * Gets the number of snow layers on the tile
     * @return The number of snow layers
     */
    public int getSnowLayers() {
        return snowLayers;
    }

    /**
     * Return if the Tile has an igloo
     * @return true if Tile has igloo
     * @author Dorina
     */
    public boolean hasIgloo() {
        return hasIgloo;
    }

    /**
     * Return if the Tile has a Tent
     * @return true if Tile has tent
     * @author Dorina
     */
    public boolean hasTent() {
        return hasTent;
    }

    public void addOnUpdateListener(Runnable listener) {
        tileUpdateListeners.add(listener);
    }

    public void updateEvent() {
        for (Runnable tileUpdateListener : this.tileUpdateListeners) {
            tileUpdateListener.run();
        }
    }
}

