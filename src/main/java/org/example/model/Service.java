package org.example.model;

import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;
import org.example.utils.QueryBuilder;
import org.example.utils.enums.ETable;
import org.example.utils.excpetions.ServerException;

import java.sql.*;
import java.util.Map;

public class Service implements IService {
    private final Connection connection;
    private StringBuilder query;
    public Service() {
        // Get database connection
        this.connection = DBConnection.getConnection();
        this.query = new StringBuilder();
    }

    @Override
    public void create(IDTO object) throws ServerException {
        // Create new record in table based on dto
        query = new StringBuilder();
        try {
            Map<String, Map<Object, Object>> data = DTOParser.getDTOData(object);
            PreparedStatement statement = connection.prepareStatement(QueryBuilder.create(object, data));
            QueryBuilder.replacePlaceholders(statement, data);
            if (statement.executeUpdate() == 0) throw new SQLException("Record wasn't created");
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            throw new ServerException(e.getMessage());
        }
        System.out.println(query);
    }


    @Override
    public ResultSet get(String name, ETable table) throws ServerException {
        // Get record in table
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
            return connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                    ).executeQuery(query.toString());
        } catch (SQLException e) {
            System.out.println(query);
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public ResultSet getAll(ETable table) throws ServerException {
        // Get all records from table
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ")
                    .append(table.getTableName())
                    .append(";");
            System.out.println(query);
            return connection.createStatement().executeQuery(query.toString());
        } catch (SQLException e) {
            System.out.println(query);
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id, ETable table) throws ServerException {
        // Delete record in table based on id
        try {
            query = new StringBuilder();
            query.append("DELETE FROM ")
                    .append(table.getTableName())
                    .append(" WHERE id = ")
                    .append(id)
                    .append(";");
            System.out.println(query);
            if (connection.createStatement().executeUpdate(query.toString()) == 0)
                throw new SQLException("We couldn't delete record with id: " + id);

        } catch (SQLException e) {
            System.out.println(query);
            throw new ServerException(e.getMessage());
        }
    }
}
