package gb.spring.homework5.controller;

import gb.spring.homework5.model.*;
import gb.spring.homework5.service.OrdersService;
import gb.spring.homework5.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Log4j2
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;
    private final ProductService productService;


    @GetMapping("create/{customerId}")
    public String addOrder(@PathVariable BigInteger customerId, Model model) {

        Order order = ordersService.prepareOrderForCustomer(customerId);

        model.addAttribute("path", "/order/create/" + customerId);
        model.addAttribute("order", order);
        model.addAttribute("details", order.getOrderDetailList());
        model.addAttribute("products", productService.getProductsList());

        return "ordersAdd";
    }

    @PostMapping("create/{customerId}")
    public String addOrderDetail(@PathVariable BigInteger customerId,
                                 @RequestParam BigInteger product_id,
                                 @RequestParam Integer quantity,
                                 Model model) {

        Order order = ordersService.getPreparedOrderForCustomer(customerId);
        if (order != null) {
            OrderDetailID orderDetailID = new OrderDetailID();
            Product product = productService.getProduct(product_id);
            orderDetailID.setProduct(product);
            orderDetailID.setOrder(order);
            OrderDetail orderDetail = new OrderDetail(orderDetailID, product.getCost(), quantity);
            order.getOrderDetailList().add(orderDetail);

        }
        model.addAttribute("order", order);
        model.addAttribute("details", order.getOrderDetailList());
        model.addAttribute("path", "/order/create/" + customerId);
        model.addAttribute("products", productService.getProductsList());

        return "ordersAdd";
    }

    @PostMapping("create/{customerId}/save")
    public String savePreparedOrder(@PathVariable BigInteger customerId,
                                    Model model) {
        Order order = ordersService.getPreparedOrderForCustomer(customerId);
        if (order != null) {
            ordersService.addOrder(order);
        }

        return "redirect:/customers/history/" + customerId;
    }
}
