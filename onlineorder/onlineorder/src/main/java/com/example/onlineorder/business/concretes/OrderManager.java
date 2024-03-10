package com.example.onlineorder.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlineorder.business.abstracts.OrderService;
import com.example.onlineorder.core.utilities.results.DataResult;
import com.example.onlineorder.core.utilities.results.ErrorDataResult;
import com.example.onlineorder.core.utilities.results.ErrorResult;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.core.utilities.results.SuccessDataResult;
import com.example.onlineorder.core.utilities.results.SuccessResult;
import com.example.onlineorder.dataAccess.abstracts.OrderDao;
import com.example.onlineorder.dataAccess.abstracts.ProductDao;
import com.example.onlineorder.entities.concretes.Order;
import com.example.onlineorder.entities.concretes.OrderItem;
import com.example.onlineorder.entities.concretes.Product;

@Service
public class OrderManager implements OrderService{
	 	private final OrderDao orderDao;
	 	private final ProductDao productDao;

	    @Autowired
	    public OrderManager(OrderDao orderDao,ProductDao productDao) {
	        this.orderDao = orderDao;
	        this.productDao=productDao;
	    }
	    @Override
	    public Result createOrder(Order order) {
	    	//Saving the order to the database
	        orderDao.save(order);
	        return new SuccessResult("The order has been created successfully.");
	    }

	    @Override
	    public Result updateOrder(Order order) {
	        //Updating the order
	        orderDao.save(order);
	        return new SuccessResult("the order has been updated succesfully");
	    }

	    @Override
	    public Result deleteOrder(int orderId) {
	        // Deleting the order
	        orderDao.deleteById(orderId);
	        return new SuccessResult("the order has been deleted succesfully");
	    }
	   
	    @Override
	    public DataResult<List<Order>> getAllOrders() {
	        // Fetching all orders
	        List<Order> orders = orderDao.findAll();
	        return new SuccessDataResult<List<Order>>(orders,"Fetched all orders");
	    }
	
	    @Override
	    public Result cancelOrder(int orderId) {
	        // Find the order
	        Optional<Order> orderOptional = orderDao.findById(orderId);
	        if (orderOptional.isEmpty()) {
	            return new ErrorResult("Order not found.");
	        }

	        Order order = orderOptional.get();

	        // Check if the order is cancelable
	        if (!order.isCanceled()) {
	            return new ErrorResult("The order cannot be cancelled");
	        }

	        // Cancel order
	        order.setCanceled(true);
	        orderDao.save(order);

	        // Add the products of the order back to stock
	        for (OrderItem orderItem : order.getOrderItems()) {
	            Product product = orderItem.getProduct();
	            short currentStock = product.getUnitsInStock();
	            short quantity = (short) orderItem.getQuantity();
	            product.setUnitsInStock((short) (currentStock + quantity));
	            productDao.save(product);
	        }

	        return new SuccessResult("The order cancelled successfully");
	    }

	    @Override
	    public DataResult<Order> getOrderById(int orderId) {
	        // Bringing the order by id
	        Order order = orderDao.findById(orderId).orElse(null);
	        if (order != null) {
	            return new SuccessDataResult<Order>(order, "Order found");
	        }
	        return new ErrorDataResult<Order>("Order not found");
	    }


}
