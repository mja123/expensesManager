package org.example;

import org.example.model.Service;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.ExpenseDTO;
import org.example.utils.DTOParser;
import org.example.view.ExpensesView;

import java.sql.Date;
import java.util.Calendar;

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
- Service should return ResultSet, controller must transform in objects
- Add correct logic to Delete button
 */