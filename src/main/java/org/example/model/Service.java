package org.example.model;

import org.example.DBConnection;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;
import org.example.utils.ObjectBuilder;
import org.example.utils.QueryBuilder;

import java.sql.*;
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
            statement = connection.prepareStatement(QueryBuilder.create(object));
            QueryBuilder.replacePlaceholders(statement, data);
            if (statement.executeUpdate() < 1) throw new SQLException("Record wasn't created");
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(query);
    }

    @Override
    public Class<?> get(Integer id, String table) {
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ")
                    .append(table)
                    .append(" WHERE id = ")
                    .append(id);
            ResultSet result = connection.createStatement().executeQuery(query.toString());
            return ObjectBuilder.setValues(result, table);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<IDTO> getAll() {
        return null;
    }

    @Override
    public IDTO modify(IDTO object) {
        return null;
    }

    @Override
    public Integer delete(IDTO object) {
        return null;
    }
}
