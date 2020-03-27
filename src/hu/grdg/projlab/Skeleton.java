package hu.grdg.projlab;

import hu.grdg.projlab.model.*;

import java.util.ArrayList;

public class Skeleton {
    public static void main(String[] args) {
        //Register tests below
        SkeletonTester.registerTest("PlayerStepOnUnstableIceTile", Skeleton::playerStepOnUnsatbleIceTile);
        SkeletonTester.registerTest("ScanLimitHoleTile", Skeleton::scanLimitHoleTile);
        SkeletonTester.registerTest("ScanLimitIceTile", Skeleton::scanLimitIceTile);
        SkeletonTester.registerTest("ScanLimitUnstableIceTile", Skeleton::scanLimitUnstableIceTile);
        SkeletonTester.registerTest("UnfreezeItem", Skeleton::unfreezeItem);
        SkeletonTester.registerTest("RemoveSnowLayer", Skeleton::RemoveSnowLayer);
        SkeletonTester.registerTest("DieByDrown",Skeleton::DieByDrown);
        SkeletonTester.registerTest("SnowStorm", Skeleton::snowStorm);
        SkeletonTester.registerTest("BuildIgloo", Skeleton::BuildIgloo);
        SkeletonTester.registerTest("RemoveSnowWithShovel",Skeleton::RemoveSnowWithShovel);


        //Run testing
        SkeletonTester.start();
    }

    /**
     * This is testing a player's step to an UnstableIceTile
     * @author Barrow099
     */
    private static void playerStepOnUnsatbleIceTile() {
        //Start new test tracking in SkeletonTester
        //Must be closed with endTest()
        SkeletonTester.beginTest("PlayerStepOnUnstableIceTile");

        int limit = SkeletonTester.askNumber("UnstableIceTile limit: ");
        int pc = SkeletonTester.askNumber("UnstableIceTile player count: ");
        UnstableIceTile ut = new UnstableIceTile(limit, pc);

        //Registers a named reference to the current test
        //It is used for printing arguments
        SkeletonTester.addNamedReference(ut,"ut");

        Scientist sc = new Scientist();
        SkeletonTester.addNamedReference(sc,"sc");

        //Call methods here
        //Make sure to document method calls in the methods
        ut.acceptPlayer(sc);

        SkeletonTester.endTest();
    }

    /**
     * Unfreeze a frozen item
     * @author Barrow099
     */
    private static void unfreezeItem() {
        SkeletonTester.beginTest("UnfreezeItem");

        IceTile it = new IceTile();
        Shovel sh = new Shovel();
        Scientist sc = new Scientist();

        SkeletonTester.addNamedReference(it, "it");
        SkeletonTester.addNamedReference(sh, "sh");
        SkeletonTester.addNamedReference(sc, "sc");

        //Setup
        it.setFrozenItem(sh);
        it.acceptPlayer(sc);

        //Actual test
        sc.unfreezeItem();

        SkeletonTester.endTest();
    }

    /**
     * Scientist scan a HoleTile
     * @author Dorina
     */
    private static void scanLimitHoleTile(){
        SkeletonTester.beginTest("ScanLimitHoleTile");

        Scientist sc = new Scientist();
        SkeletonTester.addNamedReference(sc, "sc");
        IceTile tile = new IceTile();
        SkeletonTester.addNamedReference(tile, "tile");
        HoleTile hole = new HoleTile();
        SkeletonTester.addNamedReference(hole, "hole");

        tile.setNeighbour(hole, Direction.EAST);

        sc.setCurrentTile(tile);

        sc.specialAbility();

        SkeletonTester.endTest();
    }


    /**
     * Scientist scan IceTile
     * @author Dorina
     */
    private static void scanLimitIceTile(){
        SkeletonTester.beginTest("ScanLimitIceTile");

        Scientist sc = new Scientist();
        SkeletonTester.addNamedReference(sc, "sc");
        IceTile tile = new IceTile();
        SkeletonTester.addNamedReference(tile, "tile");
        IceTile ice = new IceTile();
        SkeletonTester.addNamedReference(ice, "ice");

        tile.setNeighbour(ice, Direction.EAST);

        sc.setCurrentTile(tile);

        sc.specialAbility();

        SkeletonTester.endTest();
    }

