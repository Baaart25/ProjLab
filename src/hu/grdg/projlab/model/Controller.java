package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;

import java.util.ArrayList;

/**
 * The controller controls the whole game. It creates the map, the players, the events.
 * It schedules the turns, events. It checks the win and lose conditions.
 */
public class Controller {
    private ArrayList<TurnBasedEvent> events;
    private Level level;
    private ArrayList<Player> players;
    ArrayList<RocketPart> rocketParts;

    public Controller(){
        events = new ArrayList<TurnBasedEvent>();
        players = new ArrayList<Player>();
        rocketParts = new ArrayList<RocketPart>();
    }

    //FIXME
    public void endGame(boolean win){
        ProtoIO.output(ProtoIO.OutputMessages.SNOWSTORM_OUT_PLAYERDIE);
    }

    //FIXME
    public void startGame(){

    }

    /**
     * Checks if all conditions are true for winning the game
     * @return if all conditions are true for win
     * @author Dorina
     */
    public boolean checkWin(){
        Tile tile = players.get(0).getCurrentTile();
        for (Player player: players) {
            if(!tile.equals(player.getCurrentTile())) return false;
        }
        for (RocketPart rp: rocketParts) {
            if(rp.getOwner() == null) return false;
        }
        endGame(true);
        return true;
    }

    /**
     * Returns the number of players in the game
     * @return the number of players
     * @author Dorina
     */
    public int getPlayerCount(){
        return players.size();
    }
}
