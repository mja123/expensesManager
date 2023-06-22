package org.example.view;

import jdk.jfr.Category;
import org.example.model.Service;
import org.example.model.dto.ExpenseDTO;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.Calendar;

public class ExpensesView extends View {
    private final Service service = new Service();
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
            this.addActionListener(event -> {
                createNewExpense();
            });
        }

        private void createNewExpense() {
            ExpenseDTO expenseDTO = new ExpenseDTO(
                    nameField.getText(),
                    Double.parseDouble(amountField.getText()),
                    new Date(Calendar.getInstance().getTimeInMillis()),
                    categoryField.getSelectedIndex()
            );
            System.out.println(expenseDTO.getCategory());
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
            this.add(nameField);
            this.add(amountField);
            this.add(categoryField);
        }
    }

    private static JTextField nameField() {

        return new JTextField(20);
    }

    private static JTextField amountField() {

        return new JTextField(20);
    }

    private static JComboBox<String> categoryField() {
        String[] categories = { "a", "b", "c", "d" };

        return new JComboBox<>(categories);
    }
}
