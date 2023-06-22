package org.example.view;

import org.example.model.Service;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.ExpenseDTO;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpensesView extends View {
    private static final Service service = new Service();
    private static final JTextField nameField = nameField();
    private static final JTextField amountField = amountField();
    private static final JComboBox<String> categoryField = categoryField();

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
            this.add(new CreateButton());
            this.add(new JButton("Delete"));
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
                    categoryField.getSelectedIndex()
            );
            service.create(expenseDTO);
        }
    }

    private static class ExpensesList extends JPanel {
        ExpensesList() {
            Object[] columns = { "ID", "NAME", "AMOUNT", "DATE", "CATEGORY" };
            setLayout(new BorderLayout());
            add(new JScrollPane(new JTable(/* rows , columns */)));
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

        service.getAll("categories").forEach(c -> categoriesDTO.add((CategoryDTO) c));
        categoriesDTO.forEach(c -> categoryField.addItem(c.getName()));

        return categoryField;
    }
}

