package gb.spring.homework5.repository;

import gb.spring.homework5.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrderRepository extends JpaRepository<Order, BigInteger> {
}
