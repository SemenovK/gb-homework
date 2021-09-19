package gb.spring.homework5.controller;

import gb.spring.homework5.model.Customer;
import gb.spring.homework5.model.dto.CustomerDto;
import gb.spring.homework5.model.dto.OrderDto;
import gb.spring.homework5.service.CustomerService;
import gb.spring.homework5.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;


@Api("Покупатели")
@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final OrdersService ordersService;

    @GetMapping
    @ApiOperation("Получение списка всех покупателей")
    public List<CustomerDto> getAllCustomers(){
        return customerService.getCustomers();

    }
    @GetMapping("{id}")
    @ApiOperation("Получение покупателя по ID")
    public CustomerDto getCustomer(@PathVariable BigInteger id){
        return customerService.getCustomer(id);
    }

    @PostMapping
    @ApiOperation("Добавление покупателя")
    public CustomerDto addCustomer(@RequestBody @Valid CustomerDto customerDto){
        return customerService.addCustomer(customerDto);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление покупателя")
    public void deleteCustomer(@PathVariable BigInteger id){
        customerService.deleteCustomer(id);
    }

    @GetMapping("history/{customerId}")
    @ApiOperation("История заказов покупателя")
    public List<OrderDto> showOrders(@PathVariable BigInteger customerId){
        List<OrderDto> orderDtoList = ordersService.getCustomerOrders(customerId);

        return orderDtoList;
    }

}
