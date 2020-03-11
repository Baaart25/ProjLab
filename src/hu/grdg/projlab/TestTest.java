package hu.grdg.projlab;

import hu.grdg.projlab.model.IceTile;
import hu.grdg.projlab.model.Scientist;

public class TestTest {
    public static void run() {
        Skeleton.beginTest("TestTest");

        Scientist scientist = new Scientist();
        Skeleton.addNamedReference(scientist, "scientist");

        IceTile ict = new IceTile();
        Skeleton.addNamedReference(ict, "startTile");

        ict.acceptPlayer(scientist);
        scientist.specialAbility();

        Skeleton.endTest();
    }
}
