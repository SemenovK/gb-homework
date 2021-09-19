package gb.spring.homework5.integration;

import gb.spring.homework5.controller.CustomerController;
import gb.spring.homework5.controller.ProductController;
import gb.spring.homework5.model.Customer;
import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.Product;
import gb.spring.homework5.model.dto.CustomerDto;
import gb.spring.homework5.model.dto.ProductDto;
import gb.spring.homework5.repository.CustomerRepository;
import gb.spring.homework5.repository.OrderRepository;
import gb.spring.homework5.repository.ProductRepository;
import gb.spring.homework5.service.OrdersService;
import org.aspectj.weaver.ast.Or;
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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
    @LocalServerPort
    String port;
    private final String BASE_API_PATH = "/api/v1";

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;


    @Test
    public void getCustomers() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<CustomerDto[]> actual = testRestTemplate.getForEntity("http://localhost:" + port + BASE_API_PATH + "/customers", CustomerDto[].class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertThat(actual).isNotNull();
    }

    @Test
    public void addCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Test Testoviy");

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<CustomerDto> actual = testRestTemplate.postForEntity("http://localhost:" + port + BASE_API_PATH + "/customers", customerDto, CustomerDto.class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.ACCEPTED));
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getBody().getId()).isNotNull();
        customerRepository.deleteById(actual.getBody().getId());

    }

    @Test
    public void getCustomerByID() {

        Customer customer = new Customer();
        customer.setName("Ivan Petrov");
        customerRepository.save(customer);
        BigInteger customerId = customer.getCustomerId();
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<CustomerDto> actual = testRestTemplate.getForEntity("http://localhost:" + port + BASE_API_PATH + "/customers/" + customerId, CustomerDto.class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertThat(actual).isNotNull();
        customerRepository.delete(customer);

    }


    @Test
    public void deleteCustomerByID() {
        Customer customer = new Customer();
        customer.setName("Ivan Petrov");
        customerRepository.save(customer);
        BigInteger customerId = customer.getCustomerId();
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.delete("http://localhost:" + port + BASE_API_PATH + "/customers/" + customerId);
        Assertions.assertThat(customerRepository.existsById(customerId)).isFalse();

    }

    @Test
    public void getCustomerOrderHistory() {
        Customer customer = new Customer();
        customer.setName("Ivan Petrov");
        customerRepository.save(customer);
        List<Order> orderList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            Order order = new Order();
            order.setDate(new Date());
            order.setCustomer(customer);
            orderList.add(order);
            orderRepository.save(order);
        }

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<CustomerDto[]> actual = testRestTemplate.getForEntity("http://localhost:" + port + BASE_API_PATH + "/customers/history/" + customer.getCustomerId(), CustomerDto[].class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertThat(actual.getBody().length == 3).isTrue();

        for (Order order : orderList) {
            orderRepository.delete(order);
        }
        customerRepository.delete(customer);

    }
}
