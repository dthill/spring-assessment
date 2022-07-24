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
import pgfsd.sportyshoes.entities.Product;
import pgfsd.sportyshoes.entities.ProductCategory;
import pgfsd.sportyshoes.services.ProductService;

@Controller
public class AdminController {
    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("category", new ProductCategory());
        model.addAttribute("categories", productService.getAllProductCategories());
        return "admin";
    }

    @PostMapping("/admin-product")
    public String postProduct(@Validated @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("productSuccess", productService.updateProduct(product));
        return "redirect:admin";
    }

    @PostMapping("/admin-category")
    public String postCategory(@Validated @ModelAttribute ProductCategory productCategory, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("categorySuccess", productService.updateProductCategory(productCategory));
        return "redirect:admin";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, RedirectAttributes model) {
        model.addFlashAttribute("error", "An Error occurred please try again");
        return "redirect:admin";
    }
}
