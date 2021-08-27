package gb.spring.homework4.controller;

import gb.spring.homework4.model.Product;
import gb.spring.homework4.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRESTController {

    private final ProductService productService;

    @PostMapping("add")
    public String saveProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
        return "OK";
    }


}
