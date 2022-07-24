package pgfsd.sportyshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pgfsd.sportyshoes.dto.CompleteCartDto;
import pgfsd.sportyshoes.entities.User;
import pgfsd.sportyshoes.services.PurchaseService;

@Controller
public class CartController {
    @Autowired
    PurchaseService purchaseService;


    @GetMapping("/cart")
    public String getCart(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        model.addAttribute("cart", purchaseService.getCurrentCart(user));
        model.addAttribute("completeCart", new CompleteCartDto());
        return "cart";
    }

    @PostMapping("/buy-cart")
    public String buyCart(@Validated @ModelAttribute CompleteCartDto cart, RedirectAttributes redirectAttributes) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        redirectAttributes.addFlashAttribute("purchaseSuccess", purchaseService.buyCart(cart, user));
        return "redirect:cart";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, RedirectAttributes model) {
        model.addFlashAttribute("error", "An Error occurred please check your provided input and try again");
        return "redirect:cart";
    }
}
