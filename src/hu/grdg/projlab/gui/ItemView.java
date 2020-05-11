package hu.grdg.projlab.gui;

import hu.grdg.projlab.model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class ItemView extends JPanel {

    private Item item;
    private Color color = Color.red;
    private int lastX = 50;
    private int firstX = 0;

    public void setItem(Item item) {
        this.color = new Color(Color.HSBtoRGB(ThreadLocalRandom.current().nextFloat(), 1.0f, 1.0f));
        this.item = item;
    }

    @Override
    protected void paintComponent(Graphics go) {
        Graphics2D g = (Graphics2D) go;
        //For that stupid JList
        g.setClip(5 + firstX,5, lastX >= 50 ? getWidth() - 5 : lastX, getHeight() - 5);

        item.getRenderer().draw((Graphics2D) g,false);
    }

    protected void setVisibleX(int i) {
        this.lastX = i;
    }

    protected void setStartVisibleX(int offset) {
        firstX = offset;
    }
}
