package gb.spring.homework5.integration;

import gb.spring.homework5.controller.ProductController;
import gb.spring.homework5.model.Product;
import gb.spring.homework5.model.dto.ProductDto;
import gb.spring.homework5.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
public class ProductControllerTest {
    private final String BASE_API_PATH = "/api/v1";
    @LocalServerPort
    String port;

    @Autowired
    ProductController productController;
    @Autowired
    ProductRepository productRepository;


    @Test
    public void getProducts() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<ProductDto[]> actual = testRestTemplate.getForEntity("http://localhost:" + port + BASE_API_PATH + "/products", ProductDto[].class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertThat(actual).isNotNull();
    }

    @Test
    public void addProduct() {
        ProductDto product = new ProductDto();
        product.setTitle("Test");
        product.setPrice(BigDecimal.valueOf(100));
        product.setProducer("Company1");

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<ProductDto> actual = testRestTemplate.postForEntity("http://localhost:" + port + BASE_API_PATH + "/products", product, ProductDto.class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getBody().getId()).isNotNull();
        productRepository.deleteById(actual.getBody().getId());

    }

    @Test
    public void getProductById() {

        Product p = new Product();
        p.setTitle("Test");
        p.setCost(BigDecimal.valueOf(1));
        p.setProducer("Company1");
        productRepository.save(p);
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<ProductDto> actual = testRestTemplate.getForEntity("http://localhost:" + port + BASE_API_PATH + "/products/" + p.getProductId(), ProductDto.class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertThat(actual).isNotNull();
        productRepository.delete(p);

    }

    @Test
    public void deleteProductById() {
        Product p = new Product();
        p.setTitle("Test");
        p.setCost(BigDecimal.valueOf(1));
        p.setProducer("Company1");
        productRepository.save(p);
        BigInteger productId = p.getProductId();
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.delete("http://localhost:"+port + BASE_API_PATH + "/products/" + productId);
        Assertions.assertThat(productRepository.existsById(productId)).isFalse();


    }
}
