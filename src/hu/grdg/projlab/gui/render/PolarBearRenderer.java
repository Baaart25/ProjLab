package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.EntityRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PolarBearRenderer implements EntityRenderer {
    private BufferedImage polarBearImage;

    public PolarBearRenderer(){
        try {
            this.polarBearImage = ImageIO.read(new File("img/PolarBear.png"));
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(polarBearImage, 5, 5, 45, 45, null);
    }
}
