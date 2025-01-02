package org.example.model.controller;

import org.example.model.Service;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.ExpenseDTO;
import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;
import org.example.utils.ObjectBuilder;
import org.example.utils.enums.ETable;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static final Service service = new Service();

    public Controller() {
    }

    public void create(IDTO dtoObject) {
        service.create(dtoObject);
        ResultSet expenseCrated = service.get(dtoObject.getName(), ETable.fromValue(DTOParser.getTableName(dtoObject)));
        dtoObject.setId(getDTOFromResultSet(expenseCrated, ETable.EXPENSE).getId());
    }
    public List<IDTO> getAll(ETable table) {
        ResultSet getAllRecords = service.getAll(table);
        try {
            return ObjectBuilder.setObjects(getAllRecords, table.getTableName());
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getCategoryId(String name) {
        ResultSet category = service.get(name, ETable.CATEGORY);
        System.out.println("Result: " + category);
        return getDTOFromResultSet(category, ETable.CATEGORY).getId();
    }

    // Currently not in used
    public List<ExpenseDTO> getAllExpensesFromCategory(CategoryDTO category) {
        try {
            ResultSet result = service.getAllFromCategory(category.getId());
            List<IDTO> rawDTO;
            rawDTO = ObjectBuilder.setObjects(result, "expenses");
            List<ExpenseDTO> expensesDTO = new ArrayList<>();
            rawDTO.forEach(r -> expensesDTO.add((ExpenseDTO) r));
            return expensesDTO;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static IDTO getDTOFromResultSet(ResultSet resultSet, ETable table) {
        try {
            System.out.println("Result: " + resultSet);
            return ObjectBuilder.setValues(resultSet, table.getTableName());
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
