package gb.spring.homework5.service;

import gb.spring.homework5.model.*;
import gb.spring.homework5.model.dto.CustomerDto;
import gb.spring.homework5.model.dto.OrderDetailDto;
import gb.spring.homework5.model.dto.OrderDto;
import gb.spring.homework5.repository.OrderDetailsRepository;
import gb.spring.homework5.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class OrdersService {


    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CustomerService customerService;


    public OrdersService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.customerService = customerService;
    }

    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream().map(OrderDto::valueOf).collect(Collectors.toList());
    }

    public List<OrderDto> getCustomerOrders(BigInteger customerId) {
        CustomerDto customer = customerService.getCustomer(customerId);

        return orderRepository.findOrdersByCustomer(customer.toCustomer()).stream()
                .map(OrderDto::valueOf).collect(Collectors.toUnmodifiableList());
    }

    public OrderDto getOrder(BigInteger orderId) {
        return OrderDto.valueOf(orderRepository.getById(orderId));
    }

    public OrderDto addOrder(OrderDto orderDto) {
        Order order = orderDto.toOrder();
        orderRepository.save(order);
        return OrderDto.valueOf(order);

    }

    public void deleteOrder(OrderDto orderDto) {
        orderDetailsRepository.deleteOrderDetailsByOrderDetailID_Order(orderDto.toOrder());
        orderRepository.delete(orderDto.toOrder());
    }

    public List<OrderDetailDto> getOrderDetailByOrder(OrderDto orderDto) {
        List<OrderDetail> orderDetailList = orderDetailsRepository.findOrderDetailsByOrderDetailID_Order(orderDto.toOrder());
        return orderDetailList.stream()
                .map(OrderDetailDto::valueOf)
                .collect(Collectors.toList());
    }


    public OrderDto addOrderDetailToOrder(OrderDto orderDto, OrderDetailDto orderDetailDto) {
        Order order = orderDto.toOrder();
        OrderDetail orderDetail = orderDetailDto.toOrderDeatail();

        orderDetail.getOrderDetailID().setOrder(order);
        orderDetailsRepository.save(orderDetail);
        return getOrder(order.getOrder_id());
    }
}
