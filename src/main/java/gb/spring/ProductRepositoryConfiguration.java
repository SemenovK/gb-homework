package gb.spring;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.math.BigDecimal;

@Configuration
public class ProductRepositoryConfiguration {

    @Bean
    public ProductRepository productRepository() {
        ProductRepository pr = new ProductRepository();
        pr.putProduct(new Product(1, "Хлеб", new BigDecimal(100)));
        pr.putProduct(new Product(2, "Молоко", new BigDecimal(120)));
        pr.putProduct(new Product(3, "Яйца", new BigDecimal(84)));
        pr.putProduct(new Product(4, "Сыр", new BigDecimal(240)));
        pr.putProduct(new Product(5, "Пиво", new BigDecimal(250)));
        return pr;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Cart cart() {
        return new Cart();
    }
}
