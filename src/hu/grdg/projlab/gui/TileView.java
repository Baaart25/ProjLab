package hu.grdg.projlab.gui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class TileView extends JPanel {
    private final Color color;

    public TileView() {
        this.color = new Color(Color.HSBtoRGB(ThreadLocalRandom.current().nextFloat(),1.0f, 1.0f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(0,0,getWidth(), getHeight());
    }
}
