package org.example;


import org.example.model.Service;
import org.example.utils.DTOParser;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.ExpenseDTO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Calendar;

public class EntryPoint {
    public static void main(String[] args) {
        System.out.println(DBConnection.getConnection());
        CategoryDTO categoryDTO = new CategoryDTO("food");
        ExpenseDTO expenseDTO = new ExpenseDTO("burger", 2000.0, new Date(Calendar.getInstance().getTimeInMillis()), categoryDTO);

        new Service().get(1, DTOParser.tableName(categoryDTO));

    }
}