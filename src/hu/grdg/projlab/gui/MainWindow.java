package hu.grdg.projlab.gui;

import hu.grdg.projlab.model.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class MainWindow extends JFrame {
    private JMenuBar mainMenuBar;
    private JMenuItem menuItemNewGame;
    private JMenu menuGame;
    private JMenu menuHelp;
    private JMenuItem menuItemExit;
    private JMenuItem menuItemHelp;
    private JMenuItem menuItemAbout;
    private LevelView levelView;
    private Controller controller;
    private DataView dataView;

    public MainWindow(Controller c)  {
        super("Jégmező - GRDG | v1.0");
        setLAF();
        this.controller = c;
    }

    //NOT IN DOCS
    //Sets the look and feel
    private void setLAF() {
        try {
            for (UIManager.LookAndFeelInfo installedLookAndFeel : UIManager.getInstalledLookAndFeels()) {
                if(installedLookAndFeel.getName().toLowerCase().contains("nimbus")) {
                    UIManager.setLookAndFeel(installedLookAndFeel.getClassName());
                    return;
                }
            }

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void create() {
        createMenus();
        createLevelView();
        createDataView();

        setSize(815,600);
        setResizable(false);

        //Center
        setLocationRelativeTo(null);

        //Absolute layout
        setLayout(null);

        //Ask user on close
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                MainWindow.this.showExitConfirm(null);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        setVisible(true);
    }

    private void createDataView() {
        dataView = new DataView(controller);
        dataView.setBounds(600,0,200,540);

        this.add(dataView);
    }

    private void createLevelView() {

        levelView = new LevelView(controller);
        levelView.setBounds(0,0,600,540);

        this.add(levelView);
    }

    private void createMenus() {
        mainMenuBar = new JMenuBar();

        menuGame = new JMenu("Játék");
        menuHelp = new JMenu("Help");

        menuItemNewGame = new JMenuItem("Új játék");
        menuItemExit = new JMenuItem("Kilépés");

        menuItemHelp = new JMenuItem("Help");
        menuItemAbout = new JMenuItem("Rólunk");

        //Setup trivial menu actions
        menuItemAbout.addActionListener(this::showAbout);
        menuItemHelp.addActionListener(this::showHelp);

        menuItemNewGame.addActionListener(this::startGameAsync);
        menuItemExit.addActionListener(this::showExitConfirm);

        menuGame.add(menuItemNewGame);
        menuGame.add(menuItemExit);

        menuHelp.add(menuItemHelp);
        menuHelp.add(menuItemAbout);

        mainMenuBar.add(menuGame);
        mainMenuBar.add(menuHelp);
        setJMenuBar(mainMenuBar);
    }

    /**
     * @author Dani
     * @param actionEvent ignored
     */
    private void startGameAsync(ActionEvent actionEvent) {
        Thread gameThread = new Thread(controller::startGame);
        gameThread.setName("Game main loop");
        gameThread.start();
    }

    /**
     * Asks if the user really want to close the application
     * @param actionEvent ignored
     */
    private void showExitConfirm(ActionEvent actionEvent) {
        int res = JOptionPane.showConfirmDialog(this,"Biztosan kilép?","Kilépés",JOptionPane.YES_NO_OPTION);
        if(res == JOptionPane.YES_OPTION) {
            this.dispose();
            System.exit(0);
        }
    }

    /**
     * Shows help
     * @param actionEvent ignored
     */
    private void showHelp(ActionEvent actionEvent) {
        if(Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(new File("help.pdf"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Nem lehet megnyitni a help.pdf-t", "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Nem lehet megnyitni a help.pdf-t", "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows the about dialog
     * @param actionEvent ignored
     */
    private void showAbout(ActionEvent actionEvent) {
        String message = "Mit jelent a GRDG?\n\n" +
                "Mit rövidít?\n" +
                "Mit mond?\n" +
                "Mit mutat?\n" +
                "Ha valaki tudja, árulja el nekünk is\n\n" +
                "Köszönjük\n" +
                "    GRDG - 2020";
        JOptionPane.showMessageDialog(this, message, "GDRG - About",JOptionPane.INFORMATION_MESSAGE);
    }
}
