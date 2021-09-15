package gb.spring.homework5.model.dto;

import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.model.OrderDetailID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private BigInteger order_id;
    private Date date;
    private CustomerDto customerDto;
    private List<OrderDetailDto> orderDetailList;

    public static OrderDto valueOf(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder_id(order.getOrder_id());
        orderDto.setCustomerDto(CustomerDto.valueOf(order.getCustomer()));
        orderDto.setDate(order.getDate());
        orderDto.setOrderDetailList(new CopyOnWriteArrayList<>());
        for (OrderDetail orderDetail : order.getOrderDetailList()) {
            orderDto.getOrderDetailList().add(OrderDetailDto.valueOf(orderDetail));
        }

        return orderDto;
    }

    public Order toOrder() {
        Order order = new Order();

        order.setOrder_id(this.order_id);
        order.setCustomer(this.customerDto.toCustomer());
        order.setDate(this.date);
        order.setOrderDetailList(new CopyOnWriteArrayList<>());
        for (OrderDetailDto orderDetailDto : this.getOrderDetailList()) {
            order.getOrderDetailList().add(orderDetailDto.toOrderDeatail());
        }

        return order;
    }
}
