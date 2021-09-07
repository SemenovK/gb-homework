package gb.spring.homework5.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
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

    //Если я правильно понимаю то EAGER мы используем чтоб сразу как создаем обект заказа начитать внего все детали
    //А Lazy будет пытаться подтягивать их по мере обращения
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> orderDetailList;

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }
}
