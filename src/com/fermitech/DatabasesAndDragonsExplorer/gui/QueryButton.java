package com.fermitech.DatabasesAndDragonsExplorer.gui;

import javax.swing.*;

public class QueryButton extends JButton {
    private String query;
    private String[] domande;
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
