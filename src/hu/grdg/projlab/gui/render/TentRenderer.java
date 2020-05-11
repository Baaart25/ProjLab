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


    //TODO
    @Override
    public void draw(Graphics2D g, boolean isTile) {
        g.setColor(Color.WHITE);
        g.fillRect(5,5,45,45);
        g.drawImage(tentImage,5,5,45,45, null);
    }
}
