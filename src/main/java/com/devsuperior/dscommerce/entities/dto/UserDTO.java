package com.devsuperior.dscommerce.entities.dto;

import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;


    private List<String> roles = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(User u) {
        this.id = u.getId();
        this.name =  u.getName();
        this.email = u.getEmail();
        this.phone = u.getPhone();
        this.birthDate = u.getBirthDate();
        for(GrantedAuthority ga : u.getRoles()){
            this.roles.add(ga.getAuthority());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getRoles() {
        return roles;
    }
}
