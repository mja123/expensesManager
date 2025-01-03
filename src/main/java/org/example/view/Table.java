package org.example.view;

import org.example.controller.Controller;
import org.example.model.dto.ExpenseDTO;
import org.example.model.dto.IDTO;
import org.example.utils.enums.ETable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class Table extends JPanel {
    private DefaultTableModel defaultTableModel;
    private final Controller controller = new Controller();
    private JTable expensesTable;
    private ETable table;

    public Table(String[] columns) {
        Object[][] rows = {};
        defaultTableModel = new DefaultTableModel(rows, columns);
        setLayout(new BorderLayout());
        expensesTable = new JTable(defaultTableModel);
        add(new JScrollPane(expensesTable));
    }


    public void deleteRow(int rowNumber) {
        defaultTableModel.removeRow(rowNumber);
    }

    public JTable getExpensesTable() {
        return expensesTable;
    }

}
