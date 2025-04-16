package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Category;

public class CategoriaDTO {

    private Long id;
    private String name;

    public CategoriaDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public CategoriaDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
