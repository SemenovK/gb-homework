package gb.spring.homework5.controller;

import gb.spring.homework5.controller.filters.ProductFilter;
import gb.spring.homework5.model.Product;
import gb.spring.homework5.model.dto.ProductDto;
import gb.spring.homework5.repository.specification.ProductSpecification;
import gb.spring.homework5.service.CustomerService;
import gb.spring.homework5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CustomerService customerService;

    @GetMapping("product")
    public List<ProductDto> showAllProducts() {
        return productService.getProductsList();

    }

    @PostMapping("productFilter")
    public List<ProductDto> showFilteredProducts(@RequestBody ProductFilter productFilter) {

        System.out.println(productFilter);
        List<String> sortFields = new ArrayList<>();
        if (productFilter.getSort() != null) {
            if (productFilter.getSort().containsKey("byID"))
                if (productFilter.getSort().get("byID") == true)
                    sortFields.add("productId");
            if (productFilter.getSort().containsKey("byName"))
                if (productFilter.getSort().get("byName") == true)
                    sortFields.add("title");
            if (productFilter.getSort().containsKey("byPrice"))
                if (productFilter.getSort().get("byPrice") == true)
                    sortFields.add("cost");
            if (productFilter.getSort().containsKey("byProducer"))
                if (productFilter.getSort().get("byProducer") == true)
                    sortFields.add("producer");

        }

        if(sortFields.isEmpty())
            sortFields.add("productId");

        JpaSort sort = JpaSort.unsafe(Sort.Direction.ASC, sortFields);

        return productService.getProductsWithSpecification(ProductSpecification.getByPriceAndTitle(productFilter.getPriceFrom(), productFilter.getPriceTo(), productFilter.getTitle()), sort);

    }

    @PostMapping("product")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody @Valid ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @DeleteMapping("product/{id}")
    public void deleteProduct(@PathVariable BigInteger id) {
        productService.deleteProduct(id);
    }

}
