package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.model.BreakableShovel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BreakableShovelRenderer implements ItemRenderer {
    private BreakableShovel breakableShovel;
    private BufferedImage breakableShovel1Image;
    private BufferedImage breakableShovel2Image;
    private BufferedImage breakableShovel3Image;
    private BufferedImage brokenShovelImage;

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

    //TODO
    @Override
    public void draw(Graphics2D g, boolean isTile) {
        g.setColor(Color.WHITE);
        g.fillRect(5,5,45,45);
        int number = 3-breakableShovel.getUseCount();
        if(number == 3)
            g.drawImage(breakableShovel3Image,5,5,45,45, null);
        else if(number == 2)
            g.drawImage(breakableShovel2Image,5,5,45,45, null);
        else if(number == 1)
            g.drawImage(breakableShovel1Image,5,5,45,45, null);
        else if(number == 1)
            g.drawImage(breakableShovel1Image,5,5,45,45, null);
        else
            g.drawImage(brokenShovelImage,5,5,45,45, null);
    }
}
