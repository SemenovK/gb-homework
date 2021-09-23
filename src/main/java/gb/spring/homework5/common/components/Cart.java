package gb.spring.homework5.common.components;

import gb.spring.homework5.model.dto.OrderDetailDto;
import gb.spring.homework5.model.dto.ProductDto;
import gb.spring.homework5.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
@RequiredArgsConstructor
public class Cart {
    private final OrdersService ordersService;
    List<OrderDetailDto> customersProducts = new ArrayList<>();

    public void addProduct(ProductDto productDto) {
        OrderDetailDto detail = findOrderDeatil(productDto);
        if (detail == null) {
            detail = new OrderDetailDto();
            detail.setProductDto(productDto);
            detail.setPrice(productDto.getPrice());
            detail.setQuantity(1);
            customersProducts.add(detail);
        } else {
            detail.setQuantity(detail.getQuantity() + 1);
        }
        System.out.println(customersProducts);
    }

    public void deleteProduct(ProductDto productDto) {
        OrderDetailDto detail = findOrderDeatil(productDto);
        if (detail != null) {
            customersProducts.remove(detail);
        }

    }

    public void createOrder() {
        ordersService.createOrderAndFillDetails(showCart());
        customersProducts.clear();
    }

    public List<OrderDetailDto> showCart() {
        return customersProducts;
    }


    private OrderDetailDto findOrderDeatil(ProductDto productDto) {
        for (OrderDetailDto detail : customersProducts) {
            if (detail.getProductDto().equals(productDto)) {
                return detail;
            }
        }
        return null;
    }

}
