package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.ItemRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TentRenderer implements ItemRenderer {
    private BufferedImage tentImage;

    public TentRenderer() {
        try {
            this.tentImage = ImageIO.read(new File("img/Tent.png"));
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
            g.drawImage(tentImage,5,5,45,45, null);
        else
            g.drawImage(tentImage,5,5,30,30, null);
    }
}
