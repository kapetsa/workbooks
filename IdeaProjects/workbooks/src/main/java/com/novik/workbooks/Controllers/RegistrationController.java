package com.novik.workbooks.Controllers;

import com.novik.workbooks.domain.User;
import com.novik.workbooks.domain.Workbook;
import com.novik.workbooks.services.LocalizationService;
import com.novik.workbooks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private LocalizationService localizationService;

    @GetMapping("/registration")
    public String registration(HttpServletRequest request){
        localizationService.setLocalization(request);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Validated User user,
                          BindingResult bindingResult,
                          Model model){

        if (bindingResult.hasErrors()) {

            System.out.println("АХТУНГ! ВАЛИДАЦИЯ АЛЕС!!");
            return "registration";
        } else  {

        if(!userService.addUser(user)){
            model.addAttribute("message", "Пользователь с таким именем уже существуeт");
            return "registration";
        }

        return "redirect:/login";  }
    }

    @GetMapping("/activate/{code}")
    public String activate(HttpServletRequest request, Model model, @PathVariable String code){
        localizationService.setLocalization(request);
        boolean isActivated = userService.activateUser(code);

        if(isActivated) {
            model.addAttribute("message", "Учетная запись активирована");
        } else { model.addAttribute("message", "Неверный код активации");}

        return "login";
    }
}