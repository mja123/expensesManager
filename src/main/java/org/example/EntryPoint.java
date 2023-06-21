package org.example;


import org.example.model.Service;
import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.ExpenseDTO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EntryPoint {
    public static void main(String[] args) {
        System.out.println(DBConnection.getConnection());
        CategoryDTO categoryDTO = new CategoryDTO("transport");
        ExpenseDTO expenseDTO = new ExpenseDTO("burger", 3000.0, new Date(Calendar.getInstance().getTimeInMillis()), 1);

        Service service = new Service();
//        service.create(expenseDTO);
//        service.create(categoryDTO);
//
//        ExpenseDTO expenseDTO1 = (ExpenseDTO) service.get(2, DTOParser.tableName(expenseDTO));
//        System.out.println(expenseDTO1.getId());
//        System.out.println(expenseDTO1.getName());
//        System.out.println(expenseDTO1.getAmount());
//        System.out.println(expenseDTO1.getDate());
//        System.out.println(expenseDTO1.getCategory());
//
//
//        CategoryDTO categoryDTO1 = (CategoryDTO) service.get(1, DTOParser.tableName(categoryDTO));
//        System.out.println(categoryDTO1.getId());
//        System.out.println(categoryDTO1.getName());
//        List<IDTO> categories = service.getAll(DTOParser.tableName(categoryDTO));
//        List<CategoryDTO> categoriesDTO = new ArrayList<>();
//        categories.forEach(c -> categoriesDTO.add((CategoryDTO) c));
//        categoriesDTO.forEach(c -> {
//            System.out.println(c.getName());
//            System.out.println(c.getId());
//        });

//        List<IDTO> expenses = service.getAll(DTOParser.tableName(expenseDTO));
//        List<ExpenseDTO> expensesDTO = new ArrayList<>();
//        expenses.forEach(c -> expensesDTO.add((ExpenseDTO) c));
//        expensesDTO.forEach(c -> {
//            System.out.println(c.getName());
//            System.out.println(c.getId());
//            System.out.println(c.getAmount());
//            System.out.println(c.getDate());
//            System.out.println(c.getCategory());
//
//        });

        List<ExpenseDTO> expensesDTOCategory = service.getAllFromCategory(1);
        expensesDTOCategory.forEach(c -> {
            System.out.println(c.getName());
            System.out.println(c.getId());
            System.out.println(c.getAmount());
            System.out.println(c.getDate());
            System.out.println(c.getCategory());

        });

//        service.delete(4, DTOParser.tableName(categoryDTO));
    }
}