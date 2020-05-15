package hu.grdg.projlab;

import hu.grdg.projlab.gui.MainWindow;
import hu.grdg.projlab.model.Controller;


public class Proto implements Thread.UncaughtExceptionHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Proto());

        Controller c = new Controller();
        MainWindow mainWindow = new MainWindow(c);
        mainWindow.create();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //CATCH THE ALL
        //ONE HANDLER TO RULE THEM ALL
        //DO NOTHING
    }
}
