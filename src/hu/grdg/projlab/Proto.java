package hu.grdg.projlab;

import hu.grdg.projlab.util.ProtoInputSystem;
import hu.grdg.projlab.util.commands.ClearGameCommand;
import hu.grdg.projlab.util.commands.NewTileCommand;

public class Proto {
    public static void main(String[] args) {
        ProtoInputSystem pis = new ProtoInputSystem();

        //Register commands here
        pis.registerCommand("ClearGame", new ClearGameCommand());
        pis.registerCommand("NewTile", new NewTileCommand());


        //Run the tester
        pis.start();
    }
}
