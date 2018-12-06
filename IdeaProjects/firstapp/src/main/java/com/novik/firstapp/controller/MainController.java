package com.novik.firstapp.controller;

import com.novik.firstapp.domain.Role;
import com.novik.firstapp.domain.User;
import com.novik.firstapp.domain.Usr;
import com.novik.firstapp.repos.UserRepo;
import com.novik.firstapp.repos.UsrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import java.util.Collections;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UsrRepo usrRepo;


    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<User> allUsers = userRepo.findAll();
        model.put("allUsers", allUsers);
        return "list";
    }



    @GetMapping("/registration")
    public String reg() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Usr us, Map <String, Object> model) {
        Usr usrFromDb =  usrRepo.findByUsername(us.getUsername());
        if (usrFromDb!=null){
            model.put("message", "User exists!");
            return "registration";
        }
        us.setActive(true);
        us.setRoles(Collections.singleton(Role.ADMIN));
        usrRepo.save(us);
        User nUs = new User(us.getUsername());
        nUs.setId(us.getId());
        userRepo.save(nUs);
        return "redirect:/login";
    }






}