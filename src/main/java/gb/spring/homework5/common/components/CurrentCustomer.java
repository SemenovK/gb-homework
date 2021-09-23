package gb.spring.homework5.common.components;

import gb.spring.homework5.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentCustomer {
    private Customer customer;
}
