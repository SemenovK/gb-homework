package gb.spring.homework4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import gb.spring.homework4.annotations.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})

public class Product {

    private long id;

    @NotEmpty
    @NotBlank
    @NotNull
    private String title;

    @Min(0)
    private BigDecimal cost;

    @NotEmpty
    @NotBlank
    @NotNull
    @Company
    private String producer;


}
