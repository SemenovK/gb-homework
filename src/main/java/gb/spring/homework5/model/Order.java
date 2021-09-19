package gb.spring.homework5.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity(name="order")
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger order_id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }
}
