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

    public RocketPartRenderer(int _partType) {
        partType = _partType;
        try {
            this.rocketPartImage = ImageIO.read(new File("img/RocketPart"+partType+".png"));
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
        g.drawImage(rocketPartImage,5,5,45,45, null);
    }
}
