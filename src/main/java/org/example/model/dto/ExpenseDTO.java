package org.example.model.dto;

import java.sql.Date;

public class ExpenseDTO implements IDTO {
    private Integer id;
    private String name;
    private Double amount;
    private Date date;
    private Integer category;

    public ExpenseDTO() {
    }

    public ExpenseDTO(String name, Double amount, Date date, Integer category) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public ExpenseDTO(Integer id, String name, Double amount, Date date, Integer category) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.category = category;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
