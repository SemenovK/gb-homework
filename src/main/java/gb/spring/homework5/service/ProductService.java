package gb.spring.homework5.service;

import gb.spring.homework5.model.Customer;
import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.model.Product;
import gb.spring.homework5.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomerService customerService;
    private final OrdersService ordersService;

    public List<Product> getProductsList() {
        return productRepository.findAll();
    }

    public Product getProduct(BigInteger id) {
        return productRepository.getById(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void replaceProduct(Product product) {
        Product productInBase = productRepository.getById(product.getProductId());

        productInBase.setTitle(product.getTitle());
        productInBase.setCost(product.getCost());
        productInBase.setProducer(product.getProducer());

        productRepository.save(productInBase);
    }

    public void deleteProduct(BigInteger id) {
        productRepository.deleteById(id);
    }

    public Set<Product> getProductsListByCustomerId(BigInteger id) {
        Set<Product> productSet = new CopyOnWriteArraySet<>();
        List<Order> orderList = ordersService.getCustomerOrders(id);

        for (Order order : orderList) {
            for (OrderDetail orderDetail : order.getOrderDetailList()) {
                productSet.add(orderDetail.getOrderDetailID().getProduct());
            }
        }
        return productSet;
    }

    public Set<Product> getProductsListByCustomerName(String customerName) {
        Optional<Customer> customer = customerService.getCustomer(customerName);

        if (customer.isPresent()) {
            return getProductsListByCustomerId(customer.get().getCustomerId());
        }

        return null;
    }

    public List<Product> getProductsWithSpecification(Specification specification, Sort sort){
        return productRepository.findAll(specification,sort);
    }

    public List<Product> getProductsWithPriceBetween(BigDecimal priceFrom, BigDecimal priceTo, Sort sort) {

        if (priceFrom != null && priceTo != null) {
            return productRepository.findProductByCostBetween(priceFrom, priceTo, sort);
        } else if (priceFrom == null && priceTo != null) {
            return productRepository.findProductByCostLessThan(priceTo, sort);
        } else if (priceFrom != null && priceTo == null) {
            return productRepository.findProductByCostGreaterThan(priceFrom, sort);
        } else return getProductsList();

    }

    public Object getProductsWithPriceBetween(BigDecimal priceFrom, BigDecimal priceTo, String productTitle, JpaSort sort) {

        if (priceFrom != null && priceTo != null) {
            return productRepository.findProductByCostBetweenAndTitleContains(priceFrom, priceTo, productTitle, sort);
        } else if (priceFrom == null && priceTo != null) {
            return productRepository.findProductByCostLessThanAndTitleContains(priceTo, productTitle, sort);
        } else if (priceFrom != null && priceTo == null) {
            return productRepository.findProductByCostGreaterThanAndTitleContains(priceFrom, productTitle, sort);
        } else return getProductsList();

    }

}
