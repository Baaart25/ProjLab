package hu.grdg.projlab;

import hu.grdg.projlab.model.*;

public class Skeleton {
    public static void main(String[] args) {
        //Register tests below
        SkeletonTester.registerTest("PlayerStepOnUnstableIceTile", Skeleton::playerStepOnUnsatbleIceTile);
        SkeletonTester.registerTest("ScanLimitHoleTile", Skeleton::scanLimitHoleTile);
        SkeletonTester.registerTest("ScanLimiIceTile", Skeleton::scanLimitIceTile);
        SkeletonTester.registerTest("ScanLimitUnstableIceTile", Skeleton::scanLimitUnstableIceTile);




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

        Scientist sc = new Scientist();
        SkeletonTester.addNamedReference(sc, "sc");
        IceTile tile = new IceTile();
        SkeletonTester.addNamedReference(tile, "tile");
        UnstableIceTile unstable = new UnstableIceTile();
        SkeletonTester.addNamedReference(unstable, "ice");

        tile.setNeighbour(unstable, Direction.EAST);

        sc.setCurrentTile(tile);

        sc.specialAbility();

        SkeletonTester.endTest();
    }

}
