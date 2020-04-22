package hu.grdg.projlab.model;

public abstract class Entity {
    protected Tile currentTile;

    /**
     * Returns the Entity's current Tile
     * @return the Entity's current Tile
     * @author Dorina
     */
    public Tile getCurrentTile() {
        return currentTile;
    }

    public abstract void move(int direction);

    public abstract void damage(int i);

    public abstract void die();

    public abstract void fallInWater();

    /**
     * Sets the Entity's currentTile
     * @param tile the Entity,s current Tile
     * @author Dorina
     */
    public void setCurrentTile(Tile tile){
        currentTile=tile;
    }

    public abstract boolean savedFromWater(Tile t);

}
