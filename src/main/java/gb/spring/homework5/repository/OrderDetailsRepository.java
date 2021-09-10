package gb.spring.homework5.repository;

import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.model.OrderDetailID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, OrderDetailID> {
}
