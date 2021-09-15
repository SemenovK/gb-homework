package gb.spring.homework5.service;

import gb.spring.homework5.model.Company;
import gb.spring.homework5.model.Customer;
import gb.spring.homework5.model.dto.CustomerDto;
import gb.spring.homework5.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDto addCustomer(Customer customer) {
        customerRepository.save(customer);
        return CustomerDto.valueOf(customer);
    }

    public void deleteCustomer(BigInteger id) {
        customerRepository.deleteById(id);
    }

    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream().map(CustomerDto::valueOf).collect(Collectors.toUnmodifiableList());
    }

    public CustomerDto getCustomer(BigInteger id) {
        return CustomerDto.valueOf(customerRepository.getById(id));
    }

    public Optional<CustomerDto> getCustomer(String nameExpr) {
        Customer customer = new Customer();

        customer.setName(nameExpr);
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnorePaths("customerId")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Customer> example = Example.of(customer, modelMatcher);

        return customerRepository.findOne(example).map(CustomerDto::valueOf);

    }


}
