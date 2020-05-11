package hu.grdg.projlab.model;

import java.util.ArrayList;

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

    private IceTile genTiles(int w, int h) {
        return null;
    }

    private void genItems() {

    }
    private void placePlayers(ArrayList<Player> players, ArrayList<Tile> startTile) {

    }
    private Tile placeItem(Item item) {
        return null;
    }
    private Tile genTile() {
        return null;
    }

}