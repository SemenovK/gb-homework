package gb.spring.homework5.model.dto;

import gb.spring.homework5.model.Customer;
import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private BigInteger id;
    private String name;

    public static CustomerDto valueOf(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getCustomerId());
        customerDto.setName(customer.getName());

        return customerDto;
    }

    public Customer toCustomer(){
        Customer customer = new Customer();
        customer.setCustomerId(this.id);
        customer.setName(this.getName());

        return customer;
    }

}
