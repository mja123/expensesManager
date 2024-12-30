package org.example.model;

import org.example.model.dto.ExpenseDTO;
import org.example.model.dto.IDTO;
import org.example.utils.enums.ETable;

import java.util.List;

public interface IService {
    void create(IDTO object);
    IDTO get(String name, ETable table);
//    IDTO getLast(String name, ETable table);
    List<IDTO> getAll(ETable table);
    void delete(Integer id, ETable table);
    List<ExpenseDTO> getAllFromCategory(Integer categoryId);
}
