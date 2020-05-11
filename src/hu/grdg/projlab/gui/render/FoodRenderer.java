package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.ItemRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FoodRenderer implements ItemRenderer {
    private BufferedImage foodImage;

    public FoodRenderer() {
        try {
            this.foodImage = ImageIO.read(new File("img/Food.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    //TODO
    @Override
    public void draw(Graphics2D g, boolean isTile) {
        g.drawImage(foodImage,5,5,45,45, null);
    }
}
