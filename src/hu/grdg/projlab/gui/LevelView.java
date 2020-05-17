package hu.grdg.projlab.gui;

import hu.grdg.projlab.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LevelView extends JPanel {
    private Controller controller;

    public LevelView(Controller c) {
        super();

        this.controller = c;
        controller.addOnGameStartListener(() -> {
            this.regenTileMap();
        });
        setSize(600, 525);

        //Absolute positioning
        setLayout(null);

        setBackground(new Color(0x4487D2));

        if(controller.getLevel() == null)
            return;

        regenTileMap();
    }

    private void regenTileMap() {
        this.removeAll();
        ArrayList<Tile> tiles = controller.getLevel().getTiles();
        int tileXCount = (int) Math.sqrt(tiles.size());
        int tileYCount = (int) Math.sqrt(tiles.size());

        //Calculate the offset to center the tile map
        int xOffset = (getWidth() - (tileXCount * 50)) / 2;
        int yOffset = (getHeight() - (tileYCount * 50)) / 2;

        //Gen tiles
        for(int y = 0; y < tileYCount; y++) {
            for(int x = 0; x < tileXCount; x++) {

                Tile tile = tiles.get(y * tileYCount + x);
                TileView tw = new TileView(tile);
                tw.setSize(50,50);
                tw.setBounds(xOffset + x * 50, yOffset + y * 50, 50,50);

                this.add(tw);
            }
        }
        this.repaint();
        this.invalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
