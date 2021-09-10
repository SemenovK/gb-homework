package gb.spring.homework5.controller;

import gb.spring.homework5.model.Product;
import gb.spring.homework5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRESTController {

    private final ProductService productService;

    @PostMapping("add")
    public String saveProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
        return "ADDED";
    }
    @PostMapping("update")
    public String updateProduct (@RequestBody @Valid Product product) {
        productService.replaceProduct(product);
        return "UPDATED";
    }
    @PostMapping("delete")
    public String deleteProduct (@RequestParam BigInteger id) {
        System.out.println("DELETE");
        productService.deleteProduct(id);
        return "DELETED";
    }



}
