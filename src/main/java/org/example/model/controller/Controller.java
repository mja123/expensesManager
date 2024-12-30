package org.example.model.controller;

import org.example.model.Service;
import org.example.model.dto.CategoryDTO;
import org.example.model.dto.IDTO;
import org.example.utils.enums.ETables;

public class Controller {
    private static final Service service = new Service();

    public Controller() {
    }

    public void create(IDTO dtoObject) {
        service.create(dtoObject);
    }

    public static Integer getCategoryId(String name) {
//        return ((CategoryDTO) service.get(name, ETables.CATEGORY.ge)).getId();
        return ((CategoryDTO) service.get(name, "categories")).getId();
    }
}
