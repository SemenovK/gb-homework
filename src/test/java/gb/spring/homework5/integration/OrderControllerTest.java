package gb.spring.homework5.integration;

import gb.spring.homework5.controller.OrderController;
import gb.spring.homework5.model.*;
import gb.spring.homework5.model.dto.CustomerDto;
import gb.spring.homework5.model.dto.OrderDto;
import gb.spring.homework5.model.dto.ProductDto;
import gb.spring.homework5.repository.CustomerRepository;
import gb.spring.homework5.repository.OrderDetailsRepository;
import gb.spring.homework5.repository.OrderRepository;
import gb.spring.homework5.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
    private final String BASE_API_PATH = "/api/v1";
    @LocalServerPort
    String port;


    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrderController orderController;

    @Test
    public void getOrders() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<OrderDto[]> actual = testRestTemplate.getForEntity("http://localhost:" + port + BASE_API_PATH + "/order", OrderDto[].class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertThat(actual).isNotNull();
    }

    @Test
    public void addOrderToCustomer() {
        Customer customer = new Customer();
        customer.setName("Ivan Petrov");
        customerRepository.save(customer);
        BigInteger customerId = customer.getCustomerId();

        OrderDto order = new OrderDto();
        order.setCustomerDto(CustomerDto.valueOf(customer));
        order.setDate(new Date());

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<OrderDto> actual = testRestTemplate.postForEntity("http://localhost:" + port + BASE_API_PATH + "/order/" + customerId, order, OrderDto.class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertThat(actual).isNotNull();

        orderRepository.deleteById(actual.getBody().getOrder_id());
        customerRepository.delete(customer);
    }

    @Test
    public void deleteOrderWithOrderDetails() {
        Customer customer = new Customer();
        customer.setName("Ivan Petrov");
        customerRepository.save(customer);
        BigInteger customerId = customer.getCustomerId();

        Product product = new Product();
        product.setTitle("TestProduct");
        product.setCost(BigDecimal.valueOf(1.2));
        product.setProducer("Company1");
        productRepository.save(product);

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(new Date());
        orderRepository.save(order);
        BigInteger orderId = order.getOrder_id();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailID(new OrderDetailID());
        orderDetail.getOrderDetailID().setOrder(order);
        orderDetail.getOrderDetailID().setProduct(product);
        orderDetail.setQuantity(1);
        orderDetail.setPrice_on_order_date(product.getCost());
        orderDetailsRepository.save(orderDetail);
        Assertions.assertThat(orderRepository.existsById(orderId)).isTrue();
        Assertions.assertThat(orderDetailsRepository.existsById(orderDetail.getOrderDetailID())).isTrue();
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.delete("http://localhost:"+port + BASE_API_PATH + "/order/" + orderId);

        Assertions.assertThat(orderRepository.existsById(orderId)).isFalse();
        Assertions.assertThat(orderDetailsRepository.existsById(orderDetail.getOrderDetailID())).isFalse();




    }


}
