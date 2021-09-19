package gb.spring.homework5.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders_details")
public class OrderDetailID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;


    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @Override
    public String toString() {
        return "OrderDetailID{" +
                 product.toString() +
                ","+ order.toString()+
                '}';
    }
}
