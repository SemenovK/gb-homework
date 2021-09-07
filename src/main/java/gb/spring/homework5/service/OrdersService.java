package gb.spring.homework5.service;

import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public List<Order> getCustomerOrders(BigInteger customerId){
        return ordersRepository.getCustomerOrders(customerId);
    }


    public Order getOrder(BigInteger orderId){
        return ordersRepository.getOrder(orderId);
    }

    public List<OrderDetail> getOrderDetails(Order order){
        return ordersRepository.getOrderDetails(order);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(BigInteger orderId){
        return getOrderDetails(getOrder(orderId));
    }

    public Order prepareOrderToCustomer(BigInteger customerId) {
        return ordersRepository.prepareOrderToCustomer(customerId);
    }
    public Order getPreparedOrderByCustomerId(BigInteger customerId) {
        return ordersRepository.getPreparedOrderByCustomerId(customerId);
    }
    public void addOrder(BigInteger customerId, Order order){
        ordersRepository.addOrder(customerId, order);
    }
    public void addOrderDetailByCustomerId(BigInteger customerId, OrderDetail orderDetail) {
        ordersRepository.addOrderDetailByCustomerId(customerId, orderDetail);
    }

    public void resetPreparedOrderByCustomerId(BigInteger customerId) {
        ordersRepository.resetPreparedOrderByCustomerId(customerId);
    }
}
