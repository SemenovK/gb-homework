package gb.spring.homework5.controller;

import gb.spring.homework5.model.dto.OrderDetailDto;
import gb.spring.homework5.model.dto.OrderDto;
import gb.spring.homework5.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Log4j2
@RestController
@Api("Детали заказа")
@RequestMapping("api/v1/order/{orderId}/details")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrdersService ordersService;

    @GetMapping
    @ApiOperation("Получение деталей заказа")
    public List<OrderDetailDto> getOrderDetails(@PathVariable BigInteger orderId) {
        return ordersService.getOrderDetailByOrder(ordersService.getOrder(orderId));
    }

    @PostMapping
    @ApiOperation("Добавление записи детализации заказа")
    public OrderDto addOrderDetailToOrder(@PathVariable BigInteger orderId,
                                          @RequestBody OrderDetailDto orderDetailDto) {
        return ordersService.addOrderDetailToOrder(ordersService.getOrder(orderId), orderDetailDto);

    }

}
