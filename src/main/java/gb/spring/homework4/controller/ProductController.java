package gb.spring.homework4.controller;

import gb.spring.homework4.model.Product;
import gb.spring.homework4.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String findProduct(@PathVariable Long id, Model model) {
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


}
