package org.example.model;

import org.example.model.dto.ExpenseDTO;
import org.example.model.dto.IDTO;
import org.example.utils.enums.ETable;
import org.example.utils.excpetions.ServerException;

import java.sql.ResultSet;
import java.util.List;

public interface IService {
    // Service Interface with base methods
    void create(IDTO object) throws ServerException;
    ResultSet get(String name, ETable table) throws ServerException;
    ResultSet getAll(ETable table) throws ServerException;
    void delete(Integer id, ETable table) throws ServerException;
}
