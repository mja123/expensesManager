package org.example.view.frame;

import org.example.controller.Controller;
import org.example.utils.excpetions.ExpensesManagerException;
import org.example.utils.excpetions.FieldException;
import org.example.model.dto.CategoryDTO;
import org.example.utils.enums.ETable;
import org.example.view.listPanel.CategoryList;

import javax.swing.*;
import java.awt.*;

public class CategoryView extends View {
    private static final Controller controller = new Controller();
    private static final JTextField nameField = new JTextField(40);
    private static final CategoryList categoryList = new CategoryList();

    public CategoryView(Dimension screenDimension, ExpensesView expensesPanel) {
        super("Categories", JFrame.DISPOSE_ON_CLOSE);
        // Main container
        this.setLayout(new BorderLayout());
        // Components
        this.add(new CategoryView.NavBar(expensesPanel, this), BorderLayout.NORTH);
        this.add(categoryList, BorderLayout.CENTER);
        this.add(new CategoryView.NewExpenseForm(), BorderLayout.SOUTH);
        // Set same size as expenses panel
        this.setSize(screenDimension);
    }

    private static class NavBar extends JPanel {
        NavBar(ExpensesView expensesPanel, CategoryView categoryPanel) {
            // Main container
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            // Buttons
            this.add(new CategoryView.CreateButton(expensesPanel, categoryPanel));
            this.add(new CategoryView.DeleteButton(expensesPanel, categoryPanel));
        }
    }

    private static class CreateButton extends JButton {
        CreateButton(ExpensesView expensesPanel, CategoryView categoryPanel) {
            super("Create");
            // On click
            this.addActionListener(event -> createNewCategory(expensesPanel, categoryPanel));
        }

        private void createNewCategory(ExpensesView expensesPanel, CategoryView categoryPanel) {
            try {
                if (nameField.getText().isEmpty()) throw new FieldException();
                CategoryDTO categoryDTO = new CategoryDTO(nameField.getText());
                // Create new category in Controller
                controller.create(categoryDTO);
                // Add new row in Category table
                categoryList.addRow(categoryDTO);
                // Add new category in Expenses category drop down
                expensesPanel.addCategory(categoryDTO.getName());
            } catch (ExpensesManagerException e) {
                // Show error dialog with threw message from controller
                e.showErrorDialog(categoryPanel, e.getMessage());
            }
        }
    }

    private static class DeleteButton extends JButton {
        DeleteButton(ExpensesView expensesPanel, CategoryView categoryPanel) {
            super("Delete");
            // On click
            this.addActionListener(event -> deleteExpense(expensesPanel, categoryPanel));
        }

        private void deleteExpense(ExpensesView expensesPanel, CategoryView categoryPanel) {
            try {
                JTable categoriesTable = categoryList.getTable();
                int selectedRow = getSelectedRow(categoriesTable);
                // Delete selected category from Controller
                controller.delete(getSelectedCategory(selectedRow, categoriesTable), ETable.CATEGORY);
                // Delete selected category from categories table
                expensesPanel.deleteCategory(getSelectedCategoryName(selectedRow, categoriesTable));
                // Delete selected category from categories drop down in Expenses
                categoryList.deleteRow(selectedRow);
            } catch (ExpensesManagerException e) {
                e.showErrorDialog(categoryPanel, e.getMessage());
            }
        }

        private String getSelectedCategoryName(int selectedRow, JTable categoriesTable) {
            return (String) categoriesTable.getValueAt(selectedRow, 1);
        }
        private int getSelectedCategory(int selectedRow, JTable categoriesTable) {
            return (int) categoriesTable.getValueAt(selectedRow, 0);
        }

        private int getSelectedRow(JTable categoriesTable) throws FieldException {
            int selectedRow = categoriesTable.getSelectedRow();
            if (selectedRow  == -1) {
                throw new FieldException("Row is not selected.");
            }
            return selectedRow;
        }
    }
    private static class NewExpenseForm extends JPanel {
        NewExpenseForm() {
            add(new JLabel("Name"));
            add(nameField);
        }
    }
}
