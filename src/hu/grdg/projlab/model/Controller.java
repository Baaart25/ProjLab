package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * The controller controls the whole game. It creates the map, the players, the events.
 * It schedules the turns, events. It checks the win and lose conditions.
 */
public class Controller {
    private ArrayList<TurnBasedEvent> events;
    private Level level;
    private ArrayList<Player> players;
    ArrayList<RocketPart> rocketParts;

    //Event listeners
    private ArrayList<Runnable> gameStartEventListeners = new ArrayList<>();
    private ArrayList<Consumer<Player>> nextPlayerEventListeners = new ArrayList<>();

    public Controller(){
        events = new ArrayList<TurnBasedEvent>();
        players = new ArrayList<Player>();
        rocketParts = new ArrayList<RocketPart>();
    }


    //Event listeners
    public void addOnGameStartListener(Runnable listner) {
        this.gameStartEventListeners.add(listner);
    }

    public void addOnNextPlayerListner(Consumer<Player> listener) {
        this.nextPlayerEventListeners.add(listener);
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



    public void startGame() {
        this.init();

        //Call event listeners
        for (Runnable gameStartEventListener : this.gameStartEventListeners) {
            gameStartEventListener.run();
        }
    }

    /**
     * Létrehoz a felhasználótól megkérdezett számú játékost
     * //TODO ki kéne hogy tudjon lépni az ember a számválasztás közben is
     * @author Dani
     */
    public void generatePlayers() {

        boolean formatOk = false;
        int count = 0;
        String response;
        String message = "Add meg a játékosok számát!";
        while (!formatOk || count < 3) {
            response = JOptionPane.showInputDialog(null, message, "");
            try {
                count = Integer.parseInt(response);
                formatOk = true;
                message = "A játékosok számának minimum 3-nak kell lennie!\nAdd meg a játékosok számát!";
            } catch(NumberFormatException e) {
                formatOk = false;
                message = "Kérlek számot adj meg!\nAdd meg a játékosok számát!";
            }
        }
        for(int i = 0; i < count; i++) {
            Object[] options = { "Eszkimo", "Kutató"};

            int result = JOptionPane.showOptionDialog(null, (i+1) + ". játékos karaktere:", "Karakterválasztás",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, null);
            if(result == JOptionPane.YES_OPTION) { //Eszkimo
                players.add(new Eskimo(this));
            }
            if(result == JOptionPane.NO_OPTION) {
                players.add(new Scientist(this));
            }
            if(result == JOptionPane.CLOSED_OPTION) {
                i--;
            }
        }
    }

    /**
     * Inicializálja a pályát
     * @author Dani
     */
    public void init() {
        level = new Level(this);
        generatePlayers();
        level.generateLevel(players);
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

    public Level getLevel() {
        return level;
    }
}
