package hu.grdg.projlab.gui;

import hu.grdg.projlab.model.Tile;

import javax.swing.*;
import java.awt.*;

public class TileView extends JPanel {
    private static final int width = 50, height = 50;
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

    }
}
