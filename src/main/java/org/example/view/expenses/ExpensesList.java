package org.example.view.expenses;

import org.example.model.dto.ExpenseDTO;
import org.example.utils.enums.ETable;
import org.example.view.AbstractListPanel;

public class ExpensesList extends AbstractListPanel<ExpenseDTO> {

    public ExpensesList() {
        super(ETable.EXPENSE, new String[]{"ID", "NAME", "AMOUNT", "DATE", "CATEGORY"});
    }

    @Override
    protected void addRow(ExpenseDTO expense) {
        tableModel.addRow(new Object[]{
                expense.getId(), expense.getName(), expense.getAmount(), expense.getDate(), expense.getCategory()
        });
    }
}
