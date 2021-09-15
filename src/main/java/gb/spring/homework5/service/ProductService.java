package gb.spring.homework5.service;

import gb.spring.homework5.model.Customer;
import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.model.Product;
import gb.spring.homework5.model.dto.CustomerDto;
import gb.spring.homework5.model.dto.OrderDetailDto;
import gb.spring.homework5.model.dto.OrderDto;
import gb.spring.homework5.model.dto.ProductDto;
import gb.spring.homework5.repository.OrderDetailsRepository;
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

    public ProductDto getProduct(BigInteger id) {
        return ProductDto.valueOf(productRepository.getById(id));
    }

    public ProductDto addProduct(ProductDto productDto) {
        productRepository.save(productDto.toProduct());
        return productDto;
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

    public Set<ProductDto> getProductsListByCustomerId(BigInteger id) {
        Set<ProductDto> productSet = new CopyOnWriteArraySet<>();
        List<OrderDto> orderList = ordersService.getCustomerOrders(id);

        for (OrderDto orderDto : orderList) {
            List<OrderDetailDto> orderDetailDto = ordersService.getOrderDetailByOrder(orderDto);
            for (OrderDetailDto detailDto : orderDetailDto) {
                productSet.add(detailDto.getProductDto());

            }
        }
        return productSet;
    }

    public Set<ProductDto> getProductsListByCustomerName(String customerName) {
        Optional<CustomerDto> customer = customerService.getCustomer(customerName);

        if (customer.isPresent()) {
            return getProductsListByCustomerId(customer.get().getId());
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
