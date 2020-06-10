package com.fermitech.DatabasesAndDragonsExplorer.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class AutoDesktop extends JDesktopPane {
    private BufferedImage img;
    public AutoDesktop(){
        super();
        try {
            img = ImageIO.read(new URL("https://i.imgur.com/ddIVImf.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void spawnQuery(ArrayList<ArrayList> tabella){
        JInternalFrame jn = new JInternalFrame("Risultato della query", true, true, true, true);
        jn.setSize(50*tabella.get(0).size(), 300);
        jn.setLocation(550,10);
        jn.setVisible(true);
        try{
        AutoPanel query = new QueryWindow(jn, tabella);
        add(jn);}
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Il risultato della query è vuoto.", "Risultato query", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    protected void spawnMenu(){
        JInternalFrame jn = new JInternalFrame("Menu Operazioni", false, false, false, true);
        jn.setSize(500,180);
        jn.setLocation(10,10);
        jn.setVisible(true);
        AutoPanel menu = new Menu(jn, this);
        add(jn);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(img, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(img.getWidth(), img.getHeight());
    }
}
