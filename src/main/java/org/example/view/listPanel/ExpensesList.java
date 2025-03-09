package org.example.view.listPanel;

import org.example.model.dto.ExpenseDTO;
import org.example.utils.enums.ETable;

public class ExpensesList extends AbstractListPanel<ExpenseDTO> {

    public ExpensesList() {
        // Create new table and set columns
        super(ETable.EXPENSE, new String[]{"ID", "NAME", "AMOUNT", "DATE", "CATEGORY"});
    }

    @Override
    public void addRow(ExpenseDTO expense) {
        // Populate expenses table
        tableModel.addRow(new Object[] {
                expense.getId(), expense.getName(), expense.getAmount(), expense.getDate(), expense.getCategory()
        });
    }
}
