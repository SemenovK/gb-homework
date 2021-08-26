package gb.spring.homework3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@NoArgsConstructor
public class Product {
    private long id;
    private String title;
    private BigDecimal cost;
}
