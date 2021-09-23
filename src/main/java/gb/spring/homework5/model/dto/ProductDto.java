package gb.spring.homework5.model.dto;

import gb.spring.homework5.annotations.Company;
import gb.spring.homework5.model.Product;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "Продукт", description = "DTO Продукт")
public class ProductDto {

    private BigInteger id;
    private String title;
    private BigDecimal price;
    private String producer;

    public static ProductDto valueOf(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getProductId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getCost());
        productDto.setProducer(product.getProducer());

        return productDto;
    }

    public Product toProduct(){
        Product product = new Product();
        product.setProductId(this.id);
        product.setTitle(this.title);
        product.setCost(this.price);
        product.setProducer(this.producer);
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id.equals(that.id) && title.equals(that.title) && price.equals(that.price) && producer.equals(that.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, producer);
    }
}
