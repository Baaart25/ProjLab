package hu.grdg.projlab.gui;

import java.awt.*;

public interface EntityRenderer {
    void draw(Graphics2D g, boolean isActive, int xOffset, int yOffset, boolean isInWater);
}
