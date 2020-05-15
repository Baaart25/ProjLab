package hu.grdg.projlab.gui.render;

import hu.grdg.projlab.gui.TileRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileRendererImpl implements TileRenderer {

    private boolean isHole = false;

    private static BufferedImage holeImage;
    private static BufferedImage iceImage;

    static {
        try {
            holeImage = ImageIO.read(new File("img/HoleTile.png"));
            iceImage = ImageIO.read(new File("img/IceTile.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Image load failed");
            System.exit(-1);
        }
    }

    /**
     * Sets the attribute isHole to the given param
     * @param isHole
     */
    public TileRendererImpl(boolean isHole) {
        this.isHole = isHole;
    }

    /**
     * Draw Tile to the given g Graphics
     * @param g the Graphics
     */
    @Override
    public void draw(Graphics2D g) {
        if(isHole)
            g.drawImage(holeImage, 0,0,50,50, null);
        else
            g.drawImage(iceImage, 0,0,50,50, null);
    }
}
