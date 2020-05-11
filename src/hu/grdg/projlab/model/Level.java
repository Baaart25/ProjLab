package hu.grdg.projlab.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Level {
    private ArrayList<Tile> tiles;
    private ArrayList<RocketPart> rocketParts;

    public void generateLevel(ArrayList<Player> players) {

    }

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
                tiles.add(t);
            }
        }
        tiles.set(0,new IceTile());
        return tiles.get(0);
    }

    private void genItems() {

    }
    private void placePlayers(ArrayList<Player> players, IceTile startTile) {

    }
    private Tile placeItem(Item item) {
        return null;
    }

    /**
     * Generate a random Tile
     * @param hole true if the tile can be a HoleTile
     * @return the generated Tile
     * @author Dorina
     */
    private Tile genTile(boolean hole) {
        double pIce = 0.5, pUnstable = 1-pIce, pHole ;
        if(hole){
            pHole = 0;
        }
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