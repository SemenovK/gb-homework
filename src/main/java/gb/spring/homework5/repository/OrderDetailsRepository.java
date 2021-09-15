package gb.spring.homework5.repository;

import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.model.OrderDetailID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, OrderDetailID> {
    List<OrderDetail> findOrderDetailsByOrderDetailID_Order(Order order);
    void deleteOrderDetailsByOrderDetailID_Order(Order order);
}
