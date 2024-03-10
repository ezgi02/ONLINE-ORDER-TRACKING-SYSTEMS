package com.example.onlineorder.business.abstracts;

import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.entities.concretes.OrderItem;

public interface OrderItemService {
	Result addOrderItem(OrderItem orderItem);
	
}
