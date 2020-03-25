package hu.grdg.projlab;

import hu.grdg.projlab.model.IceTile;
import hu.grdg.projlab.model.Scientist;

public class TestTest {
    public static void run() {
        SkeletonTester.beginTest("TestTest");

        Scientist scientist = new Scientist();
        SkeletonTester.addNamedReference(scientist, "scientist");

        IceTile ict = new IceTile();
        SkeletonTester.addNamedReference(ict, "startTile");

        ict.acceptPlayer(scientist);
        scientist.specialAbility();

        SkeletonTester.endTest();
    }
}
