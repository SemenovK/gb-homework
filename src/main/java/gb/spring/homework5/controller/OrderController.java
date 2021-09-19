package gb.spring.homework5.controller;

import gb.spring.homework5.model.*;
import gb.spring.homework5.model.dto.OrderDetailDto;
import gb.spring.homework5.model.dto.OrderDto;
import gb.spring.homework5.model.dto.ProductDto;
import gb.spring.homework5.service.CustomerService;
import gb.spring.homework5.service.OrdersService;
import gb.spring.homework5.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Log4j2
@RestController
@Api("Заказы")
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;
    private final ProductService productService;
    private final CustomerService customerService;


    @GetMapping
    @ApiOperation("Получение списка всех заказов")
    public List<OrderDto> getOrders() {
        return ordersService.getOrders();

    }

    @PostMapping("{customerId}")
    @ApiOperation("Добавление заказа")
    public OrderDto addOrder(@PathVariable BigInteger customerId) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerDto(customerService.getCustomer(customerId));
        orderDto.setDate(new Date());
        return ordersService.addOrder(orderDto);

    }

    @DeleteMapping("{orderId}")
    @ApiOperation("Удаление заказа с добавленными в него товарами")
    public void deleteOrder(@PathVariable BigInteger orderId) {
        ordersService.deleteOrder(ordersService.getOrder(orderId));

    }
}
