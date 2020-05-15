package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.ItemRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RocketPartRenderer implements ItemRenderer {
    private int partType;
    private BufferedImage rocketPartImage;

    /**
     * Betölti a rakéta képét a típusa alapján
     * @param _partType A rakéta típusa
     */
    public RocketPartRenderer(int _partType) {
        partType = _partType;
        try {
            this.rocketPartImage = ImageIO.read(new File("img/RocketPart"+partType+".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Kirajzoltatja g-re a képet
     * ha Tile-ra kell rajzolni, akkor kisebbet rajzol
     * @param g A kirajzolás helye
     * @param isTile Mezőre kell-e rajzolni
     */
    @Override
    public void draw(Graphics2D g, boolean isTile) {
        if(!isTile)
            g.drawImage(rocketPartImage,5,5,45,45, null);
        else
            g.drawImage(rocketPartImage,15,15,30,30, null);
    }
}
