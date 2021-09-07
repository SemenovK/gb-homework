package gb.spring.homework5.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

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

    @OneToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    //Не до конца понимаю почему он выдает что неверно смапирована product_id и требует insertable = false, updatable = false
    private Product product;


    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailID=" + orderDetailID +
                ", price_on_order_date=" + price_on_order_date +
                ", quantity=" + quantity +
                '}';
    }
}
