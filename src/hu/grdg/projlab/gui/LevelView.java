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

        //Absolute positioning
        setLayout(null);

        //TODO Repalce with level data
        int tileXCount = 10;
        int tileYCount = 10;

        //Calculate the offset to center the tile map
        int xOffset = (getWidth() - (tileXCount * 50)) / 2;
        int yOffset = (getHeight() - (tileYCount * 50)) / 2;

        setBackground(Color.cyan);

        //Gen tiles
        for(int x = 0; x < tileXCount; x++) {
            for(int y = 0; y < tileYCount; y++) {
                TileView tw = new TileView();
                tw.setSize(50,50);
                tw.setBounds(xOffset + x * 50, yOffset + y * 50, 50,50);

                this.add(tw);
            }
        }
    }

}
