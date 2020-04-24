package hu.grdg.projlab.util;

import hu.grdg.projlab.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The game runtime state of the proto
 * @author Barrow099
 */
public class ProtoRuntime {
    private HashMap<String, Tile> namedTiles;
    private HashMap<String, Entity> namedEntities;

    private Controller gameController;


    public ProtoRuntime() {
       reset();
    }

    /**
     * Resets the game
     */
    public void reset() {
        namedTiles = new HashMap<>();
        namedEntities = new HashMap<>();
        gameController = new Controller();
    }

    /**
     * Ads a new named tile to the tiles
     * @param tileName The name of the new tile
     * @param tile The new tile
     */
    public void addTile(String tileName, Tile tile) {
        namedTiles.put(tileName, tile);
    }

    /**
     * Gets a tile by its name, returns null if not found
     * @param tileName The name of the tile
     * @return The tile or null
     */
    public Tile getTile(String tileName) {
        if(namedTiles.containsKey(tileName))
            return namedTiles.get(tileName);
        return null;
    }

    /**
     * Gets a registered entity by its name
     * @param entityName The name of the entity
     * @return The entity or null if not found
     */
    public Entity getEntity(String entityName) {
        if(namedEntities.containsKey(entityName))
            return namedEntities.get(entityName);
        return null;
    }

    /**
     * Registers a new entity to the game
     * @param name Name of the entity
     * @param entity The new entity
     */
    public void addEntity(String name, Entity entity) {
        namedEntities.put(name, entity);
        if(entity instanceof Player)
            gameController.addPlayer((Player) entity);
    }

    /**
     * Returns all named tile
     * @return Set of named tile
     */
    public Set<Map.Entry<String, Tile>> getTiles() {
        return namedTiles.entrySet();
    }

    /**
     * Returns all named entities
     * @return Set of named entities
     */
    public Set<Map.Entry<String, Entity>> getEntities() {
        return namedEntities.entrySet();
    }

    /**
     * Returns the current game controller
     * @return The game controller
     */
    public Controller getController() {
        return gameController;
    }
}
