package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long userId){
        User i = userService.getUserLogged();
        if(!i.hasRole("ROLE_ADMIN") && !i.getId().equals(userId)){
            throw new ForbiddenException("Access Denied");
        }
    }

}
