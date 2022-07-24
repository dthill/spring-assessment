package pgfsd.sportyshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pgfsd.sportyshoes.dto.UserRegistrationDto;
import pgfsd.sportyshoes.entities.User;
import pgfsd.sportyshoes.services.UserService;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("registerUser", new UserRegistrationDto());
        return "register";
    }

    @PostMapping(value = "/register")
    public String postRegister(@Validated @ModelAttribute UserRegistrationDto userRegistrationDto, RedirectAttributes model) {
        User user = userService.registerUser(userRegistrationDto);
        if (user == null) {
            model.addFlashAttribute("error", "username already in use");
            return "redirect:register";
        }
        return "redirect:";
    }


    @ExceptionHandler(Exception.class)
    public String handleRegisterExceptions(Exception exception, RedirectAttributes model) {
        model.addFlashAttribute("error", "username or password illegal format. Size must be between 3 and 50");
        return "redirect:register";
    }


}
