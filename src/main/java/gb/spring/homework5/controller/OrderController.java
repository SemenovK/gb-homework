package gb.spring.homework5.controller;

import gb.spring.homework5.model.*;
import gb.spring.homework5.service.CustomerService;
import gb.spring.homework5.service.OrdersService;
import gb.spring.homework5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final CustomerService customerService;
    private final OrdersService ordersService;
    private final ProductService productService;

    @GetMapping("create/{customerId}")
    public String addOrder(@PathVariable BigInteger customerId, Model model) {

        Order order = ordersService.prepareOrderToCustomer(customerId);
        order.setOrderDetailList(new CopyOnWriteArrayList<>());
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
        Order order = ordersService.getPreparedOrderByCustomerId(customerId);
        if (order != null) {
            OrderDetailID orderDetailID = new OrderDetailID();
            orderDetailID.setProduct_id(product_id);
            Product product = productService.getProduct(product_id);
            OrderDetail orderDetail = new OrderDetail(orderDetailID, product.getCost(), quantity, product);
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
        Order order = ordersService.getPreparedOrderByCustomerId(customerId);
        if (order != null) {

            System.out.println(order);
            System.out.println(order.getOrderDetailList());
            ordersService.addOrder(customerId, order);
        }


        return "redirect:/customers/history/" + customerId;
    }
}
