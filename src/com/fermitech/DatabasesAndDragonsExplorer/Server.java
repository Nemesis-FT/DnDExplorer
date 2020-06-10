package com.fermitech.DatabasesAndDragonsExplorer;

import com.fermitech.DatabasesAndDragonsExplorer.gui.AutoPanel;

import java.sql.*;
import java.util.ArrayList;
/**
 * This is the Server class, that manages the query execution and authentication.
 */
public class Server {
    private Connection connection;
    private AutoPanel panel;
    /**
     * This is the Server constructor.
     * It tries to connect to the DBMS.
     *
     * @param address the DBMS address.
     * @param databaseName the name of the database.
     * @param username the user's username.
     * @param password the user's password.
     * @param panel the panel that creates the server instance.
     */
    public Server(String address, String databaseName, String username, String password, AutoPanel panel) throws SQLException, ClassNotFoundException {
        this.panel = panel;
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection("jdbc:postgresql://" + address + "/" + databaseName, username, password);
    }
    /**
     * This is the executeQuery method.
     * It executes a query, and asks the user additional data.
     * PreparedStatement wasn't used since it requires the procedure to be very specific, and this is a generic procedure.
     *
     * @param query the query.
     * @param domande questions that the user has to answer.
     */
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
