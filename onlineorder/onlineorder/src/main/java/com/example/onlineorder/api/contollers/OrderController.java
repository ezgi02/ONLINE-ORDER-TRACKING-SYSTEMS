package com.example.onlineorder.api.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineorder.business.abstracts.OrderService;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.entities.concretes.Order;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public Result createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/update")
    public Result updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/delete/{orderId}")
    public Result deleteOrder(@PathVariable int orderId) {
        return orderService.deleteOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public Result getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/all")
    public Result getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/cancel/{orderId}")
    public Result cancelOrder(@PathVariable int orderId) {
        return orderService.cancelOrder(orderId);
    }
}
