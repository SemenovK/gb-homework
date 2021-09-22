package gb.spring.homework11.repository;

import gb.spring.homework11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {
    User findUserByUserName(String userName);
}
