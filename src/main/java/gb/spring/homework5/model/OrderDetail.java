package gb.spring.homework5.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "orders_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail implements Serializable {

    @EmbeddedId
    OrderDetailID orderDetailID;

    @Column(name = "price_on_order_date")
    private BigDecimal price_on_order_date;


    @Column(name = "quantity")
    private int quantity;


    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailID=" + orderDetailID +
                ", price_on_order_date=" + price_on_order_date +
                ", quantity=" + quantity +
                '}';
    }
}
