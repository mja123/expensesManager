package org.example.controller;

import org.example.model.Service;
import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;
import org.example.utils.ObjectBuilder;
import org.example.utils.enums.ETable;
import org.example.utils.excpetions.DatabaseConstraintException;
import org.example.utils.excpetions.ServerException;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    private static final Service service = new Service();

    public Controller() {
    }

    public void create(IDTO dtoObject) throws ServerException {
        try {
            service.create(dtoObject);
            // Create dto in database
            ResultSet expenseCrated = service.get(dtoObject.getName(), ETable.fromValue(DTOParser.getTableName(dtoObject)));
            expenseCrated.last();
            // Set id assign in database in dto
            dtoObject.setId(getDTOFromResultSet(expenseCrated, ETable.EXPENSE).getId());
        } catch (ServerException | SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new DatabaseConstraintException();
            }
            throw new ServerException(e.getMessage());
        }
    }
    public List<IDTO> getAll(ETable table) throws ServerException {
        // Get all records in table
        ResultSet getAllRecords = service.getAll(table);
        try {
            return ObjectBuilder.setObjects(getAllRecords, table.getTableName());
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | SQLException e) {
            throw new ServerException();
        }
    }

    public void delete(int id, ETable table) throws ServerException {
        // Delete record in table
        try {
            service.delete(id, table);
        } catch (ServerException e) {
            if (e.getMessage().contains("foreign key constraint fails")) {
                throw new DatabaseConstraintException("You need to delete expenses with this category before.");
            }
        }

    }
    public static Integer getCategoryId(String name) throws ServerException {
        // Get category id by name
        ResultSet category = service.get(name, ETable.CATEGORY);
        return getDTOFromResultSet(category, ETable.CATEGORY).getId();
    }

    private static IDTO getDTOFromResultSet(ResultSet resultSet, ETable table) throws ServerException {
        // Set values in dto from database result set
        try {
            return ObjectBuilder.setValues(resultSet, table.getTableName());
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new ServerException();
        }
    }
}
