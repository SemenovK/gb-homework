package gb.spring.homework5.service;

import gb.spring.homework5.model.Customer;
import gb.spring.homework5.repository.CustomerRepository;
import gb.spring.homework5.repository.OrdersRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void addCustomer(Customer customer){
        customerRepository.addCustomer(customer);
    }

    public void deleteCustomer(BigInteger id){
        customerRepository.deleteCustomer(id);
    }

    public List<Customer> getCustomers(){
        return customerRepository.getCustomers();
    }

    public Customer getCustomer(BigInteger id) {
        return customerRepository.getCustomer(id);
    }


}
