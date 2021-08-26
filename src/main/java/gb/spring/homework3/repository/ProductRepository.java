package gb.spring.homework3.repository;

import gb.spring.homework3.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class ProductRepository {

    private List<Product> productDB;
    private AtomicLong idCounter;


    public ProductRepository() {
        productDB = new CopyOnWriteArrayList<>();
        productDB.add(new Product(1, "Test", new BigDecimal(100)));
        productDB.add(new Product(2, "Test2", new BigDecimal(101)));

        idCounter = new AtomicLong(productDB.size());
    }

    public void addProduct(Product product) {
        long id = idCounter.incrementAndGet();
        product.setId(id);
        productDB.add(product);
    }

    public List<Product> getProductsList() {
        return Collections.unmodifiableList(productDB);
    }

    public Optional<Product> getProduct(long id) {
        return productDB.stream().filter(product -> {
            return product.getId() == id;
        }).findFirst();
    }

    public void deleteProduct(long id) {
        productDB.remove(id);
    }

    public void replaceProduct(Product product) {
        if (productDB.isEmpty() || !productDB.contains(product)) {
            return;
        }

        productDB.forEach(o -> {
            if (o.getId() == product.getId()) {
                productDB.remove(o);
            }
        });
        productDB.add(product);

    }

}
