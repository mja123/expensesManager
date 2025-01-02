package org.example.model;

import org.example.model.dto.ExpenseDTO;
import org.example.model.dto.IDTO;
import org.example.utils.enums.ETable;

import java.sql.ResultSet;
import java.util.List;

public interface IService {
    void create(IDTO object);
    ResultSet get(String name, ETable table);
//    IDTO getLast(String name, ETable table);
    ResultSet getAll(ETable table);
    void delete(Integer id, ETable table);
    ResultSet getAllFromCategory(Integer categoryId);
}
