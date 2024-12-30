package org.example.model;

import org.example.model.dto.ExpenseDTO;
import org.example.model.dto.IDTO;

import java.util.List;

public interface IService {
    void create(IDTO object);
    IDTO get(Integer id, String table);
    IDTO get(String name, String table);
    List<IDTO> getAll(String table);
    void delete(Integer id, String table);
    List<ExpenseDTO> getAllFromCategory(Integer categoryId);
}
