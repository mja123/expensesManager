package org.example.model;

import org.example.DBConnection;
import org.example.model.dto.ExpenseDTO;
import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;
import org.example.utils.ObjectBuilder;
import org.example.utils.QueryBuilder;
import org.example.utils.enums.ETable;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service implements IService {
    private final Connection connection;
    private StringBuilder query;
    public Service() {
        this.connection = DBConnection.getConnection();
        this.query = new StringBuilder();
    }

    @Override
    public void create(IDTO object) {
        query = new StringBuilder();
        try {
            Map<String, Map<Object, Object>> data = DTOParser.getDTOData(object);
            PreparedStatement statement = connection.prepareStatement(QueryBuilder.create(object, data));
            QueryBuilder.replacePlaceholders(statement, data);
            System.out.println(statement.toString());
            if (statement.executeUpdate() == 0) throw new SQLException("Record wasn't created");
            System.out.println(statement);
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(query);
    }


    @Override
    public ResultSet get(String name, ETable table) {
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ")
                    .append(table.getTableName())
                    .append(" WHERE name = ")
                    .append("'")
                    .append(name)
                    .append("'")
                    .append(";");
            System.out.println(query);
            return connection.createStatement().executeQuery(query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAll(ETable table) {
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ")
                    .append(table.getTableName())
                    .append(";");
            return connection.createStatement().executeQuery(query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id, ETable table) {
        try {
            query = new StringBuilder();
            query.append("DELETE FROM ")
                    .append(table.getTableName())
                    .append(" WHERE id = ")
                    .append(id)
                    .append(";");

            if (connection.createStatement().executeUpdate(query.toString()) == 0)
                throw new SQLException("We couldn't delete record with id: " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ResultSet getAllFromCategory(Integer categoryId) {
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM expenses WHERE category = ")
                    .append(categoryId)
                    .append(";");
            return connection.createStatement().executeQuery(query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
