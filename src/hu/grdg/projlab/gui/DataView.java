package hu.grdg.projlab.gui;

import hu.grdg.projlab.model.Controller;

import javax.swing.*;
import java.awt.*;

public class DataView extends JPanel {
    private Controller controller;

    public DataView(Controller controller) {
        super();

        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0,0,getWidth(), getHeight());
    }
}
