package com.novik.workbooks.services;

import com.novik.workbooks.domain.Role;
import com.novik.workbooks.domain.User;
import com.novik.workbooks.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user){
        if(!StringUtils.isEmpty(user.getUsername())&&!StringUtils.isEmpty(user.getPassword())){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb!=null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n +" +
                            "Добро пожаловать на сайт 'Конспекты'! Для подтверждения адреса электронной почты пройдите, пожалуйста, по ссылке: http://localhost:8080/activate/%s ",
                    user.getUsername(),
                    user.getActivationCode()
            );
   mailSender.send(user.getEmail(),"Активация учетной записи", message);
        }
        return true;
    }

    else return false;}

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null)
               {return false;}
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }
}
