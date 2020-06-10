package com.fermitech.DatabasesAndDragonsExplorer.gui;

import com.fermitech.DatabasesAndDragonsExplorer.CompactResult;
import com.fermitech.DatabasesAndDragonsExplorer.Server;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * This is the Menu class, which implements the main menu ui elements and functionalities.
 */
public class Menu extends AutoPanel implements ActionListener, ChangeListener {

    private QueryButton pb1, pb2, pb3, pb4, pb5, pb6;
    private QueryButton ub1, ub2;
    private QueryButton cb1, cb2, cb3, cb4, cb5;
    private QueryButton mb1, mb2, mb3;
    private QueryButton qb1;
    private JTextField address, dbName, username, password, freeQuery;
    private JLabel instructions;
    private JButton connect;
    private AutoPanel pannelloS, pannelloP, pannelloU, pannelloC, pannelloM, pannelloQ, pannelloA;
    private JTabbedPane tab;
    private Server server;
    private AutoDesktop desktop;
    private JInternalFrame frame;
    private JTextArea about;
    private String test;
    /**
     * This is the MainPanel constructor.
     * It initializes the ui.
     *
     * @param frame the frame that hosts the panel.
     * @param desktop the desktop in which the window exists.
     */
    public Menu(JInternalFrame frame, AutoDesktop desktop) {
        super("Menu Principale");
        this.frame = frame;
        this.desktop = desktop;
        Container container1 = this.frame.getContentPane();
        pannelloS = new AutoPanel("Impostazioni");
        container1.add(pannelloS);
        impostazioniSetup();
        pannelloP = new AutoPanel("Personaggi");
        tab = new JTabbedPane();
        container1.add(pannelloP);
        personaggiSetup();
        pannelloU = new AutoPanel("Utenti");
        container1.add(pannelloU);
        utentiSetup();
        pannelloC = new AutoPanel("Campagne");
        container1.add(pannelloC);
        campagnaSetup();
        pannelloM = new AutoPanel("Messaggi");
        container1.add(pannelloM);
        messaggioSetup();
        pannelloQ = new AutoPanel("Query");
        container1.add(pannelloQ);
        querySetup();
        pannelloA = new AutoPanel("?");
        container1.add(pannelloA);
        aboutSetup();
        tab.addTab("Connessione", pannelloS);
        tab.addTab("Personaggi", pannelloP);
        tab.addTab("Utenti", pannelloU);
        tab.addTab("Campagna", pannelloC);
        tab.addTab("Messaggi", pannelloM);
        tab.addTab("Query", pannelloQ);
        tab.addTab("?", pannelloA);
        tab.setEnabledAt(1, false);
        tab.setEnabledAt(2, false);
        tab.setEnabledAt(3, false);
        tab.setEnabledAt(4, false);
        tab.setEnabledAt(5, false);
        tab.setVisible(true);
        container1.add(tab);
    }

    private void aboutSetup() {
        about = new JTextArea("Databases&Dragons Explorer è parte della relazione del Gruppo 3,"+"composto da Lorenzo Balugani (Matr 128991) e Matteo Balugani (Matr 135475)."+"\nIstruzioni per l'uso: dopo aver eseguito l'accesso al database, si possono eseguire"+" le query presenti nella relazione, oppure accedere alla tab 'Query' per inserirne di nuove."+" Se la query ha esito positivo, e il set dei risultati non è nullo, viene creata una finestra contenente una tabella."+" Per ottenere il valore di un campo, fare doppio click su di esso.");
        about.setEditable(false);
        about.setColumns(27);
        about.setRows(10);
        about.setLineWrap(true);
        about.setWrapStyleWord(true);
        about.setBounds(15,10, 485, 170);
        pannelloA.add(about);
    }

    private void querySetup() {
        freeQuery = new JTextField(30);
        freeQuery.setVisible(true);
        pannelloQ.add(freeQuery);
        qb1 = new QueryButton("Esegui", "", new String[]{"Nothing to see here"});
        pannelloQ.add(qb1);
        qb1.addActionListener(this);
        qb1.setVisible(true);
    }

