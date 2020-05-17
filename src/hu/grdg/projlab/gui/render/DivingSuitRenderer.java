package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.ItemRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * DivingSuithoz tartozó renderer
 */
public class DivingSuitRenderer implements ItemRenderer {
    //kép tárolása
    private BufferedImage divingSuitImage;

    /**
     * Beolvassa a megfelelő képet
     */
    public DivingSuitRenderer() {
        try {
            this.divingSuitImage = ImageIO.read(new File("img/DivingSuit.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Kirajzoltatja g-re a képet
     * ha Tile-ra kell rajzolni, akkor kisebbet rajzol
     * @param g
     * @param isTile
     */
    @Override
    public void draw(Graphics2D g, boolean isTile) {
        if(!isTile)
            g.drawImage(divingSuitImage,5,5,45,45, null);
        else
            g.drawImage(divingSuitImage,15,15,30,30, null);
    }
}
