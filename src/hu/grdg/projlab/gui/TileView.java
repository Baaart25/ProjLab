package hu.grdg.projlab.gui;

import com.sun.org.apache.xml.internal.security.utils.IgnoreAllErrorHandler;
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

    static {
        try {
            imgSnowLayer = ImageIO.read(new File("img/TileWithSnow.png"));
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Paint base
        g.setClip(0,0,width, height);
        Graphics2D g2 = (Graphics2D) g;
        tile.getRenderer().draw(g2);

        if(tile.getFrozenItem() != null) {
            tile.getFrozenItem().getRenderer().draw(g2, true);
        }

        //FIXME Uncomment if asset is done
        /*
        if(tile.getFrozenItem().isFrozen()) {
            g.drawImage(imgIceLayer, 0,0,50,50, null);
        }
         */

        if(tile.getSnowLayers() > 0) {
            g.drawImage(imgSnowLayer, 0,0,50,50, null);
        }

        if(tile.hasTent()) {
            tentRenderer.draw((Graphics2D) g, true);
        }

        if(tile.hasIgloo()) {
            iglooRenderer.draw((Graphics2D) g, true);
        }

        ArrayList<Entity> entities = tile.getEntities();
        double gap = (2 * Math.PI) / entities.size();
        for(int i = 0; i < entities.size(); i++) {
            double phi = gap * i;
            double xd = Math.cos(phi);
            double yd = Math.sin(phi);

            int xOffset = (int) (25 + (xd * 25));
            int yOffset = (int) (25 + (yd * 25));

            Entity e = entities.get(i);
            e.getRenderer().draw((Graphics2D) g, false, xOffset, yOffset);

        }

    }
}
