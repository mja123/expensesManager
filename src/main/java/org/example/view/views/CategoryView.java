package org.example.view.views;

import org.example.controller.Controller;
import org.example.utils.excpetions.DuplicateEntryException;
import org.example.utils.excpetions.ExpensesManagerException;
import org.example.utils.excpetions.FieldException;
import org.example.utils.excpetions.ServerException;
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
        this.setLayout(new BorderLayout());
        this.add(new CategoryView.NavBar(expensesPanel, this), BorderLayout.NORTH);
        this.add(categoryList, BorderLayout.CENTER);
        this.add(new CategoryView.NewExpenseForm(), BorderLayout.SOUTH);
        this.setSize(screenDimension);
    }

    private static class NavBar extends JPanel {
        NavBar(ExpensesView expensesPanel, CategoryView categoryPanel) {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.add(new CategoryView.CreateButton(expensesPanel, categoryPanel));
            this.add(new CategoryView.DeleteButton(expensesPanel, categoryPanel));
        }
    }

    private static class CreateButton extends JButton {
        CreateButton(ExpensesView expensesPanel, CategoryView categoryPanel) {
            super("Create");
            this.addActionListener(event -> createNewExpense(expensesPanel, categoryPanel));
        }

        private void createNewExpense(ExpensesView expensesPanel, CategoryView categoryPanel) {
            try {
                CategoryDTO categoryDTO = new CategoryDTO(nameField.getText());
                if (nameField.getText().isEmpty()) throw new FieldException();
                controller.create(categoryDTO);
                categoryList.addRow(categoryDTO);
                expensesPanel.addCategory(categoryDTO.getName());
            } catch (ExpensesManagerException e) {
                e.showErrorDialog(categoryPanel, e.getMessage());
            }
        }
    }

    private static class DeleteButton extends JButton {
        DeleteButton(ExpensesView expensesPanel, CategoryView categoryPanel) {
            super("Delete");
            this.addActionListener(event -> deleteExpense(expensesPanel, categoryPanel));
        }

        private void deleteExpense(ExpensesView expensesPanel, CategoryView categoryPanel) {
            JTable categoriesTable = categoryList.getTable();
            int selectRow = categoriesTable.getSelectedRow();
            String categoryName = (String) categoriesTable.getValueAt(selectRow, 1);
            int categoryId = (int) categoriesTable.getValueAt(selectRow, 0);
//            controller.delete(categoryId, ETable.CATEGORY);
            categoryList.deleteRow(selectRow);
            expensesPanel.deleteCategory(categoryName);
        }
    }
    private static class NewExpenseForm extends JPanel {
        NewExpenseForm() {
            add(new JLabel("Name"));
            add(nameField);
        }
    }
}
