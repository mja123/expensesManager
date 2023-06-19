package org.example.model;

import org.example.model.dto.IDTO;

import java.util.List;

public interface IService {
    Integer create(IDTO object);
    IDTO get(Integer id);
    List<IDTO> getAll();
    IDTO modify(IDTO object);
    Integer delete(IDTO object);
}
