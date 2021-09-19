package gb.spring.homework5.unit;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import gb.spring.homework5.common.ProductDeserialiser;
import gb.spring.homework5.model.Product;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@SpringBootTest
@DisplayName("Product deserializer test")
public class ProductDeserialiserTest {

    @Autowired
    ProductDeserialiser productDeserialiser;

    private ObjectMapper mapper;
    private DeserializationContext ctxt;

    @BeforeEach
    private void prepareToTests() {
        mapper = new ObjectMapper();
        ctxt = mapper.getDeserializationContext();
    }


    @Test
    @DisplayName("Test to deserialize Product from simple JSON")
    void deserializeFromCorrectJSONLikeExistsClass() throws IOException {

        BigInteger productId = BigInteger.valueOf(99);
        BigDecimal productCost = BigDecimal.valueOf(99.1);


        JsonParser parser = mapper.getFactory().createParser("{\"id\": " + productId + ",\n" +
                "        \"title\": \"Test\",\n" +
                "        \"cost\": " + productCost + ",\n" +
                "        \"producer\": \"Company3\"}");

        Product product = productDeserialiser.deserialize(parser, ctxt);
        Assertions.assertEquals(product.getProductId(), productId);
        Assertions.assertEquals(product.getTitle(), "Test");
        Assertions.assertEquals(product.getCost(), productCost);
        Assertions.assertEquals(product.getProducer(), "Company3");
    }


    @Test
    @DisplayName("Test to deserialize Product from JSON where fields Title and Producer ar coded in one string and separate with '-'")
    void deserializeFromCorrectJSONProductWithCode() throws IOException {
        BigInteger productId = BigInteger.valueOf(99);
        BigDecimal productCost = BigDecimal.valueOf(99.1);
        JsonParser parser = mapper.getFactory().createParser("{\"id\": " + productId + ",\n" +
                "        \"cost\": " + productCost + ",\n" +
                "        \"code\": \"Test - Company3\"}");

        Product product = productDeserialiser.deserialize(parser, ctxt);
        Assertions.assertEquals(product.getProductId(), productId);
        Assertions.assertEquals(product.getTitle(), "Test");
        Assertions.assertEquals(product.getCost(), productCost);
        Assertions.assertEquals(product.getProducer(), "Company3");
    }

}
