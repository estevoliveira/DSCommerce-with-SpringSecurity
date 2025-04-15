package com.devsuperior.dscommerce.services;


import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projection.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    protected User getUserLogged(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            return respository.findByEmail(username).get();

        }catch (Exception e){
            throw new UsernameNotFoundException("");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getMe(){
        return new UserDTO(getUserLogged());
    }
}
