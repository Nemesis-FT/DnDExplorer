package com.fermitech.DatabasesAndDragonsExplorer.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * This is the QueryWindow class, which implements the main ui elements for the window containing the query's result.
 */
public class QueryWindow extends AutoPanel{
    private JInternalFrame frame;
    private AutoPanel table;
    private JTable tabella;
    /**
     * This is the MainPanel constructor.
     * It initializes the ui and builds the table.
     *
     * @param frame the frame that hosts the panel.
     * @param tab the result of the query
     */
    public QueryWindow(JInternalFrame frame, ArrayList<ArrayList> tab) {
        super("Risultato della query");
        this.frame = frame;
        tableSetup(tab);
        tabella.setVisible(true);
        this.setVisible(true);
        try{
            frame.setSelected(true);
        } catch (PropertyVetoException e) {
        }
    }
    /**
     * This is the tableSetup method.
     * It builds the table.
     *
     * @param tab the result of the query
     */
    private void tableSetup(ArrayList<ArrayList> tab){
        DefaultTableModel model = new DefaultTableModel();
        for(Object s: tab.get(0)){
            model.addColumn(s);
        }
        tab.remove(0);
        for(ArrayList r: tab){
            model.addRow(r.toArray());
        }
        this.tabella = new JTable(model){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabella.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                int column = table.columnAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    JOptionPane.showMessageDialog(null, tabella.getModel().getColumnName(column)+":\n"+tabella.getModel().getValueAt(row, column), "Contenuto della cella", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(tabella, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(50*tab.get(0).size(), 300));
        this.setBounds(1,1,50*tab.get(0).size(), 300);
        this.frame.add(scrollPane);
    }
}
