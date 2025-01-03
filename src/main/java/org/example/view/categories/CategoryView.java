package org.example.view.categories;

import org.example.controller.Controller;
import org.example.model.dto.CategoryDTO;
import org.example.utils.enums.ETable;
import org.example.view.View;
import org.example.view.expenses.ExpensesView;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CategoryView extends View {
    private static final Controller controller = new Controller();
    private static final JTextField nameField = nameField();
    private static final JComboBox<String> categoryField = categoryField();

    public CategoryView(Dimension screenDimension) {
        super("Categories", JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new CategoryView.NavBar(), BorderLayout.NORTH);
        this.add(new CategoryList(), BorderLayout.CENTER);
        this.add(new CategoryView.NewExpenseForm(), BorderLayout.SOUTH);
        this.setSize(screenDimension);
    }

    private static class NavBar extends JPanel {
        NavBar() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.add(new CategoryView.CreateButton());
            this.add(new CategoryView.DeleteButton());
        }
    }

    private static class CreateButton extends JButton {
        CreateButton() {
            super("Create");
            this.addActionListener(event -> createNewExpense());
        }

        private void createNewExpense() {
            CategoryDTO categoryDTO = new CategoryDTO(nameField.getText());
            controller.create(categoryDTO);
            CategoryList.addRow(categoryDTO);
            ExpensesView.addCategory(categoryDTO.getName());
        }
    }

    private static class DeleteButton extends JButton {
        DeleteButton() {
            super("Delete");
            this.addActionListener(event -> deleteExpense());
        }

        private void deleteExpense() {
//            JTable expensesTable = CategoryList.getExpensesTable();
//            int selectRow = expensesTable.getSelectedRow();
//            int expenseId = (int) expensesTable.getValueAt(selectRow, 0);
//            controller.delete(expenseId, ETable.EXPENSE);
//            CategoryList.deleteRow(selectRow);
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
