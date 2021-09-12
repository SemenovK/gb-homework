package gb.spring.homework5.service;

import gb.spring.homework5.model.Customer;
import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.model.Product;
import gb.spring.homework5.model.dto.ProductDto;
import gb.spring.homework5.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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

    public List<ProductDto> getProductsList() {
        return productRepository.findAll().stream()
                .map(ProductDto::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    public Product getProduct(BigInteger id) {
        return productRepository.getById(id);
    }

    public void addProduct(ProductDto productDto) {
        productRepository.save(productDto.toProduct());
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

    public List<ProductDto> getProductsWithSpecification(Specification specification, Sort sort){
        List<Product> list = productRepository.findAll(specification, sort);
        //здесь почемуто не получалось череч Стимы отконвертить
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : list) {
            productDtoList.add(ProductDto.valueOf(product));
        }

        return productDtoList;
    }

}
