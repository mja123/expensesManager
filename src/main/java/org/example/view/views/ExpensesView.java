package org.example.view.views;

import org.example.controller.Controller;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.ExpenseDTO;
import org.example.utils.enums.ETable;
import org.example.utils.excpetions.ExpensesManagerException;
import org.example.utils.excpetions.FieldException;
import org.example.view.listPanel.ExpensesList;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpensesView extends View {
    private static final Controller controller = new Controller();
    private static final JTextField nameField = nameField();
    private static final JTextField amountField = amountField();
    private static final JComboBox<String> categoryField = categoryField();
    private static Dimension screenDimension;
    private static final ExpensesList expensesList = new ExpensesList();

    public ExpensesView() {
        super("Expenses", JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new NavBar(this), BorderLayout.NORTH);
        this.add(expensesList, BorderLayout.CENTER);
        this.add(new NewExpenseForm(), BorderLayout.SOUTH);
        this.pack();
        screenDimension = new Dimension(this.getWidth(), this.getHeight());
    }

    private static class NavBar extends JPanel {
        NavBar(ExpensesView panel) {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.add(new CreateButton(panel));
            this.add(new DeleteButton(panel));
            this.add(new CategoryButton(panel));
        }
    }

    private static class CreateButton extends JButton {
        public CreateButton(ExpensesView expensesView) {
            super("Create");
            this.addActionListener(event -> createNewExpense(expensesView));
        }

        private void createNewExpense(ExpensesView expensesView) {
            try {
                if (nameField.getText().isEmpty() || amountField.getText().isEmpty()) throw new FieldException();
                ExpenseDTO expenseDTO = new ExpenseDTO(
                        nameField.getText(),
                        Double.parseDouble(amountField.getText()),
                        new Date(Calendar.getInstance().getTimeInMillis()),
                        (String) categoryField.getSelectedItem()
                );
                controller.create(expenseDTO);
                expensesList.addRow(expenseDTO);
            } catch (ExpensesManagerException e) {
                e.showErrorDialog(expensesView, e.getMessage());
            }

        }
    }

    private static class DeleteButton extends JButton {
        public DeleteButton(ExpensesView expensesView) {
            super("Delete");
            this.addActionListener(event -> deleteExpense(expensesView));
        }

        private void deleteExpense(ExpensesView expensesView) {
            JTable expensesTable = expensesList.getTable();
            int selectRow = expensesTable.getSelectedRow();
            int expenseId = (int) expensesTable.getValueAt(selectRow, 0);
//            controller.delete(expenseId, ETable.EXPENSE);
            expensesList.deleteRow(selectRow);
        }
    }

    private static class CategoryButton extends JButton {
        public CategoryButton(ExpensesView panel) {
            super("Categories");
            this.addActionListener(event -> goToCategoryView(panel));
        }
        private void goToCategoryView(ExpensesView panel) {
            new CategoryView(screenDimension, panel);
        }
    }
    private static class NewExpenseForm extends JPanel {
        NewExpenseForm() {
            add(new JLabel("Name"));
            add(nameField);
            add(new JLabel("Amount"));
            add(amountField);
            add(categoryField);
        }
    }

    public void addCategory(String categoryName) {
        categoryField.addItem(categoryName);
        this.pack();
    }

    public void deleteCategory(String categoryName) {
        categoryField.removeItem(categoryName);
        this.pack();
    }
    private static JTextField nameField() {
        return new JTextField(40);
    }

    private static JTextField amountField() {
        return new JTextField(25);
    }

    private static JComboBox<String> categoryField() {
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        JComboBox<String> categoryField = new JComboBox<>();

//        controller.getAll(ETable.CATEGORY).forEach(c -> categoriesDTO.add((CategoryDTO) c));
        categoriesDTO.forEach(c -> categoryField.addItem(c.getName()));

        return categoryField;
    }
}
