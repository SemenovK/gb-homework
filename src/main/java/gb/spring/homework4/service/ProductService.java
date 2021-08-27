package gb.spring.homework4.service;

import gb.spring.homework4.model.Product;
import gb.spring.homework4.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductsList() {
        return productRepository.getProductsList();
    }

    public Product getProduct(long id) {
        return productRepository.getProduct(id).orElseThrow();
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void replaceProduct(Product product) {
        productRepository.replaceProduct(product);
    }

}
