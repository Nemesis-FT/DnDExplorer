package com.fermitech.DatabasesAndDragonsExplorer.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
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
