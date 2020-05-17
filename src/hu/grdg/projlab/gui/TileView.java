package hu.grdg.projlab.gui;

import hu.grdg.projlab.debug.DebugSettings;
import hu.grdg.projlab.gui.render.IglooRenderer;
import hu.grdg.projlab.gui.render.TentRenderer;
import hu.grdg.projlab.model.Entity;
import hu.grdg.projlab.model.Tile;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TileView extends JPanel {
    private static final int width = 50, height = 50;
    private static BufferedImage imgSnowLayer;
    private static TentRenderer tentRenderer = new TentRenderer();
    private static IglooRenderer iglooRenderer = new IglooRenderer();
    private static BufferedImage imgIceLayer;

    static {
        try {
            imgSnowLayer = ImageIO.read(new File("img/TileWithSnow.png"));
            imgIceLayer = ImageIO.read(new File("img/IceLayer.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private Tile tile;

    public TileView(Tile tile) {
        if(tile == null)
            throw new RuntimeException("DO NOT USE FUCKIN NULLS");

        this.tile = tile;
        this.tile.addOnUpdateListener(() -> {
            this.repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Paint base
        g.setClip(0,0,width, height);
        Graphics2D g2 = (Graphics2D) g;
        tile.getRenderer().draw(g2);

        if(DebugSettings.DEBUG_SHOW_ALL_ITEMS) {
            if(tile.getSnowLayers() > 0) {
                g.drawImage(imgSnowLayer, 0,0,50,50, null);
            }

            if(tile.getFrozenItem() != null) {
                tile.getFrozenItem().getRenderer().draw(g2, true);

                if(tile.getFrozenItem().isFrozen()) {
                    g.drawImage(imgIceLayer, 0,0,50,50, null);
                }
            }
        } else {
            if (tile.getFrozenItem() != null) {
                tile.getFrozenItem().getRenderer().draw(g2, true);

                if (tile.getFrozenItem().isFrozen()) {
                    g.drawImage(imgIceLayer, 0, 0, 50, 50, null);
                }
            }

            if (tile.getSnowLayers() > 0) {
                g.drawImage(imgSnowLayer, 0, 0, 50, 50, null);
            }
        }



        if(tile.hasTent()) {
            tentRenderer.draw((Graphics2D) g, true);
        }

        if(tile.hasIgloo()) {
            iglooRenderer.draw((Graphics2D) g, true);
        }

        //Pleas do not touch
        //IDK why its working
        ArrayList<Entity> entities = tile.getEntities();
        int count = (int) Math.ceil(Math.sqrt(entities.size()));
        for(int i = 0; i < entities.size(); i++) {
            int yd = Math.floorDiv(i, count);
            int xd = i - (yd * count);

            int gap = 40 / count;

            int xOffset = xd * gap;
            int yOffset = yd * gap;

            xOffset = clamp(xOffset, 5, 50);
            yOffset = clamp(yOffset, 0, 50);

            Entity e = entities.get(i);
            e.getRenderer().draw((Graphics2D) g, false, xOffset, yOffset, tile.scanLimit() == 0);
        }

        //DEBUG DRAW
        if(DebugSettings.DEBUG_DRAW) {
            String text = "T";
            if(tile.scanLimit() == 0) {
                text = "H";
            }else if(tile.scanLimit() > 0) {
                text = String.valueOf(tile.scanLimit());
            }

            g.setColor(Color.black);
            g.drawString(text,35,40);
        }

    }


    private static int clamp(int num, int min, int max) {
        return num > max ? max : num < min ? min : num;
    }
}
