package hu.grdg.projlab.model;

import java.util.ArrayList;

public class Level {
    private ArrayList<Tile> tiles;
    private ArrayList<RocketPart> rocketParts;

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
    }

    private void placePlayers(ArrayList<Player> players, Tile startTile) {

    }
    private Tile placeItem(Item item) {

    }
    private Tile genTile() {
        return null;
    }

}