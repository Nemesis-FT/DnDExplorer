package com.fermitech.DatabasesAndDragonsExplorer.gui;

import javax.swing.*;
/**
 * This is the QueryButton class, which extends the JButton.
 */
public class QueryButton extends JButton {
    private String query;
    private String[] domande;
    /**
     * This is the QueryButton constructor.
     * It initializes the button using the JButton constructor, and then saves the query and the questions to the user.
     *
     * @param testo text of the button.
     * @param query query connected to the button.
     * @param domande array containing the questions to the user.
     */
    public QueryButton(String testo, String query, String[] domande){
        super(testo);
        this.query = query;
        this.domande = domande;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String[] getDomande() {
        return domande;
    }
}
