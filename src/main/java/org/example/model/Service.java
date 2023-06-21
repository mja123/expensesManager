package org.example.model;

import org.example.DBConnection;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.ExpenseDTO;
import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;
import org.example.utils.ObjectBuilder;
import org.example.utils.QueryBuilder;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service implements IService {
    private final Connection connection;
    private PreparedStatement statement;
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
            statement = connection.prepareStatement(QueryBuilder.create(object, data));
            QueryBuilder.replacePlaceholders(statement, data);
            System.out.println(statement.toString());
            if (statement.executeUpdate() == 0) throw new SQLException("Record wasn't created");
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(query);
    }

    @Override
    public IDTO get(Integer id, String table) {
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ")
                    .append(table)
                    .append(" WHERE id = ")
                    .append(id)
                    .append(";");
            ResultSet result = connection.createStatement().executeQuery(query.toString());
            return ObjectBuilder.setValues(result, table);

        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<IDTO> getAll(String table) {
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ")
                    .append(table)
                    .append(";");
            ResultSet result = connection.createStatement().executeQuery(query.toString());
            return ObjectBuilder.setObjects(result, table);
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Integer id, String table) {
        try {
            query = new StringBuilder();
            query.append("DELETE FROM ")
                    .append(table)
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
    public List<ExpenseDTO> getAllFromCategory(Integer categoryId) {
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM expenses WHERE category = ")
                    .append(categoryId)
                    .append(";");
            ResultSet result = connection.createStatement().executeQuery(query.toString());
            List<IDTO> rawDTO = ObjectBuilder.setObjects(result, "expenses");
            List<ExpenseDTO> expensesDTO = new ArrayList<>();
            rawDTO.forEach(r -> expensesDTO.add((ExpenseDTO) r));
            return expensesDTO;
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
