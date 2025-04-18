package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.User;

public class ClientMinDTO {

    private Long id;
    private String name;

    public ClientMinDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public ClientMinDTO(Long id, String name) {
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
