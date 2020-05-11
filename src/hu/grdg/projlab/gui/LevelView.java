package hu.grdg.projlab.gui;

import hu.grdg.projlab.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

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

        controller.init();
        ArrayList<Tile> tiles = controller.getLevel().getTiles();

        //Calculate the offset to center the tile map
        int xOffset = (getWidth() - (tileXCount * 50)) / 2;
        int yOffset = (getHeight() - (tileYCount * 50)) / 2;

        setBackground(new Color(0x4487D2));

        //Gen tiles
        for(int x = 0; x < tileXCount; x++) {
            for(int y = 0; y < tileYCount; y++) {

                /*IceTile tile = new IceTile();
                tile.addSnowLayer(ThreadLocalRandom.current().nextInt(0,2));

                for(int i = 0; i < y;i++) {
                    tile.acceptEntity(i % 2 == 0 ? new Eskimo(null) : new Scientist(null));
                }

                if(ThreadLocalRandom.current().nextBoolean())
                    tile.buildIgloo();
                if(ThreadLocalRandom.current().nextBoolean())
                    tile.buildTent();

                if(x % 2 == 0 && y % 2 == 0)
                    tile.setFrozenItem(new RocketPart(null, 1 + (y % 3)));*/

                TileView tw = new TileView(tiles.get(x + y * 20));
                tw.setSize(50,50);
                tw.setBounds(xOffset + x * 50, yOffset + y * 50, 50,50);

                this.add(tw);
            }
        }

        //Ez anno működött és nem mertem kitörölni xD (Dani)
        /*for(int x = 0; x < tileXCount; x++) {
            for(int y = 0; y < tileYCount; y++) {

                IceTile tile = new IceTile();
                tile.addSnowLayer(ThreadLocalRandom.current().nextInt(0,2));

                for(int i = 0; i < y;i++) {
                    tile.acceptEntity(i % 2 == 0 ? new Eskimo(null) : new Scientist(null));
                }

                if(ThreadLocalRandom.current().nextBoolean())
                    tile.buildIgloo();
                if(ThreadLocalRandom.current().nextBoolean())
                    tile.buildTent();

                if(x % 2 == 0 && y % 2 == 0)
                    tile.setFrozenItem(new RocketPart(null, 1 + (y % 3)));

                TileView tw = new TileView(tile);
                tw.setSize(50,50);
                tw.setBounds(xOffset + x * 50, yOffset + y * 50, 50,50);

                this.add(tw);
            }
        }


    }

}
