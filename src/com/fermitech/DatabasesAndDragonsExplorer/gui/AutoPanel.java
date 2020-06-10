package com.fermitech.DatabasesAndDragonsExplorer.gui;

import javax.swing.*;

/**
 * This is the AutoPanel class, an extension of JPanel.
 */
public class AutoPanel extends JPanel {

    String panel_name;

    /**
     * This is the AutoPanel class constructor.
     *
     * @param panel_name the name of the panel.
     */
    public AutoPanel(String panel_name) {
        super();
        this.panel_name = panel_name;
    }

    /**
     * This is the AutoPanel setPanel_name method.
     * It sets the panel_name to a certain value.
     *
     * @param panel_name the new name of the panel.
     */
    public void setPanel_name(String panel_name) {
        this.panel_name = panel_name;
    }

    /**
     * This is the AutoPanel getPanel_name method.
     * It returns the panel_name.
     *
     * @return panel_name the new name of the panel.
     */
    public String getPanel_name() {
        return panel_name;
    }

    /**
     * This is the AutoPanel summonErrorPopup method.
     * It sets the summons an error dialog.
     *
     * @param message the message that gets displayed.
     */
    public void summonErrorPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Errore", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * This is the AutoPanel summonInputPopup method.
     * It sets the summons an input dialog.
     *
     * @param message the message that gets displayed.
     * @return response the user's response to the question.
     */
    public String summonInputPopup(String message) {
        return JOptionPane.showInputDialog(null, message);
    }
    /**
     * This is the AutoPanel summonInfoPopup method.
     * It sets the summons an information dialog.
     *
     * @param infoMessage the message that gets displayed.
     * @param titleBar the title of the window.
     */
    public void summonInfoPopup(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}