package com.fermitech.DatabasesAndDragonsExplorer;

import com.fermitech.DatabasesAndDragonsExplorer.gui.AutoPanel;

import java.sql.*;
import java.util.ArrayList;

public class Server {
    private Connection connection;
    private AutoPanel panel;

    public Server(String address, String databaseName, String username, String password, AutoPanel panel) throws SQLException, ClassNotFoundException {
        this.panel = panel;
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection("jdbc:postgresql://" + address + "/" + databaseName, username, password);
    }

    public CompactResult executeQuery(String query, String[] domande) {
        try {
            int richieste = (int) query.codePoints().filter(ch -> ch == '?').count();
            String[] risposte = new String[2];
            for (int i = 0; i < richieste; i++) {
                risposte[i] = this.panel.summonInputPopup(domande[i]);
                if (risposte[i] == null) {
                    return null;
                }
                query = query.replaceFirst("\\?", risposte[i]);
            }
            Statement statement = connection.createStatement();
            ResultSet r = statement.executeQuery(query);
            return new CompactResult(r, r.getMetaData());
        } catch (SQLException e) {
            panel.summonErrorPopup("Qualcosa è andato storto nella query.");
        }
        return null;
    }

}
