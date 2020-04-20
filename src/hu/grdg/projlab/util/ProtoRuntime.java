package hu.grdg.projlab.util;

import hu.grdg.projlab.model.Controller;
import hu.grdg.projlab.model.Entity;
import hu.grdg.projlab.model.IceTile;
import hu.grdg.projlab.model.Tile;

import java.util.HashMap;
import java.util.List;

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
}
