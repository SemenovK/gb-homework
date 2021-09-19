package gb.spring.homework5.model.dto;

import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.model.OrderDetailID;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
@ApiModel(value = "Заказ", description = "DTO Заказ")
public class OrderDto {

    private BigInteger order_id;
    private Date date;
    private CustomerDto customerDto;


    public static OrderDto valueOf(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder_id(order.getOrder_id());
        orderDto.setCustomerDto(CustomerDto.valueOf(order.getCustomer()));
        orderDto.setDate(order.getDate());


        return orderDto;
    }

    public Order toOrder() {
        Order order = new Order();

        order.setOrder_id(this.order_id);
        order.setCustomer(this.customerDto.toCustomer());
        order.setDate(this.date);

        return order;
    }
}
