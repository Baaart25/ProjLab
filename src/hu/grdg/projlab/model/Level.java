package hu.grdg.projlab.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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

    /**
     *Generate the tiles of the level
     * @param w the width of the table
     * @param h the height of the table
     * @return the IceTile where the players begin the game
     * @author Dorina
     */
    private Tile genTiles(int w, int h) {
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                boolean hole = true;
                Tile n1=null,n2=null,t;
                if(i>0) {
                    n1 = tiles.get(j * w + i - 1);
                    if (n1.scanLimit() == 0) hole = false;
                }
                if(j>0) {
                    n2 = tiles.get((i - 1) * w + i);
                    if (n2.scanLimit() == 0) hole = false;
                }
                t = genTile(hole);
                if(n1!=null) {
                    t.setNeighbour(n1, 3);
                    n1.setNeighbour(t, 1);
                }
                if(n2!=null){
                    t.setNeighbour(n2, 0);
                    n2.setNeighbour(t,2);
                }
                t.addSnowLayer(ThreadLocalRandom.current().nextInt(4));
                tiles.add(t);
            }
        }
        tiles.set(0,new IceTile());
        return tiles.get(0);
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

    /**
     * Place players to the start tile
     * @param players the players in the game
     * @param startTile the tile where the players start the game
     */
    private void placePlayers(ArrayList<Player> players, Tile startTile) {
        for (Player player : players) {
            player.setCurrentTile(startTile);
            startTile.acceptEntity(player);
        }
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

    /**
     * Generate a random Tile
     * @param hole true if the tile can be a HoleTile
     * @return the generated Tile
     * @author Dorina
     */
    private Tile genTile(boolean hole) {
        double pIce = 0.5, pUnstable, pHole = 0.25 ;
        if(hole){
            pHole = 0;
        }
        pUnstable = 1 - pIce - pHole;
        double rnd = Math.random();
        Tile tile;
        if(rnd<pIce){
            tile = new IceTile();
        }else if(rnd<pIce+pUnstable){
            tile = new UnstableIceTile(ThreadLocalRandom.current().nextInt(4));
        }else {
            tile = new HoleTile();
        }
        return tile;

    }

}