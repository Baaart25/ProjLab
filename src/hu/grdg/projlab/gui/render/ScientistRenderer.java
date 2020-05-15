package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.EntityRenderer;
import hu.grdg.projlab.model.Scientist;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScientistRenderer implements EntityRenderer {
    private final Scientist player;
    private BufferedImage scientistImage;
    private BufferedImage scientistSelectedImage;
    private BufferedImage scientistOnHoleImage;
    private BufferedImage scientistOnHoleSelectedImage;

    public ScientistRenderer(Scientist player) {
        this.player = player;
        try {
            this.scientistImage = ImageIO.read(new File("img/Scientist.png"));
            this.scientistSelectedImage = ImageIO.read(new File("img/ScientistSelected.png"));
            this.scientistOnHoleImage = ImageIO.read(new File("img/ScientistOnHoleTile.png"));
            this.scientistOnHoleSelectedImage = ImageIO.read(new File("img/ScientistOnHoleTileSelected.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }


    /**
     * Draws the Eskimo based on its state
     * @param g Canvas for drawing
     * @param isActive True if this is the selected player
     * @param xOffset X position of the picture
     * @param yOffset Y position of the picture
     * @param isInWater True if the eskimo is in water
     */
    @Override
    public void draw(Graphics2D g, boolean isActive, int xOffset, int yOffset, boolean isInWater) {
        if (isInWater)
            if (player.isActive()) {
                g.drawImage(scientistOnHoleSelectedImage, xOffset, yOffset, 25, 25, null);
            } else {
                g.drawImage(scientistOnHoleImage, xOffset, yOffset, 25, 25, null);
            }
        else if (player.isActive()) {
            g.drawImage(scientistSelectedImage, xOffset, yOffset, 25, 25, null);
        } else {
            g.drawImage(scientistImage, xOffset, yOffset, 25, 25, null);
        }
    }
}
