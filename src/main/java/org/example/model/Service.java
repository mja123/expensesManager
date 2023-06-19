package org.example.model;

import org.example.DBConnection;
import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class Service implements IService {
    private final Connection connection;
    private PreparedStatement statement;
    private String query;
    public Service() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public Integer create(IDTO object) {
        DTOParser.getDTOData(object);
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
