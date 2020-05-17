package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.model.BreakableShovel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * BreakableShovelhez tartozó renderer
 */
public class BreakableShovelRenderer implements ItemRenderer {
    //kép tárolása
    private BreakableShovel breakableShovel;
    //kép1 tárolása
    private BufferedImage breakableShovel1Image;
    //kép2 tárolása
    private BufferedImage breakableShovel2Image;
    //kép3 tárolása
    private BufferedImage breakableShovel3Image;
    //kép tárolása
    private BufferedImage brokenShovelImage;

    /**
     * Az átvett shovelt elmenti tagváltozóba
     * beolvassa az összes breakableshovel képet
     * @param shovel
     */
    public BreakableShovelRenderer(BreakableShovel shovel) {
        try {
            breakableShovel = shovel;
            this.breakableShovel1Image = ImageIO.read(new File("img/BreakableShovel1.png"));
            this.breakableShovel2Image = ImageIO.read(new File("img/BreakableShovel2.png"));
            this.breakableShovel3Image = ImageIO.read(new File("img/BreakableShovel3.png"));
            this.brokenShovelImage = ImageIO.read(new File("img/BrokenBreakableShovel.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * g-re rajzolja a megfelelő képet
     * ha Tile-ra kell rajzolni, akkor kisebbet rajzol
     * @param g
     * @param isTile
     */
    @Override
    public void draw(Graphics2D g, boolean isTile) {
        int number = 3-breakableShovel.getUseCount();
        BufferedImage img;
        if(number == 3)
            img = breakableShovel3Image;
        else if(number == 2)
            img = breakableShovel2Image;
        else if(number == 1)
            img = breakableShovel1Image;
        else
            img = brokenShovelImage;

        if(!isTile)
            g.drawImage(img, 5,5,45,45, null);
        else
            g.drawImage(img,15,15,30,30, null);
    }
}
