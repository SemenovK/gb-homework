package gb.spring.homework5.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders_details")

public class OrderDetailID implements Serializable {

    @Column(name = "product_id")
    private BigInteger product_id;
    @Column(name = "order_id")
    private BigInteger order_id;

    @Override
    public String toString() {
        return "OrderDetailID{" +
                "product_id=" + product_id +
                ", order_id=" + order_id +
                '}';
    }
}
