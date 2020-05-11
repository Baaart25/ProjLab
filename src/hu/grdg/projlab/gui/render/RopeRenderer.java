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

    @Override
    public void draw(Graphics2D g, boolean isTile) {
        g.setColor(Color.WHITE);
        g.fillRect(5,5,45,45);
        g.drawImage(ropeImage,5,5,45,45, null);
    }
}
