package pgfsd.sportyshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pgfsd.sportyshoes.services.UserService;

@Controller
public class UserAdminController {
    @Autowired
    UserService userService;

    @GetMapping("/user-admin")
    public String getUserAdmin(@RequestParam(name = "name", defaultValue = "", required = false) String name, Model model) {
        model.addAttribute("users", userService.getAllUsers(name));
        return "user-admin";
    }


    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, RedirectAttributes model) {
        model.addFlashAttribute("error", "An Error occurred please check your provided input and try again");
        return "redirect:admin";
    }
}
