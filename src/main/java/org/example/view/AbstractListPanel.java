package org.example.view;

import org.example.controller.Controller;
import org.example.utils.enums.ETable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;

public abstract class AbstractListPanel<T> extends JPanel {
    protected DefaultTableModel tableModel;
    protected final Controller controller;
    protected JTable table;

    public AbstractListPanel(ETable tableType, String[] columns) {
        tableModel = new DefaultTableModel(new Object[][]{}, columns);
        controller = new Controller();
        table = new JTable(tableModel);
        fillTable(tableType, this::addRow);
        setLayout(new BorderLayout());
        add(new JScrollPane(table));
    }

    // Abstract method to be implemented by subclasses for row adding
    protected abstract void addRow(T dto);

    // Populate the table with data
    private void fillTable(ETable tableType, Consumer<T> rowAdder) {
        controller.getAll(tableType).forEach(e -> rowAdder.accept((T) e));
    }

    // Delete a row by index
    public void deleteRow(int rowNumber) {
        tableModel.removeRow(rowNumber);
    }

    // Get the JTable
    public JTable getTable() {
        return table;
    }
}
