package hu.grdg.projlab.gui;

import hu.grdg.projlab.model.Controller;

import javax.swing.*;
import java.awt.*;

public class LevelView extends JPanel {
    private Controller controller;

    public LevelView(Controller c) {
        super();

        this.controller = c;
        setSize(600, 525);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.cyan);
        g2d.fillRect(0,0,getWidth(),getHeight());

        super.paintComponent(g);

    }
}
