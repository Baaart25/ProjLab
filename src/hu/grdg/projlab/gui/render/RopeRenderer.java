package hu.grdg.projlab.gui.render;
import hu.grdg.projlab.gui.ItemRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RopeRenderer implements ItemRenderer {
    private BufferedImage ropeImage;

    public RopeRenderer() {
        try {
            this.ropeImage = ImageIO.read(new File("img/Rope.png"));
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
            g.drawImage(ropeImage,5,5,45,45, null);
        else
            g.drawImage(ropeImage,15,15,30,30, null);
    }
}
