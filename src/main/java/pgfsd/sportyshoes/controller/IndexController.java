package pgfsd.sportyshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pgfsd.sportyshoes.dto.ProductId;
import pgfsd.sportyshoes.entities.User;
import pgfsd.sportyshoes.entities.UserRole;
import pgfsd.sportyshoes.services.ProductService;
import pgfsd.sportyshoes.services.PurchaseService;

@Controller
public class IndexController {

    @Autowired
    ProductService productService;
    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/")
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            model.addAttribute("user", user.getUsername());
            boolean isAdmin = false;
            for (GrantedAuthority authority : user.getAuthorities()) {
                if (authority.getAuthority().equals(UserRole.Role.ADMIN.name())
                ) {
                    isAdmin = true;
                    break;
                }
            }
            model.addAttribute("admin", isAdmin);
        }
        model.addAttribute("productId", new ProductId());
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @PostMapping("/purchase")
    public String index(@Validated @ModelAttribute ProductId productId, RedirectAttributes redirectAttributes) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        redirectAttributes.addFlashAttribute("purchaseSuccess", purchaseService.addToCart(productId, user));
        return "redirect:";
    }
}
