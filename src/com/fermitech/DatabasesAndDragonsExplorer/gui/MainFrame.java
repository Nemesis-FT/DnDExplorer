package com.fermitech.DatabasesAndDragonsExplorer.gui;

import javax.swing.*;
import java.awt.*;
/**
 * This is the MainPanel class, which creates the main window.
 */
public class MainFrame extends JFrame {
    /**
     * This is the MainFrame constructor.
     * It sets up the window, and creates the desktop enviroment.
     */
    public MainFrame(){
        AutoDesktop desktop = new AutoDesktop();
        Container contentPane = this.getContentPane();
        contentPane.add(desktop);
        desktop.spawnMenu();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 755);
        setResizable(false);
        setTitle("Databases&Dragons Explorer");
    }
}
