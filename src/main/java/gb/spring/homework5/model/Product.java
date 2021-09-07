package gb.spring.homework5.model;

import gb.spring.homework5.annotations.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Product.getAll", query = "select p from Product p"),
        @NamedQuery(name = "Product.getById", query = "select p from Product p where p.productId = :id")
})
public class Product {
    @Id
    @Column(name = "product_id")
    @SequenceGenerator(name = "ProductIdGenerator", sequenceName = "\"Homework\".\"products_id_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductIdGenerator")
    private BigInteger productId;

    @Column(name = "title")
    @NotNull
    @NotEmpty
    @NotBlank
    private String title;

    @DecimalMin("0.0")
    @Column(name = "cost", precision = 19, scale = 6)
    private BigDecimal cost;

    @Column(name = "producer")
    @NotNull
    @NotEmpty
    @NotBlank
    @Company
    private String producer;


}
