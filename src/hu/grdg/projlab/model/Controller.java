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
    private static int numberOfPlayers;


    /**
     * Returns the number of players
     * @return numOfPlayers attribute
     * @author Dorina
     */
    public static int getNumOfPlayers(){
        return numberOfPlayers;
    }

    private Object playerLock = new Object();

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
            JOptionPane.showMessageDialog(null, "YOU WIN","GAME ENDED", JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "YOU LOSE","GAME ENDED", JOptionPane.INFORMATION_MESSAGE);
        }
        //TODO Add graceful shutdown
        System.exit(0);
    }

    private void reset() {
        this.players.clear();

    }

    public void startGame(){

        this.reset();
        this.init();

        //Call event listeners
        for (Runnable gameStartEventListener : this.gameStartEventListeners) {
            gameStartEventListener.run();
        }

        while(true) {
            for(int i = 0; i < players.size(); i++) {
                System.out.printf("Player %d's turn\n", i);
                JOptionPane.showMessageDialog(null, String.format("Player %d's turn\n", i),"Player turn",JOptionPane.INFORMATION_MESSAGE);
                players.get(i).setActive(true);
                for (Consumer<Player> nextPlayerEventListener : nextPlayerEventListeners) {
                    nextPlayerEventListener.accept(players.get(i));
                }

                //Wait until player return
                try {
                    synchronized (playerLock) {
                        playerLock.wait();
                    }
                } catch (InterruptedException e) {
                    System.err.println("Player loop wait interrupted");
                    System.exit(-1);
                }
                players.get(i).setActive(false);

                //Run env events between players
                for (TurnBasedEvent event : events) {
                    event.doEvent(this.level, false);
                }
            }
            //Run env events after full circle
            for (TurnBasedEvent event : events) {
                event.doEvent(this.level, true);
            }
        }
    }

    public void playerTurnEndedHandler() {
        synchronized (playerLock) {
            playerLock.notifyAll();
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
                numberOfPlayers = count;
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
        System.out.println("INIT called");

        //Actually init events because they dont work without it xD
        events.add(new SnowStorm());
        events.add(new PolarBearStep());
        events.add(new RemoveTent());

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
