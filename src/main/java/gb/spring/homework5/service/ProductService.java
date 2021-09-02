package gb.spring.homework5.service;

import gb.spring.homework5.model.Product;
import gb.spring.homework5.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductsList() {
        return productRepository.getProductsList();
    }

    public List<Product> getProduct(BigInteger id) {
        return productRepository.getProduct(id);
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void replaceProduct(Product product) {
        productRepository.replaceProduct(product);
    }

    public void deleteProduct(BigInteger id) {
        productRepository.deleteProduct(id);
    }

}
