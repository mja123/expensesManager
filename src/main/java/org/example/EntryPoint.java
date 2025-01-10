package org.example;

import org.example.view.views.ExpensesView;

public class EntryPoint {
    public static void main(String[] args) {
        new ExpensesView();
//        System.out.println(DBConnection.getConnection());
//        String categoryName = "food";
//        CategoryDTO categoryDTO = new CategoryDTO(categoryName);
//
//        Service service = new Service();
//        service.create(categoryDTO);
//        CategoryDTO categoryDTO1 = (CategoryDTO) service.get(categoryName, DTOParser.tableName(categoryDTO));
//        ExpenseDTO expenseDTO = new ExpenseDTO("burger", 2000.0, new Date(Calendar.getInstance().getTimeInMillis()), categoryDTO1.getId());
//        service.create(expenseDTO);
//        System.out.println(categoryDTO1.getId());
//        System.out.println(categoryDTO1.getName());
    }
}

/*
TODO:
- Uncomment all commented methods in views folder and add exceptions with dialog messages
 */