package org.example.model;

import org.example.DBConnection;
import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;
import org.example.utils.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public Integer create(IDTO object) {
        try {
            Map<String, Map<Object, Object>> data = DTOParser.getDTOData(object);
            statement = connection.prepareStatement(QueryBuilder.create(object));
            QueryBuilder.replacePlaceholders(statement, data);
            statement.execute();
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
//            throw new RuntimeException(e);
            System.out.println("ERROR " + e.getMessage());
        }
        System.out.println(query);

        return null;
    }

    @Override
    public IDTO get(Integer id) {
        return null;
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
