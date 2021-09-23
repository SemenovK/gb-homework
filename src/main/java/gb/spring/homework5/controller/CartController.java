package gb.spring.homework5.controller;

import gb.spring.homework5.common.components.Cart;
import gb.spring.homework5.model.dto.OrderDetailDto;
import gb.spring.homework5.model.dto.ProductDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Корзина")
@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    final private Cart cart;

    @GetMapping("")
    public List<OrderDetailDto> showCart(){
        return cart.showCart();
    }

    @PostMapping
    public void addToCart(@RequestBody ProductDto productDto){
        cart.addProduct(productDto);
    }

    @PostMapping("/delete")
    public void deleteFromCart(@RequestBody ProductDto productDto){
        cart.deleteProduct(productDto);
    }

    @PostMapping("/order")
    public void orderCart(){
        cart.createOrder();
    }
}
