package hu.grdg.projlab;

import hu.grdg.projlab.gui.MainWindow;
import hu.grdg.projlab.util.ProtoInputSystem;
import hu.grdg.projlab.util.commands.*;
import hu.grdg.projlab.util.file.GameLoadException;
import hu.grdg.projlab.util.file.SavedGame;
import hu.grdg.projlab.util.file.TagIO;

import java.io.IOException;

public class Proto {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.create(null);
    }
}
