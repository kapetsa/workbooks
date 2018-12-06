package com.novik.workbooks.Controllers;

import com.novik.workbooks.domain.User;
import com.novik.workbooks.domain.Workbook;
import com.novik.workbooks.repositories.UserRepo;
import com.novik.workbooks.repositories.WBookRepo;
import com.novik.workbooks.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private WBookRepo wbookrepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LocalizationService localizationService;

    @GetMapping("/")
    public String main(HttpServletRequest request, Model model) {
        localizationService.setLocalization(request);
        return "main";
    }


    @GetMapping("/wbooks")
    public String wbooks(HttpServletRequest request, Model model){
        localizationService.setLocalization(request);
        Iterable<Workbook> workbooks = wbookrepo.findAll();
        model.addAttribute("workbooks", workbooks);

        return "wbooks";
    }

    @GetMapping("/delete/{id}")
    public String UserDel(@PathVariable Long id){
        userRepo.deleteById(id);
        return "redirect:/user";
    }

    @GetMapping("/createWB")
    public String createWb(HttpServletRequest request){

        localizationService.setLocalization(request);
        return "createWB";
    }

    @PostMapping("/createWB")
    public String addWB(@AuthenticationPrincipal User user,
                        @Validated Workbook workbook,
                        BindingResult bindingResult,
                        Model model) {


        if (bindingResult.hasErrors()) {

 System.out.println("АХТУНГ! ВАЛИДАЦИЯ АЛЕС!!");
            return "createWB";
        } else {
            workbook.setAuthor(user);
            wbookrepo.save(workbook);
        return "redirect:/wbooks";
    }}


}