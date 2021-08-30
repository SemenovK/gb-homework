package gb.spring.homework5.controller;

import gb.spring.homework5.model.Product;
import gb.spring.homework5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.getProductsList());
        return "products";
    }

    @GetMapping("{id}")
    public String findProduct(@PathVariable BigInteger id, Model model) {
        model.addAttribute("products", productService.getProduct(id));
        return "products";
    }

    @GetMapping("product_add")
    public String addProduct(Model model) {
        model.addAttribute("mode", "Добавить продукт");
        model.addAttribute("product");
        return "productEditForm";
    }
    @PostMapping("product_add")
    public String saveProduct(@ModelAttribute @Valid Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("product_edit/{id}")
    public String editProduct(@PathVariable BigInteger id,Model model) {
        model.addAttribute("mode", "Редактировать продукт");
        model.addAttribute("product",(Product)productService.getProduct(id).stream().findFirst().get());
        return "productEditForm";
    }
    @PostMapping("product_edit")
    public String editProduct(@ModelAttribute @Valid Product product) {
        productService.replaceProduct(product);
        return "redirect:/";
    }

    @GetMapping("product_delete/{id}")
    public String deleteProduct(@PathVariable BigInteger id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
