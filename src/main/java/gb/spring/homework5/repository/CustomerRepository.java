package gb.spring.homework5.repository;

import gb.spring.homework5.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CustomerRepository extends JpaRepository<Customer, BigInteger> {
}
