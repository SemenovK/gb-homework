package gb.spring.homework5.controller;

import gb.spring.homework5.model.Product;
import gb.spring.homework5.repository.specification.ProductSpecification;
import gb.spring.homework5.service.CustomerService;
import gb.spring.homework5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CustomerService customerService;

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.getProductsList());
        return "products";
    }

    @PostMapping("products")
    public String showAllFilteredAndSortedProducts(@RequestParam(required = false) BigDecimal priceFrom,
                                                   @RequestParam(required = false) BigDecimal priceTo,
                                                   @RequestParam(required = false) String productTitle,
                                                   @RequestParam(required = false) String sortById,
                                                   @RequestParam(required = false) String sortByTitle,
                                                   @RequestParam(required = false) String sortByCost,
                                                   @RequestParam(required = false) String sortByProducer,
                                                   Model model) {

        List<String> sortFields = new ArrayList<>();
        if (sortById != null)
            sortFields.add("productId");
        if (sortByTitle != null)
            sortFields.add("title");
        if (sortByCost != null)
            sortFields.add("cost");
        if (sortByProducer != null)
            sortFields.add("producer");

        if (sortFields.isEmpty()) {
            sortFields.add("productId");
        }
        System.out.println(sortFields);
        JpaSort sort = JpaSort.unsafe(Sort.Direction.ASC, sortFields);

        model.addAttribute("products", productService.getProductsWithSpecification(ProductSpecification.getByPriceAndTitle(priceFrom, priceTo, productTitle), sort));

        //Заметка для памяти. Если вдруг готов буду идти сложным и походу неправильным путем
        //
//       if(productTitle==null||productTitle.trim().equals("")){
//            model.addAttribute("products", productService.getProductsWithPriceBetween(priceFrom, priceTo, sort));
//        } else {
//            //model.addAttribute("products", productService.getProductsWithPriceBetween(priceFrom, priceTo, productTitle, sort));
//            model.addAttribute("products", productService.getProductsWithSpecification(ProductSpecification.getByTitle(productTitle), sort));
//
//        }


        model.addAttribute("priceFrom", priceFrom);
        model.addAttribute("priceTo", priceTo);
        model.addAttribute("productTitle", productTitle);
        model.addAttribute("sortById", sortById == null ? null : "on");
        model.addAttribute("sortByTitle", sortByTitle == null ? null : "on");
        model.addAttribute("sortByCost", sortByCost == null ? null : "on");
        model.addAttribute("sortByProducer", sortByProducer == null ? null : "on");


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
        return "productAddForm";
    }

    @PostMapping("product_add")
    public String saveProduct(@ModelAttribute @Valid Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("product_edit/{id}")
    public String editProduct(@PathVariable BigInteger id, Model model) {
        model.addAttribute("mode", "Редактировать продукт");
        model.addAttribute("product", productService.getProduct(id));
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

    @GetMapping("product_byCustomerId/{id}")
    public String showProductsByCustomerId(@PathVariable BigInteger id, Model model) {
        model.addAttribute("customer", customerService.getCustomer(id));
        model.addAttribute("products", productService.getProductsListByCustomerId(id));

        return "products";
    }

    @GetMapping("product_byCustomerName")
    public String showProductsByCustomerId(@RequestParam(name = "name") String customerName,
                                           Model model) {
        model.addAttribute("customer", customerService.getCustomer(customerName).orElse(null));
        model.addAttribute("products", productService.getProductsListByCustomerName(customerName));
        return "products";

    }
}
