package hu.grdg.projlab.gui;

import hu.grdg.projlab.model.Controller;
import hu.grdg.projlab.model.Item;
import hu.grdg.projlab.model.Shovel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DataView extends JPanel {
    private final JScrollPane inventoryScrollPane;
    private final JList<Item> list;
    private Controller controller;

    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton specButton;

    private JLabel healthLabel;
    private JLabel workLabel;

    public DataView(Controller controller) {
        super();

        this.controller = controller;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Setup the view
        JLabel hlth = new JLabel("Testhő: ");
        healthLabel = new JLabel("3");
        JPanel hthPanel = new JPanel();
        hthPanel.setLayout(new BoxLayout(hthPanel, BoxLayout.X_AXIS));
        hthPanel.add(hlth);
        hthPanel.add(healthLabel);
        this.add(hthPanel);

        JLabel wrkl = new JLabel("Munkák: ");
        workLabel = new JLabel("2");
        JPanel wrkPanel = new JPanel();
        wrkPanel.setLayout(new BoxLayout(wrkPanel, BoxLayout.X_AXIS));
        wrkPanel.add(wrkl);
        wrkPanel.add(workLabel);
        this.add(wrkPanel);

        JPanel centerRow = new JPanel();
        centerRow.setLayout(new BoxLayout(centerRow, BoxLayout.X_AXIS));
        centerRow.setSize(95,95);

        leftButton = new JButton("B");
        leftButton.setSize(25,25);
        centerRow.add(leftButton);

        specButton = new JButton("SP");
        specButton.setSize(25,25);
        centerRow.add(specButton);

        rightButton = new JButton("J");
        rightButton.setSize(25,25);
        centerRow.add(rightButton);

        JPanel upRow = new JPanel();
        upRow.setLayout(new BoxLayout(upRow, BoxLayout.X_AXIS));
        upButton = new JButton("F");
        upButton.setSize(25,25);
        upRow.add(upButton);

        JPanel downRow = new JPanel();
        downRow.setLayout(new BoxLayout(downRow, BoxLayout.X_AXIS));
        downButton = new JButton("L");
        downButton.setSize(25,25);
        downRow.add(downButton);

        this.add(upRow);
        this.add(centerRow);
        this.add(downRow);


        //Item view

        list = new JList<Item>(new Item[]{new Shovel(), new Shovel(), new Shovel(), new Shovel(), new Shovel(), new Shovel(), new Shovel(), new Shovel(), new Shovel()});

        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setCellRenderer(new ItemViewCellRenderer());

        list.setFixedCellHeight(50);
        list.setFixedCellWidth(50);
        list.setVisibleRowCount(1);

        inventoryScrollPane = new JScrollPane(list);

        JPanel invPanel = new JPanel();
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.X_AXIS));
        invPanel.setMaximumSize(new Dimension(180,85));
        invPanel.add(inventoryScrollPane);
        this.add(invPanel);
    }

    private class ItemViewCellRenderer extends ItemView implements ListCellRenderer<Item> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Item> list, Item value, int index, boolean isSelected, boolean cellHasFocus) {
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setItem(value);

            //Idiot hack to not overdraw the scrollpane just because Java Swing is great
            Rectangle viewportBorderBounds = inventoryScrollPane.getViewport().getViewRect();
            int lastVisiblePixel = viewportBorderBounds.x + viewportBorderBounds.width;
            int firstVisibleImagePixel = viewportBorderBounds.x - ((viewportBorderBounds.x / 50)*50);
            int firstVisibleIndex = (viewportBorderBounds.x / 50);

            if(firstVisibleIndex == index)
                this.setStartVisibleX(firstVisibleImagePixel-5);
            else
                this.setStartVisibleX(0);

            this.setVisibleX(lastVisiblePixel - index * 50 - 5);

            //End of idiot hack

            this.setPreferredSize(new Dimension(50,50));
            return this;
        }
    }
}
