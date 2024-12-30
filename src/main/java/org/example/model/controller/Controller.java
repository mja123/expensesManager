package org.example.model.controller;

import org.example.model.Service;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.IDTO;
import org.example.utils.DTOParser;
import org.example.utils.enums.ETable;

import java.util.List;

public class Controller {
    private static final Service service = new Service();

    public Controller() {
    }

    public void create(IDTO dtoObject) {
        service.create(dtoObject);
        dtoObject.setId(service.get(dtoObject.getName(), ETable.fromValue(DTOParser.getTableName(dtoObject))).getId());
    }
    public List<IDTO> getAll(ETable table) {
        return service.getAll(table);
    }

    public static Integer getCategoryId(String name) {
        return (service.get(name, ETable.CATEGORY)).getId();
    }
}
