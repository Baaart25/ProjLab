package hu.grdg.projlab.gui;

import hu.grdg.projlab.model.*;

import javax.swing.*;
import java.awt.*;

public class DataView extends JPanel {
    private final JScrollPane inventoryScrollPane;
    private final JList<Item> list;
    private final Runnable onPlayerTurnEnd;
    private final JButton unfreezeButton;
    private final JButton pickupButton;
    private final JButton skipButton;
    private final JButton digButton;
    private Controller controller;

    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton specButton;

    private JLabel healthLabel;
    private JLabel workLabel;

    private Player currentPlayer = null;
    private int playerRemActions = 4;

    public DataView(Controller controller) {
        super();

        this.controller = controller;

        this.controller.addOnNextPlayerListner(this::nextPlayer);
        this.onPlayerTurnEnd = this.controller::playerTurnEndedHandler;

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

        unfreezeButton = new JButton("Unfreeze");
        pickupButton = new JButton("Pickup");
        JPanel bpanel = new JPanel();
        bpanel.setLayout(new BoxLayout(bpanel, BoxLayout.X_AXIS));
        bpanel.add(unfreezeButton);
        bpanel.add(pickupButton);
        this.add(bpanel);

        digButton = new JButton("DIG");
        this.add(digButton);

        skipButton = new JButton("Pass");
        this.add(skipButton);
        //Item view
        list = new JList<Item>();
        list.setModel(new DefaultListModel<>());

        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setCellRenderer(new ItemViewCellRenderer());

        list.setFixedCellHeight(50);
        list.setFixedCellWidth(50);
        list.setVisibleRowCount(1);

        list.addListSelectionListener(listSelectionEvent -> {
            try {
                if (listSelectionEvent.getFirstIndex() < 0)
                    return;
                int index = listSelectionEvent.getFirstIndex();
                Item item = list.getModel().getElementAt(index);
                if (workCheck()) {
                    System.out.println("Item used: " + item.toString());
                    //TODO fix it
                    boolean succ = item.useItem();
                    currentPlayer.getCurrentTile().updateEvent();
                    list.setSelectedIndex(-1);

                    //Very bad hack to ensure no concurrent modifs
                    new Thread(() -> {
                        try {
                            Thread.sleep(10);
                            DataView.this.reloadInfo();
                        } catch (InterruptedException ignored) {
                        }
                    }).start();

                    workDone(succ);
                }
            } catch (Exception ignored) {
                //IDK why but it is pretty dangerous
            }

        });

        inventoryScrollPane = new JScrollPane(list);
        inventoryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


        JPanel invPanel = new JPanel();
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.X_AXIS));
        invPanel.setMaximumSize(new Dimension(180,85));
        invPanel.add(inventoryScrollPane);
        this.add(invPanel);

        setupActions();
    }

    private boolean workCheck() {

        return currentPlayer != null && playerRemActions > 0;
    }

    private void workDone(boolean success) {
        if(success) {
            playerRemActions--;
        }

        reloadInfo();

        if(playerRemActions == 0) {
            this.onPlayerTurnEnd.run();
        }
        updateUI();
    }

    private void setupActions() {
        //Move north
        upButton.addActionListener(e -> {
            if(workCheck()) {
                boolean res = currentPlayer.move(0);
                workDone(res);
            }
        });

        downButton.addActionListener(e -> {
            if(workCheck()) {
                boolean res = currentPlayer.move(2);
                workDone(res);
            }
        });

        leftButton.addActionListener(e -> {
            if(workCheck()) {
                boolean res = currentPlayer.move(3);
                workDone(res);
            }
        });

        rightButton.addActionListener(e -> {
            if(workCheck()) {
                boolean res = currentPlayer.move(1);
                workDone(res);
            }
        });

        unfreezeButton.addActionListener(e -> {
            if(workCheck()) {
                Item frozenItem = currentPlayer.getCurrentTile().getFrozenItem();
                if(frozenItem != null) {
                    boolean res = frozenItem.unfreeze();
                    workDone(res);

                    //Stupid hack to update tile
                    currentPlayer.getCurrentTile().updateEvent();
                }
            }
        });

        pickupButton.addActionListener(e -> {
            if(workCheck()) {
                Item frozenItem = currentPlayer.getCurrentTile().getFrozenItem();
                if(frozenItem != null) {
                    boolean res = frozenItem.pickedUp(currentPlayer);
                    if(res)
                        currentPlayer.getCurrentTile().setFrozenItem(null);
                    workDone(res);

                    currentPlayer.getCurrentTile().updateEvent();
                }
            }
        });

        skipButton.addActionListener(e -> {
            this.onPlayerTurnEnd.run();
        });

        digButton.addActionListener(e -> {
            if(workCheck()) {
                boolean succ = currentPlayer.getCurrentTile().removeSnowLayer(1);
                workDone(succ);
            }
        });

        specButton.addActionListener(e -> {
            if(workCheck()) {
                boolean succ = currentPlayer.specialAbility();
                workDone(succ);
            }
        });


    }

    private void reloadInfo() {
        try {
            list.setSelectedIndex(-1);
            //Inventory
            DefaultListModel<Item> model = new DefaultListModel<>();
            for (Item item : currentPlayer.getInventory()) {
                model.addElement(item);
            }

            list.setModel(model);
        }catch (Exception ignored) {
            //IDK WHY
        }

        this.healthLabel.setText(String.valueOf(currentPlayer.getTemp()));
        updateWorkLabel();
    }

    private void updateWorkLabel() {
        this.workLabel.setText(String.valueOf(playerRemActions));
    }


    private void nextPlayer(Player p) {
        playerRemActions = 4;
        this.currentPlayer = p;

        reloadInfo();
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
