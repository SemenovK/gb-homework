package gb.spring.homework3.controller;

import gb.spring.homework3.model.Product;
import gb.spring.homework3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String showMainPage(Model model) {
        return showAllProducts(model);
    }

    @GetMapping("products")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.getProductsList());

        return "products";
    }

    @GetMapping("add")
    public String addProduct(Model model) {
        model.addAttribute("mode", "Добавить продукт");
        model.addAttribute("product");
        return "productEditForm";
    }

    @GetMapping("{id}")
    public String findProduct(@PathVariable Long id, Model model) {
        model.addAttribute("products", productService.getProduct(id));
        return "products";
    }

    @PostMapping("product_add")
    public String saveProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }


}
