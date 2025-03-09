package org.example.view.frame;

import org.example.controller.Controller;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.ExpenseDTO;
import org.example.utils.enums.ETable;
import org.example.utils.excpetions.ExpensesManagerException;
import org.example.utils.excpetions.FieldException;
import org.example.utils.excpetions.ServerException;
import org.example.view.listPanel.ExpensesList;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpensesView extends View {
    private static final Controller controller = new Controller();
    private static final JTextField nameField = new JTextField(40);
    private static final JTextField amountField = new JTextField(25);
    private static JComboBox<String> categoryField;
    private static Dimension screenDimension;
    private static final ExpensesList expensesList = new ExpensesList();

    public ExpensesView() {
        super("Expenses", JFrame.EXIT_ON_CLOSE);
        // Main container
        this.setLayout(new BorderLayout());
        // Components
        this.add(new NavBar(this), BorderLayout.NORTH);
        this.add(expensesList, BorderLayout.CENTER);
        this.add(new NewExpenseForm(this), BorderLayout.SOUTH);
        // Group components to avoid unused space
        this.pack();
        screenDimension = new Dimension(this.getWidth(), this.getHeight());
    }

    private static class NavBar extends JPanel {
        NavBar(ExpensesView panel) {
            // Main container
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            // Buttons
            this.add(new CreateButton(panel));
            this.add(new DeleteButton(panel));
            this.add(new CategoryButton(panel));
        }
    }

    private static class CreateButton extends JButton {
        public CreateButton(ExpensesView expensesView) {
            super("Create");
            // On click
            this.addActionListener(event -> createNewExpense(expensesView));
        }

        private void createNewExpense(ExpensesView expensesView) {
            try {
                // Create new Expense in Controller
                ExpenseDTO expenseDTO = expenseDTOCreation();
                controller.create(expenseDTO);
                // Add new row in expenses table
                expensesList.addRow(expenseDTO);
            } catch (ExpensesManagerException e) {
                // Show error dialog with threw message from controller
                e.showErrorDialog(expensesView, e.getMessage());
            }

        }

        private ExpenseDTO expenseDTOCreation() throws ExpensesManagerException {
            // Get selected category in drop down
            String category = (String) categoryField.getSelectedItem();
            double amount;

            if (nameField.getText().isEmpty() ||
                    amountField.getText().isEmpty() ||
                    category == null)
                // Validate required fields existence
                throw new FieldException();

            try {
                // Amount validation/assignation
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException e) {
                throw new FieldException("Amount is not a number.");
            }
            return new ExpenseDTO(
                    nameField.getText(),
                    amount,
                    new Date(Calendar.getInstance().getTimeInMillis()),
                    category
            );
        }
    }

    private static class DeleteButton extends JButton {
        public DeleteButton(ExpensesView expensesView) {
            super("Delete");
            // On click
            this.addActionListener(event -> deleteExpense(expensesView));
        }

        private void deleteExpense(ExpensesView expensesView) {
            try {
                // Get selected row
                JTable expensesTable = expensesList.getTable();
                int selectedRow = getSelectedRow(expensesTable);
                int expenseId = getSelectedExpense(selectedRow, expensesTable);
                // Delete in database and in view table
                controller.delete(expenseId, ETable.EXPENSE);
                expensesList.deleteRow(selectedRow);
            } catch (ExpensesManagerException e) {
                // Show error dialog with threw message from controller
                e.showErrorDialog(expensesView, e.getMessage());
            }
        }
        private int getSelectedExpense(int selectedRow, JTable expensesTable) {
            // Get selected expense in row
            return (int) expensesTable.getValueAt(selectedRow, 0);
        }

        private int getSelectedRow(JTable expensesTable) throws FieldException {
            // Get selected row in table
            int selectedRow = expensesTable.getSelectedRow();
            if (selectedRow  == -1) {
                throw new FieldException("Row is not selected.");
            }
            return selectedRow;
        }
    }

    private static class CategoryButton extends JButton {
        public CategoryButton(ExpensesView panel) {
            super("Categories");
            // On click
            this.addActionListener(event -> goToCategoryView(panel));
        }

        // Creates new category view
        private void goToCategoryView(ExpensesView panel) {
            new CategoryView(screenDimension, panel);
        }
    }
    private static class NewExpenseForm extends JPanel {
        NewExpenseForm(ExpensesView expensesView) {
            // Form with necessary fields to create new expense
            categoryField = categoryField(expensesView);
            add(new JLabel("Name"));
            add(nameField);
            add(new JLabel("Amount"));
            add(amountField);
            add(categoryField);
        }
    }

    public void addCategory(String categoryName) {
        // Add new category in drop down
        categoryField.addItem(categoryName);
        this.pack();
    }

    public void deleteCategory(String categoryName) {
        // Delete category in drop down
        categoryField.removeItem(categoryName);
        this.pack();
    }

    private static JComboBox<String> categoryField(ExpensesView expensesView) {
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        JComboBox<String> categoryField = new JComboBox<>();

        try {
            // Get all categories from controller
            controller.getAll(ETable.CATEGORY).forEach(c -> categoriesDTO.add((CategoryDTO) c));
        } catch (ServerException e) {
            e.showErrorDialog(expensesView, e.getMessage());
        }
        // Add all categories in drop down
        categoriesDTO.forEach(c -> categoryField.addItem(c.getName()));

        return categoryField;
    }
}
