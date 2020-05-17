package hu.grdg.projlab.model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Level {
    //tárolja a mezőket amelyeket legenerál
    private ArrayList<Tile> tiles;
    //ismeri a controllert
    private Controller controller;
    private static int size = 10;

    /**
     * Konstruktor, paraméterként kapott controllert beállítja
     * @author Dani
     * @param c kontroller
     */
    Level(Controller c) {
        controller = c;
        tiles = new ArrayList<Tile>();
    }

    /**
     * Legenerálja a levelt
     * @author Dani
     * @param players
     */
    public void generateLevel(ArrayList<Player> players) {
        Tile startTile = genTiles();
        genItems();
        placePlayers(players, startTile);
        createPolarBears();
    }

    /**
     * Create a PolarBear and place it somewhere
     * @author Dorina
     */
    private void createPolarBears(){
        PolarBear bear = new PolarBear();
        int x = ThreadLocalRandom.current().nextInt(1,10), y = ThreadLocalRandom.current().nextInt(1,10);
        bear.setCurrentTile(tiles.get(y*size+x));
        tiles.get(y*size+x).acceptEntity(bear);
        PolarBearStep.getInstance().addPolarBear(bear);
    }

    /**
     * Visszaadja a tile-okat
     * @author Dani
     * @return
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    /**
     *Generate the tiles of the level
     * @return the IceTile where the players begin the game
     * @author Dorina
     */
    private Tile genTiles() {
        for(int j = 0; j < size; j++){
            for(int i = 0; i < size; i++){
                boolean hole = true;
                Tile n1=null,n2=null,t;
                if(i>0) {
                    n1 = tiles.get(j * size + i - 1);
                    if (n1.scanLimit() == 0) hole = false;
                    if(j>0){
                        if(tiles.get((j-1)*size+i-1).scanLimit()==0) hole=false;
                    }

                }
                if(j>0) {
                    n2 = tiles.get((j - 1) * size + i);
                    if (n2.scanLimit() == 0) hole = false;
                    if(i<size-1){
                        if(tiles.get((j-1)*size + i + 1).scanLimit()==0) hole = false;
                    }
                }
                if(i==0 && j ==0){
                    t = new IceTile();
                }else {
                    t = genTile(hole);
                }
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
            controller.addRocketPart(item);
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
        int x, y;
        do {
            x = ThreadLocalRandom.current().nextInt(0, size); //sor
            y = ThreadLocalRandom.current().nextInt(0, size); //oszlop
            tile = tiles.get(x + y * 10);
        } while(!tile.setFrozenItem(item));
        return tile;
    }

    /**
     * Generate a random Tile
     * @param hole true if the tile can be a HoleTile
     * @return the generated Tile
     * @author Dorina
     */
    private Tile genTile(boolean hole) {
        double pIce = 0.6, pUnstable, pHole = 0.20;
        if(!hole){
            pHole = 0;
        }
        pUnstable = 1 - pIce - pHole;
        double rnd = Math.random();
        Tile tile;
        if(rnd<pIce){
            tile = new IceTile();
        }else if(rnd<pIce+pUnstable){
            tile = new UnstableIceTile(ThreadLocalRandom.current().nextInt(1,6));
        }else {
            tile = new HoleTile();
        }
        return tile;
    }
}