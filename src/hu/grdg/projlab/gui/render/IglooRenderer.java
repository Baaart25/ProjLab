package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.ItemRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IglooRenderer implements ItemRenderer {
    private BufferedImage iglooImage;

    /**
     * Loads the image for igloo
     */
    public IglooRenderer() {
        try {
            this.iglooImage = ImageIO.read(new File("img/Igloo.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Kirajzolja a képet
     * @param g Ahova rajzolja
     * @param isTile Mezőre kell-e rajzolni
     */
    @Override
    public void draw(Graphics2D g, boolean isTile) {
        if(!isTile)
            g.drawImage(iglooImage,5,5,45,45, null);
        else
            g.drawImage(iglooImage,20,15,27,27, null);
    }
}
