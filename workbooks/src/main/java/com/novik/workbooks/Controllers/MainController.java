package com.novik.workbooks.Controllers;

import com.novik.workbooks.domain.User;
import com.novik.workbooks.domain.Workbook;
import com.novik.workbooks.repositories.WBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private WBookRepo wbookrepo;

    @GetMapping("/")
    public String main(Map<String, Object> model) {

        return "main";
    }


    @GetMapping("/wbooks")
    public String wbooks(Model model){
        Iterable<Workbook> workbooks = wbookrepo.findAll();
        model.addAttribute("workbooks", workbooks);

        return "wbooks";
    }

    @GetMapping("/createWB")
    public String createWb(){
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