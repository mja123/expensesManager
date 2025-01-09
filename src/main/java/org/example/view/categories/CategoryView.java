package org.example.view.categories;

import org.example.controller.Controller;
import org.example.model.dto.CategoryDTO;
import org.example.utils.enums.ETable;
import org.example.view.View;
import org.example.view.expenses.ExpensesView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryView extends View {
    private static final Controller controller = new Controller();
    private static final JTextField nameField = nameField();
    private static final JComboBox<String> categoryField = categoryField();
    private static final CategoryList categoryList = new CategoryList();

    public CategoryView(Dimension screenDimension, ExpensesView expensesPanel) {
        super("Categories", JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new CategoryView.NavBar(expensesPanel), BorderLayout.NORTH);
        this.add(categoryList, BorderLayout.CENTER);
        this.add(new CategoryView.NewExpenseForm(), BorderLayout.SOUTH);
        this.setSize(screenDimension);
    }

    private static class NavBar extends JPanel {
        NavBar(ExpensesView expensesPanel) {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.add(new CategoryView.CreateButton(expensesPanel));
            this.add(new CategoryView.DeleteButton(expensesPanel));
        }
    }

    private static class CreateButton extends JButton {
        CreateButton(ExpensesView expensesPanel) {
            super("Create");
            this.addActionListener(event -> createNewExpense(expensesPanel));
        }

        private void createNewExpense(ExpensesView expensesPanel) {
            CategoryDTO categoryDTO = new CategoryDTO(nameField.getText());
            controller.create(categoryDTO);
            categoryList.addRow(categoryDTO);
            expensesPanel.addCategory(categoryDTO.getName());
        }
    }

    private static class DeleteButton extends JButton {
        DeleteButton(ExpensesView expensesPanel) {
            super("Delete");
            this.addActionListener(event -> deleteExpense(expensesPanel));
        }

        private void deleteExpense(ExpensesView expensesPanel) {
            JTable categoriesTable = categoryList.getTable();
            int selectRow = categoriesTable.getSelectedRow();
            String categoryName = (String) categoriesTable.getValueAt(selectRow, 1);
            int categoryId = (int) categoriesTable.getValueAt(selectRow, 0);
            controller.delete(categoryId, ETable.CATEGORY);
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

    private static JTextField nameField() {
        return new JTextField(40);
    }

    private static JComboBox<String> categoryField() {
        JComboBox<String> categoryField = new JComboBox<>();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();

        controller.getAll(ETable.CATEGORY).forEach(c -> categoriesDTO.add((CategoryDTO) c));
        categoriesDTO.forEach(c -> categoryField.addItem(c.getName()));

        return categoryField;
    }
}
