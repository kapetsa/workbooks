
        package com.novik.firstapp.controller;

        import com.novik.firstapp.repos.UserRepo;
        import com.novik.firstapp.repos.UsrRepo;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UsrRepo usrRepo;

    @GetMapping("/delete/{id}")
    public String UserDel(@PathVariable Long id) {
        userRepo.deleteById(id);
        usrRepo.deleteById(id);
        return "redirect:/";
    }
}