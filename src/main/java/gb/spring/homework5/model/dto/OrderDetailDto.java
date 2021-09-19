package gb.spring.homework5.model.dto;

import gb.spring.homework5.model.Order;
import gb.spring.homework5.model.OrderDetail;
import gb.spring.homework5.model.OrderDetailID;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "Детали заказа", description = "DTO детали заказа")
public class OrderDetailDto {
    ProductDto productDto;
    BigDecimal price;
    private int quantity;


    public static OrderDetailDto valueOf(OrderDetail orderDetail){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setProductDto(ProductDto.valueOf(orderDetail.getOrderDetailID().getProduct()));
        orderDetailDto.setPrice(orderDetail.getPrice_on_order_date());
        orderDetailDto.setQuantity(orderDetail.getQuantity());

        return orderDetailDto;
    }

    public OrderDetail toOrderDeatail(){
        OrderDetail orderDetail = new OrderDetail();
        OrderDetailID orderDetailID = new OrderDetailID();
        orderDetailID.setProduct(this.productDto.toProduct());
        orderDetail.setOrderDetailID(orderDetailID);
        orderDetail.setQuantity(quantity);
        orderDetail.setPrice_on_order_date(price);
        return orderDetail;
    }

}
