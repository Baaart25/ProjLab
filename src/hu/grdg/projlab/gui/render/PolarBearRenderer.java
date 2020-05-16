package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.EntityRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PolarBearRenderer implements EntityRenderer {
    private BufferedImage polarBearImage;

    /**
     * Betölti a jegesmedve képét
     */
    public PolarBearRenderer(){
        try {
            this.polarBearImage = ImageIO.read(new File("img/PolarBear.png"));
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Kirazoltatja g-re a képet
     * @param g A kirajzolás helye
     * @param isActive Ebben az esetben nem csinál semmit
     * @param xOffset X pozíciója a képnek
     * @param yOffset Y pozíciója
     * @param isInWater Ebben az esetben nem csinál semmit
     */
    @Override
    public void draw(Graphics2D g, boolean isActive, int xOffset, int yOffset,boolean isInWater){
        g.drawImage(polarBearImage, 5, 5, 45, 45, null);
    }
}
