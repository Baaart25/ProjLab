package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;

import javax.swing.*;
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

    /**TODO: lehet kikéne az ilyeneket törölni
     * Proto használta csak!
     * @param p
     */
    public void addPlayer(Player p){
        players.add(p);
    }

    /**TODO: lehet kikéne az ilyeneket törölni
     * Proto használta csak!
     * @param rp
     */
    public void addRocketPart(RocketPart rp){
        rocketParts.add(rp);
    }

    //FIXME
    public void endGame(boolean win){

        if(win) {
            ProtoIO.output("Item used. Game ended with a win.");
        }
        //TODO Add graceful shutdown
        System.exit(0);
    }



    public void startGame(){ }

    /**
     * Létrehoz a paraméterként kapott darabszámú játékost
     * @author Dani
     */
    void generatePlayers() {
        /*JFrame frame = new JFrame("InputDialog");

        int n = JOptionPane.showConfirmDialog(
                frame, "Add meg a játékosok számát?",
                "Játékosok száma",
                JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {

        }

        String response = JOptionPane.showInputDialog(frame, "Add meg a játékosok számát?", "");
        if ((response != null) && (response.length() > 0)) {

        }*/

    }

    /**
     * Inicializálja a pályát
     * @author Dani
     */
    void init() {
        Level level = new Level();
        generatePlayers();
        level.genrateLevel(players);
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
