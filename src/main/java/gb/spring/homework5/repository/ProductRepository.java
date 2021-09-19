package gb.spring.homework5.repository;

import gb.spring.homework5.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.math.BigInteger;


public interface ProductRepository extends JpaRepository<Product, BigInteger>, JpaSpecificationExecutor<Product> {

    // Просто оставлю это для памяти. Пока дошел до спецификаций прошел мимо этого
//    List<Product> findProductByCostGreaterThan(BigDecimal priceFrom, Sort sort);
//    List<Product> findProductByCostLessThan(BigDecimal priceTo, Sort sort);
//    List<Product> findProductByCostBetween(BigDecimal priceFrom, BigDecimal priceTo, Sort sort);
//
//    List<Product> findProductByCostGreaterThanAndTitleContains(BigDecimal priceFrom, String title, Sort sort);
//    List<Product> findProductByCostLessThanAndTitleContains(BigDecimal priceTo, String title, Sort sort);
//    List<Product> findProductByCostBetweenAndTitleContains(BigDecimal priceFrom, BigDecimal priceTo, String title, Sort sort);

}
