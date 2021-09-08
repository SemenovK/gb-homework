package gb.spring.homework5.repository.specification;

import gb.spring.homework5.model.Product;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ProductSpecification {

    public static Specification<Product> getByTitle(String title){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%"+title+"%");
    }

    public static Specification<Product> getByPriceAndTitle(BigDecimal priceFrom, BigDecimal priceTo, String title){
        //Знаю что можно в лямбду. Но мне пока так понятней.
        //Это гениальная вещь
        Specification<Product> productSpecification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               List<Predicate> conditions = new ArrayList<>();
                if(priceFrom!=null){
                    conditions.add(criteriaBuilder.greaterThan(root.get("cost"), priceFrom));
                }
                if(priceTo!=null){
                    conditions.add(criteriaBuilder.lessThan(root.get("cost"), priceTo));
                }
                if(title!=null && !title.trim().equals("")){
                    conditions.add(criteriaBuilder.like(root.get("title"), "%"+title+"%"));
                }

                //не могу разобраться  почему 0 и почему []
                return criteriaQuery.where(conditions.toArray(new Predicate[0])).getRestriction();
            }
        };

        return productSpecification;
    }

}
