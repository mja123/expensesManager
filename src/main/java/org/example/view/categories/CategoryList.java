package org.example.view.categories;

import org.example.controller.Controller;
import org.example.model.dto.CategoryDTO;
import org.example.utils.enums.ETable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CategoryList extends JPanel {
    private static DefaultTableModel defaultTableModel;
    private static final Controller controller = new Controller();
    private static JTable expensesTable;
    public CategoryList() {
        String[] columns = {"ID", "NAME"};
        Object[][] rows = {};
        defaultTableModel = new DefaultTableModel(rows, columns);
        fillExpensesTable();
        setLayout(new BorderLayout());
        expensesTable = new JTable(defaultTableModel);
        add(new JScrollPane(expensesTable));
    }

    private static void fillExpensesTable() {
        controller.getAll(ETable.CATEGORY).forEach(e -> addRow((CategoryDTO) e));
    }

    public static void addRow(CategoryDTO expense) {
        defaultTableModel.addRow(new Object[] {expense.getId(), expense.getName()});
    }

    public static void deleteRow(int rowNumber) {
        defaultTableModel.removeRow(rowNumber);
    }

    public static JTable getCategoriesTable() {
        return expensesTable;
    }
}

