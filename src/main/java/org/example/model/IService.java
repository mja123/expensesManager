package org.example.model;

import org.example.model.dto.IDTO;

import java.util.List;

public interface IService {
    void create(IDTO object);
    Class<?> get(Integer id, String table);
    List<IDTO> getAll();
    IDTO modify(IDTO object);
    Integer delete(IDTO object);
}