    /**
     * Scientist scan UnstableIceTile
     * @author Dorina
     */
    private static void scanLimitUnstableIceTile(){
        SkeletonTester.beginTest("ScanLimitUnstableIceTile");

        int limit = SkeletonTester.askNumber("UnstableIceTile limit: ");

        Scientist sc = new Scientist();
        SkeletonTester.addNamedReference(sc, "sc");
        IceTile tile = new IceTile();
        SkeletonTester.addNamedReference(tile, "tile");
        UnstableIceTile unstable = new UnstableIceTile(limit);
        SkeletonTester.addNamedReference(unstable, "ice");

        tile.setNeighbour(unstable, Direction.EAST);

        sc.setCurrentTile(tile);

        sc.specialAbility();

        SkeletonTester.endTest();
    }

    /**
     * Snowstorm damage tiles and players
     * @author Dorina
     */
    private static void snowStorm(){
        SkeletonTester.beginTest("SnowStorm");

        SnowStorm storm = new SnowStorm();
        SkeletonTester.addNamedReference(storm, "storm");

        Level lvl = new Level();
        SkeletonTester.addNamedReference(lvl, "lvl");

        Eskimo e = new Eskimo();
        SkeletonTester.addNamedReference(e,"e");

        lvl.genTiles(1,1);
        ArrayList<Tile> tiles = lvl.getTiles();
        Tile tile = tiles.get(0);
        SkeletonTester.addNamedReference(tile, "tile");
        tile.acceptPlayer(e);
        storm.doEvent(lvl);

        SkeletonTester.endTest();
    }

    /**
     * Az eszkimo belefullad a vízbe
     * @author Boti
     */
    private static void DieByDrown(){
        SkeletonTester.beginTest("DieByDrown");

        HoleTile h = new HoleTile();
        SkeletonTester.addNamedReference(h,"h");
        Eskimo e = new Eskimo();
        SkeletonTester.addNamedReference(e,"e");
        Controller c = new Controller();
        SkeletonTester.addNamedReference(c, "c");

        e.setController(c);

        h.acceptPlayer(e);
        e.die();

        SkeletonTester.endTest();
    }
    /**
     * Remove snow layer without shovel
     * @author Geri
     */
    private static void RemoveSnowLayer(){
        SkeletonTester.beginTest("RemoveSnowLayer");

        int layers = SkeletonTester.askNumber("Number of snow layers on tile: ");

        Eskimo e = new Eskimo();
        SkeletonTester.addNamedReference(e,"e");
        IceTile it = new IceTile(layers);
        SkeletonTester.addNamedReference(it,"it");
        it.removeSnowLayer(1);

        SkeletonTester.endTest();
    }


    /**
     * Eskimo build igloo
     * @author Dorina
     */
    private static void BuildIgloo(){
        SkeletonTester.beginTest("BuildIgloo");

        Eskimo e = new Eskimo();
        SkeletonTester.addNamedReference(e,"e");

        IceTile currentTile = new IceTile();
        SkeletonTester.addNamedReference(currentTile, "currentTile");

        e.setCurrentTile(currentTile);

        e.specialAbility();

        SkeletonTester.endTest();
    }
    /**
     * Removing 2 layer of snow with shovel
     * @author Boti
     */
    private static void RemoveSnowWithShovel(){
        SkeletonTester.beginTest("RemoveSnowWithShovel");

        int layers = SkeletonTester.askNumber("Number of snow layers on tile: ");

        Shovel sh = new Shovel();
        SkeletonTester.addNamedReference(sh,"sh");
        Scientist owner = new Scientist();
        SkeletonTester.addNamedReference(owner,"owner");
        IceTile currentTile = new IceTile(layers);
        SkeletonTester.addNamedReference(currentTile,"currentTile");

        owner.setCurrentTile(currentTile);
        sh.setOwner(owner);
        sh.useItem();


        SkeletonTester.endTest();
    }
}