    private void personaggiSetup() {
        pb1 = new QueryButton("Tutti i personaggi di un giocatore", "SELECT p.* FROM personaggio AS p, utente AS u WHERE u.utenteemail = '?' AND u.utenteemail = p.utenteemail;", new String[]{"Mail dell'utente?"});
        pannelloP.add(pb1);
        pb1.addActionListener(this);
        pb1.setVisible(true);
        pb2 = new QueryButton("Inventario di un personaggio", "SELECT O.*, Pos.quantita FROM Oggetto AS O, Possiede AS Pos WHERE Pos.personaggioid=? AND Pos.oggettoid = O.oggettoid;", new String[]{"Id del personaggio?"});
        pannelloP.add(pb2);
        pb2.addActionListener(this);
        pb2.setVisible(true);
        pb3 = new QueryButton("Incantesimi di un personaggio", "SELECT I.*, La.preparato FROM Incantesimo AS I, Lancia AS La WHERE La.personaggioid=? AND La.incantesimoid = I.incantesimoid;", new String[]{"Id del personaggio?"});
        pannelloP.add(pb3);
        pb3.addActionListener(this);
        pb3.setVisible(true);
        pb4 = new QueryButton("Classi di un personaggio", "SELECT C.*, Ist.livello FROM Classe AS C, IstruitoA AS Ist WHERE Ist.personaggioid=? AND Ist.classeid = C.classeid;", new String[]{"Id del personaggio?"});
        pannelloP.add(pb4);
        pb4.addActionListener(this);
        pb4.setVisible(true);
        pb5 = new QueryButton("Abilità di un personaggio", "SELECT A.*, Sa.grado FROM Abilita AS A, SaFare AS Sa WHERE Sa.personaggioid=? AND Sa.abilitaid = A.abilitaid;", new String[]{"Id del personaggio?"});
        pannelloP.add(pb5);
        pb5.addActionListener(this);
        pb5.setVisible(true);
        pb6 = new QueryButton("Campagne di un personaggio", "SELECT C.* FROM Campagna AS C, Agisce AS A WHERE A.personaggioid=? AND A.campagnaid = C.campagnaid;", new String[]{"Id del personaggio?"});
        pannelloP.add(pb6);
        pb6.addActionListener(this);
        pb6.setVisible(true);
        pannelloP.setVisible(true);
    }

    private void utentiSetup() {
        ub1 = new QueryButton("Gruppi di un utente in una campagna", "SELECT * FROM Gruppo WHERE Gruppo.gruppoid IN (SELECT ScriveNel.gruppoid FROM ScriveNel WHERE ScriveNel.utenteemail='?') AND Gruppo.campagnaid=?;", new String[]{"Email dell'utente?", "Id della campagna?"});
        pannelloU.add(ub1);
        ub1.addActionListener(this);
        ub1.setVisible(true);
        ub2 = new QueryButton("Messaggi dei gruppi in cui si trova un utente di una certa campagna", "SELECT * FROM Messaggio WHERE Messaggio.gruppoid IN (SELECT ScriveNel.gruppoid FROM ScriveNel, Gruppo WHERE utenteemail='?' AND ScriveNel.gruppoid = Gruppo.gruppoid AND Gruppo.campagnaid=? );", new String[]{"Email dell'utente?", "Id della campagna?"});
        pannelloU.add(ub2);
        ub2.addActionListener(this);
        ub2.setVisible(true);
    }

    private void campagnaSetup() {
        cb1 = new QueryButton("Tutti i partecipanti di una campagna", "SELECT Utente.* FROM Campagna JOIN Partecipa ON Campagna.campagnaid = Partecipa.campagnaid JOIN Utente ON Partecipa.utenteemail = Utente.utenteemail WHERE Campagna.campagnaid=?;", new String[]{"Id della campagna?"});
        pannelloC.add(cb1);
        cb1.addActionListener(this);
        cb1.setVisible(true);
        cb2 = new QueryButton("Tutti i DM di una campagna", "SELECT Utente.* FROM Campagna JOIN Partecipa ON Campagna.campagnaid = Partecipa.campagnaid JOIN Utente ON Partecipa.utenteemail = Utente.utenteemail WHERE Campagna.campagnaid=? AND Partecipa.comeDm = True;", new String[]{"Id della campagna?"});
        pannelloC.add(cb2);
        cb2.addActionListener(this);
        cb2.setVisible(true);
        cb3 = new QueryButton("Tutti i personaggi di una campagna", "SELECT Personaggio.* FROM Campagna JOIN Agisce ON Campagna.campagnaid = Agisce.campagnaid JOIN Personaggio ON Agisce.personaggioid = Personaggio.personaggioid WHERE Campagna.campagnaid=?;", new String[]{"Id della campagna?"});
        pannelloC.add(cb3);
        cb3.addActionListener(this);
        cb3.setVisible(true);
        cb4 = new QueryButton("Tutti i gruppi di una campagna", "SELECT Gruppo.* FROM Campagna JOIN Gruppo ON Gruppo.campagnaid = Campagna.campagnaid WHERE Campagna.campagnaid=?;", new String[]{"Id della campagna?"});
        pannelloC.add(cb4);
        cb4.addActionListener(this);
        cb4.setVisible(true);
        cb5 = new QueryButton("Tutti i gruppi attivi di una campagna", "SELECT Gruppo.* FROM Campagna JOIN Gruppo ON Gruppo.campagnaid = Campagna.campagnaid WHERE Campagna.campagnaid=? AND Gruppo.attivo=True;", new String[]{"Id della campagna?"});
        pannelloC.add(cb5);
        cb5.addActionListener(this);
        cb5.setVisible(true);
    }

