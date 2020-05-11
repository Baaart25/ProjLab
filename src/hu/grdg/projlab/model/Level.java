package hu.grdg.projlab.model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Level {
    private ArrayList<Tile> tiles;
    private ArrayList<RocketPart> rocketParts;
    private Controller controller;

    /**
     * Konstruktor, paraméterként kapott controllert beállítja
     * @author Dani
     * @param c kontroller
     */
    Level(Controller c) {
        controller = c;
    }

    /**
     * Legenerálja a levelt
     * @author Dani
     * @param players
     */
    public void generateLevel(ArrayList<Player> players) {
        Tile startTile = genTiles(10, 10);
        genItems();
        placePlayers(players, startTile);
    }

    /**
     * Visszaadja a tile-okat
     * @author Dani
     * @return
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<RocketPart> getRocketParts() {
        return rocketParts;
    }

    private Tile genTiles(int w, int h) {
        return null;
    }

    /**
     * Legenerálja a tárgyakat
     * @author Dani
     */
    private void genItems() {
        //Food
        for(int i = 0; i < 10; i++) {
            Item item = new Food();
            placeItem(item);
        }
        //Rope
        for(int i = 0; i < 5; i++) {
            Item item = new Rope();
            placeItem(item);
        }
        //DivingSuit
        for(int i = 0; i < 5; i++) {
            Item item = new DivingSuit();
            placeItem(item);
        }
        //Shovel
        for(int i = 0; i < 10; i++) {
            Item item = new Shovel();
            placeItem(item);
        }
        //BrokenBreakableShovel
        for(int i = 0; i < 5; i++) {
            Item item = new BreakableShovel();
            placeItem(item);
        }
        //Tent
        for(int i = 0; i < 5; i++) {
            Item item = new Tent();
            placeItem(item);
        }
        //RocketParts
        for(int i = 0; i < 3; i++) {
            RocketPart item = new RocketPart(controller, i + 1);
            placeItem(item);
            rocketParts.add(item);
        }
    }

    private void placePlayers(ArrayList<Player> players, Tile startTile) {

    }

    /**
     * Addig próbálkozik egy paraméterként kapott item elhelyezésével, amíg az sikeres nem lesz
     * @author Dani
     * @param item
     */
    private Tile placeItem(Item item) {
        Tile tile;
        do {
            int x = ThreadLocalRandom.current().nextInt(0, 20); //sor
            int y = ThreadLocalRandom.current().nextInt(0, 20); //oszlop
            tile = tiles.get(x + y * 20);
        } while(tile.setFrozenItem(item));
        return tile;
    }

    private Tile genTile() {
        return null;
    }

}