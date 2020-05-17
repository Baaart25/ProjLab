package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.ItemRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FoodRenderer implements ItemRenderer {
    //kép tárolása
    private BufferedImage foodImage;

    /**
     * Loads the image for food
     */
    public FoodRenderer() {
        try {
            this.foodImage = ImageIO.read(new File("img/Food.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Kirajzoltatja g-re a képet
     * ha Tile-ra kell rajzolni, akkor kisebbet rajzol
     * @param g Canvas for drawing
     * @param isTile True if its being drawn to tile
     */
    @Override
    public void draw(Graphics2D g, boolean isTile) {
        if(!isTile)
            g.drawImage(foodImage,5,5,45,45, null);
        else
            g.drawImage(foodImage,15,15,30,30, null);
    }
}
