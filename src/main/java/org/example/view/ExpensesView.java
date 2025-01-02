package org.example.view;

import org.example.model.controller.Controller;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.ExpenseDTO;
import org.example.utils.enums.ETable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpensesView extends JFrame {
    private static final Controller controller = new Controller();
    private static final JTextField nameField = nameField();
    private static final JTextField amountField = amountField();
    private static final JComboBox<String> categoryField = categoryField();

    public ExpensesView() {
        super("Expenses");
        this.setLayout(new BorderLayout());
        this.add(new NavBar(), BorderLayout.NORTH);
        this.add(new ExpensesList(), BorderLayout.CENTER);
        this.add(new NewExpenseForm(), BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class NavBar extends JPanel {
        NavBar() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.add(new CreateButton());
            this.add(new DeleteButton());
        }
    }

    private static class CreateButton extends JButton {
        CreateButton() {
            super("Create");
            this.addActionListener(event -> createNewExpense());
        }

        private void createNewExpense() {
            ExpenseDTO expenseDTO = new ExpenseDTO(
                    nameField.getText(),
                    Double.parseDouble(amountField.getText()),
                    new Date(Calendar.getInstance().getTimeInMillis()),
                    (String) categoryField.getSelectedItem()
            );
            controller.create(expenseDTO);
            ExpensesList.addRow(expenseDTO);
        }
    }

    private static class DeleteButton extends JButton {
        DeleteButton() {
            super("Delete");
            this.addActionListener(event -> deleteExpense());
        }

        private void deleteExpense() {
            ExpenseDTO expenseDTO = new ExpenseDTO(
                    nameField.getText(),
                    Double.parseDouble(amountField.getText()),
                    new Date(Calendar.getInstance().getTimeInMillis()),
                    (String) categoryField.getSelectedItem()
            );
            controller.create(expenseDTO);
        }
    }
    private static class ExpensesList extends JPanel {
        private static DefaultTableModel defaultTableModel;
        ExpensesList() {
            Object[] columns = { "ID", "NAME", "AMOUNT", "DATE", "CATEGORY" };
            Object[][] rows = {};
            defaultTableModel = new DefaultTableModel(rows, columns);
            fillExpensesTable();
            setLayout(new BorderLayout());
            add(new JScrollPane(new JTable(defaultTableModel)));
        }

        private static void fillExpensesTable() {
            controller.getAll(ETable.EXPENSE).forEach(e -> addRow((ExpenseDTO) e));
        }

        public static void addRow(ExpenseDTO expense) {
            defaultTableModel.addRow(new Object[] {expense.getId(), expense.getName(), expense.getAmount(), expense.getDate(), expense.getCategory()});
        }
    }
    private static class NewExpenseForm extends JPanel {
        NewExpenseForm() {
            add(nameField);
            add(amountField);
            add(categoryField);
        }
    }

    private static JTextField nameField() {
        return new JTextField(40);
    }

    private static JTextField amountField() {
        return new JTextField(25);
    }

    private static JComboBox<String> categoryField() {
        JComboBox<String> categoryField = new JComboBox<>();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();

        controller.getAll(ETable.CATEGORY).forEach(c -> categoriesDTO.add((CategoryDTO) c));
        categoriesDTO.forEach(c -> categoryField.addItem(c.getName()));

        return categoryField;
    }
}

/* TODO:
- Implement all methods from IService
-
 */
