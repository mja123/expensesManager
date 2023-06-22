package org.example.view;

import jdk.jfr.Category;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class ExpensesView extends View {
    public ExpensesView() {
        super("Expenses", WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new NavBar(), BorderLayout.NORTH);
        this.add(new ExpensesList(), BorderLayout.CENTER);
        this.add(new NewExpenseForm(), BorderLayout.SOUTH);
    }

    private static class NavBar extends JPanel {
        NavBar() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.add(new JButton("Create"));
            this.add(new JButton("Delete"));
        }
    }

    private static class ExpensesList extends JPanel {
        ExpensesList() {
            Object[][] rowData = {
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
                    {1, "stefa", 200.2, "date", 'A'},
            };
            String[] columns = {"ID", "NAME", "AMOUNT", "DATE", "CATEGORY"};
            this.setLayout(new BorderLayout());
            this.add(new JScrollPane(new JTable(rowData, columns)), BorderLayout.CENTER);
        }
    }

    private static class NewExpenseForm extends JPanel {
        NewExpenseForm() {
            this.add(this.idField());
            this.add(new JTextField(20));
            this.add(new JTextField(20));
            this.add(this.dateField());
            this.add(this.categoryField());
        }

        private JTextField idField() {
            JTextField idField = new JTextField(20);
            idField.setEditable(false);
            idField.setText("" + 1);

            return idField;
        }

        private JTextField dateField() {
            JTextField dateField = new JTextField(20);
            dateField.setEditable(false);
            dateField.setText(new Date(System.currentTimeMillis()).toString());

            return dateField;
        }

        private JComboBox<String> categoryField() {
            String[] categories = { "a", "b", "c", "d" };

            return new JComboBox<>(categories);
        }
    }
}
