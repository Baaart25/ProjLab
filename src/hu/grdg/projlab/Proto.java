package hu.grdg.projlab;

import hu.grdg.projlab.gui.MainWindow;
import hu.grdg.projlab.model.Controller;


public class Proto {
    public static void main(String[] args) {
        Controller c = new Controller();
        MainWindow mainWindow = new MainWindow(c);
        mainWindow.create();
    }
}
