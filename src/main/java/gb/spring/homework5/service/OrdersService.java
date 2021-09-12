package gb.spring.homework5.service;

import gb.spring.homework5.model.*;
import gb.spring.homework5.repository.OrderDetailsRepository;
import gb.spring.homework5.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class OrdersService {


    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CustomerService customerService;

    private Map<BigInteger, Order> preparedOrders;

    public OrdersService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.customerService = customerService;
        this.preparedOrders = new ConcurrentHashMap<>();
    }


    public List<Order> getCustomerOrders(BigInteger customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return customer.getOrderList();
    }

    public Order getOrder(BigInteger orderId) {
        return orderRepository.getById(orderId);
    }

    public void addOrder(Order order) {
        orderRepository.save(order);

    }

    public Order prepareOrderForCustomer(BigInteger customerId) {
        Order order = new Order();

        order.setCustomer(customerService.getCustomer(customerId));
        order.setDate(new Date());
        order.setOrderDetailList(new CopyOnWriteArrayList<>());

        preparedOrders.put(customerId, order);
        return order;

    }

    public Order getPreparedOrderForCustomer(BigInteger customerId) {
        return preparedOrders.get(customerId);

    }
}
