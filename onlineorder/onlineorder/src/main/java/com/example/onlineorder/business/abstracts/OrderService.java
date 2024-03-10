package com.example.onlineorder.business.abstracts;

import java.util.List;

import com.example.onlineorder.core.utilities.results.DataResult;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.entities.concretes.Order;

public interface OrderService {
	  	Result createOrder(Order order);
	    Result updateOrder(Order order);
	    Result deleteOrder(int orderId);
	    DataResult<Order> getOrderById(int orderId);
	    DataResult<List<Order>> getAllOrders();
	    Result cancelOrder(int orderId);
}
