package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

import java.util.HashMap;

/**
 * Base class for map tiles
 */
public abstract class Tile {
    private HashMap<Direction, Tile> neighbours;

    public Tile(){
        neighbours=new HashMap<>();
    }

    public void acceptPlayer(Player player) {
        SkeletonTester.call(this, player);
        player.currentTile = this;
        SkeletonTester.creturn();
    }

    public void buildIgloo() {
        SkeletonTester.call(this, null);

        SkeletonTester.creturn();
    }

    public abstract int scanLimit();

    public void setNeighbour(Tile nb, Direction d){
        SkeletonTester.call(this, nb, d);
        neighbours.put(d, nb);
        SkeletonTester.creturn();
    }

    public Tile getNeighbour(Direction d){
        SkeletonTester.call(this, d);
        SkeletonTester.creturn();
        return neighbours.get(d);

    }


}
