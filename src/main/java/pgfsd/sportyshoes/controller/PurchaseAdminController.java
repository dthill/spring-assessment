package pgfsd.sportyshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pgfsd.sportyshoes.dto.PurchaseAdminDto;
import pgfsd.sportyshoes.dto.PurchaseFilterDto;
import pgfsd.sportyshoes.entities.ProductCategory;
import pgfsd.sportyshoes.services.PurchaseService;

import java.text.ParseException;

@Controller
public class PurchaseAdminController {
    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/purchase-admin")
    public String getPurchaseAdmin(@Validated @ModelAttribute  PurchaseFilterDto purchaseFilterDto, Model model) throws ParseException {
        model.addAttribute("purchaseAdmin", purchaseService.getPurchaseAdmin(purchaseFilterDto));
        model.addAttribute("purchaseFilter", new PurchaseFilterDto());
        return "purchase-admin";
    }

    /*
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, RedirectAttributes model) {
        model.addFlashAttribute("error", "An Error occurred please try again");
        return "redirect:admin";
    }

     */
}
