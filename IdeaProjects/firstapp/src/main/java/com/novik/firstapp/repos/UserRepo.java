package com.novik.firstapp.repos;

import com.novik.firstapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepo extends CrudRepository<User, Long> {

    void deleteById(Long Id);


    UserDetails findByUsername(String username);
}
