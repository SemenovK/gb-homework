package gb.spring.homework5.controller;

import gb.spring.homework5.model.Customer;
import gb.spring.homework5.service.CustomerService;
import gb.spring.homework5.service.OrdersService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final OrdersService ordersService;

    @GetMapping
    public String getAllCustomers(Model model){
        model.addAttribute("customer",customerService.getCustomers());
        return "customers";
    }
    @GetMapping("{id}")
    public String getAllCustomers(@PathVariable BigInteger id, Model model){
        model.addAttribute("customer",customerService.getCustomer(id));
        return "customers";
    }

    @PostMapping
    public String addCustomer(@ModelAttribute @Valid Customer customer){
        customerService.addCustomer(customer);
        return "redirect:/customers";
    }
    @GetMapping("delete/{id}")
    public String deleteCustomer(@PathVariable BigInteger id, Model model){
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @GetMapping("history/{customerId}")
    public String showOrders(@PathVariable BigInteger customerId, Model model){
        model.addAttribute("customer",customerService.getCustomer(customerId));
        model.addAttribute("order",ordersService.getCustomerOrders(customerId));
        return "orders";
    }

}
