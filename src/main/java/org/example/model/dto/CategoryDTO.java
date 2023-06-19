package org.example.model.dto;

public class CategoryDTO implements IDTO {
    private Integer id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public CategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

