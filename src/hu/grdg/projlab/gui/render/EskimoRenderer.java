package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.EntityRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EskimoRenderer implements EntityRenderer {
    private BufferedImage eskimoImage;
    private BufferedImage eskimoSelectedImage;
    private BufferedImage eskimoOnHoleImage;
    private BufferedImage eskimoOnHoleSelectedImage;

    /**
     * Loads the images for Eskimo
     */
    public EskimoRenderer(){
        try {
            this.eskimoImage = ImageIO.read(new File("img/Eskimo.png"));
            this.eskimoSelectedImage = ImageIO.read(new File("img/EskimoSelected.png"));
            this.eskimoOnHoleImage = ImageIO.read(new File("img/EskimoOnHoleTile.png"));
            this.eskimoOnHoleSelectedImage = ImageIO.read(new File("img/EskimoOnHoleTileSelected.png"));
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
    public void draw(Graphics2D g, boolean isActive, int xOffset, int yOffset, boolean isInWater){
        if (isInWater)
            if (isActive) {
                g.drawImage(eskimoOnHoleSelectedImage, xOffset, yOffset, 25, 25, null);
            } else {
                g.drawImage(eskimoOnHoleImage, xOffset, yOffset, 25, 25, null);
            }
        else if (isActive) {
            g.drawImage(eskimoSelectedImage, xOffset, yOffset, 25, 25, null);
        } else {
            g.drawImage(eskimoImage, xOffset, yOffset, 25, 25, null);
        }
    }
}
