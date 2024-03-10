package com.example.onlineorder.api.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineorder.business.abstracts.OrderItemService;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.entities.concretes.OrderItem;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
		private final OrderItemService orderItemService;
	  @Autowired
	    public OrderItemController(OrderItemService orderItemService) {
	        this.orderItemService = orderItemService;
	    }

	    @PostMapping("/add")
	    public Result addOrderItem(@RequestBody OrderItem orderItem) {
	        return orderItemService.addOrderItem(orderItem);
	    }

}
