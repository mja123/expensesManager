package org.example.view.expenses;

import org.example.controller.Controller;
import org.example.model.dto.ExpenseDTO;
import org.example.utils.enums.ETable;
import org.example.view.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ExpensesList extends JPanel {
    private static DefaultTableModel defaultTableModel;
    private static final Controller controller = new Controller();
    private static JTable expensesTable;
    public ExpensesList() {
        String[] columns = {"ID", "NAME", "AMOUNT", "DATE", "CATEGORY"};
        Object[][] rows = {};
        defaultTableModel = new DefaultTableModel(rows, columns);
        fillExpensesTable();
        setLayout(new BorderLayout());
        expensesTable = new JTable(defaultTableModel);
        add(new JScrollPane(expensesTable));
    }

    private static void fillExpensesTable() {
        controller.getAll(ETable.EXPENSE).forEach(e -> addRow((ExpenseDTO) e));
    }

    public static void addRow(ExpenseDTO expense) {
        defaultTableModel.addRow(new Object[] {expense.getId(), expense.getName(), expense.getAmount(), expense.getDate(), expense.getCategory()});
    }

    public static void deleteRow(int rowNumber) {
        defaultTableModel.removeRow(rowNumber);
    }

    public static JTable getExpensesTable() {
        return expensesTable;
    }
}

