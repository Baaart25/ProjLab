package hu.grdg.projlab.model;

public class SnowStorm implements TurnBasedEvent{
    private static SnowStorm instance = null;
    public static SnowStorm getInstance() {

        //TODO Replace with controller initialization
        if(instance == null)
            new SnowStorm();


        return instance;
    }

    public SnowStorm() {
        instance = this;
    }

    @Override
    public void doEvent(Level lvl, boolean fullTurn) {

    }

    //FIXME Fuckin not in docs
    /**
     * Do the storm on the tile. It adds the snow and damages the players.
     * @param tile Target tile
     * @param amount Amount of snow to add
     * @author Barrow099
     */
    public void doStorm(Tile tile, int amount) {
        tile.addSnowLayer(amount);
        tile.stormDamage();
    }
}
