package com.devsuperior.dscommerce.services;


import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projection.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRespository respository;

    @Override
    public UserDetails loadUserByUsername(String username) {
       List<UserDetailsProjection> userDetailsProjectionList = respository.searchUserAndRolesByEmail(username);
       if(userDetailsProjectionList.isEmpty()){
           throw new UsernameNotFoundException(username);
       }
       User u = new User();
       u.setEmail(username);
       u.setPassword(userDetailsProjectionList.get(0).getPassword());
       for(UserDetailsProjection projection:userDetailsProjectionList){
           u.addRole(new Role(projection.getRoleId(),projection.getAuthority()));
       }

       return u;
    }
}