    private void messaggioSetup() {
        mb1 = new QueryButton("Membri di un gruppo", "SELECT Utente.* FROM Gruppo JOIN ScriveNel ON Gruppo.gruppoid = ScriveNel.gruppoid JOIN Utente ON ScriveNel.utenteEmail = Utente.utenteEmail WHERE Gruppo.gruppoid=?;", new String[]{"Id del gruppo?"});
        pannelloM.add(mb1);
        mb1.addActionListener(this);
        mb1.setVisible(true);
        mb2 = new QueryButton("Messaggi di un gruppo", "SELECT Messaggio.* FROM Gruppo JOIN Messaggio ON Gruppo.gruppoid = Messaggio.gruppoid WHERE Gruppo.gruppoid=?;", new String[]{"Id del gruppo?"});
        pannelloM.add(mb2);
        mb2.addActionListener(this);
        mb2.setVisible(true);
        mb3 = new QueryButton("Risposta ad un messaggio", "SELECT Target.* FROM Messaggio, Messaggio as Target WHERE Messaggio.gruppoid=? AND Messaggio.messaggioid=? AND Target.messaggioid=Messaggio.rispostamessid AND Target.gruppoid=Messaggio.rispostagroupid;", new String[]{"Id del gruppo?", "Id del messaggio?"});
        pannelloM.add(mb3);
        mb3.addActionListener(this);
        mb3.setVisible(true);
    }

    private void impostazioniSetup() {
        instructions = new JLabel("Inserire di seguito l'indirizzo (localhost:1234), il nome del db e le credenziali.");
        pannelloS.add(instructions);
        instructions.setVisible(true);
        address = new JTextField(20);
        pannelloS.add(address);
        address.setVisible(true);
        dbName = new JTextField(5);
        pannelloS.add(dbName);
        dbName.setVisible(true);
        username = new JTextField(5);
        pannelloS.add(username);
        username.setVisible(true);
        password = new JTextField(5);
        pannelloS.add(password);
        password.setVisible(true);
        connect = new JButton("Connessione");
        connect.setVisible(true);
        pannelloS.add(connect);
        connect.addActionListener(this);
    }

    /**
     * This is the Menu actionPerformed method.
     * It reacts to changes to the ui, and runs specific features.
     *
     * @param e the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connect) {
            try {
                this.server = new Server(address.getText(), dbName.getText(), username.getText(), password.getText(), this.pannelloS);
                tab.setEnabledAt(1, true);
                tab.setEnabledAt(2, true);
                tab.setEnabledAt(3, true);
                tab.setEnabledAt(4, true);
                tab.setEnabledAt(5, true);
                tab.remove(0);
            } catch (SQLException ex) {
                pannelloS.summonErrorPopup("Si è verificato un errore di connessione.");
            } catch (ClassNotFoundException ex) {
                pannelloS.summonErrorPopup("Si è verificato un errore con il driver.");
            }
        }
        if (e.getSource() instanceof QueryButton) {
            if (e.getSource() == qb1) {
                qb1.setQuery(freeQuery.getText());
            }
            CompactResult ans = this.server.executeQuery(((QueryButton) e.getSource()).getQuery(), ((QueryButton) e.getSource()).getDomande());
            ArrayList<ArrayList> tabella = new ArrayList<ArrayList>();
            ArrayList<String> colonne = new ArrayList<String>();
            if (ans != null) {
                int j = 0;
                while (true) {
                    try {
                        j++;
                        colonne.add(ans.metaData.getColumnName(j));
                    } catch (Exception ex) {
                        tabella.add(colonne);
                        break;
                    }
                }
                while (true) {
                    try {
                        if (!ans.dati.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    ArrayList<String> riga = new ArrayList<String>();
                    int i = 0;
                    while (true) {
                        try {
                            i++;
                            riga.add(ans.dati.getString(i));
                        } catch (Exception ex) {
                            tabella.add(riga);
                            break;
                        }
                    }
                }
                this.desktop.spawnQuery(tabella);
            } else {
                this.summonInfoPopup("Il risultato della query è vuoto.", "Risultato query");
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {

    }
}
