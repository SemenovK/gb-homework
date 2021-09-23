package gb.spring.homework5.controller.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductFilter {
    private String title;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private HashMap<String, Boolean> sort;

}
