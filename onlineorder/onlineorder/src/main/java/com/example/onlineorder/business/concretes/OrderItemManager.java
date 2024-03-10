package com.example.onlineorder.business.concretes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlineorder.business.abstracts.OrderItemService;
import com.example.onlineorder.core.utilities.results.ErrorResult;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.core.utilities.results.SuccessResult;
import com.example.onlineorder.dataAccess.abstracts.ProductDao;
import com.example.onlineorder.entities.concretes.OrderItem;
import com.example.onlineorder.entities.concretes.Product;

@Service
public class OrderItemManager implements OrderItemService {
    private final ProductDao productDao;
    private final Logger logger = LoggerFactory.getLogger(OrderItemManager.class);

    @Autowired
    public OrderItemManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Result addOrderItem(OrderItem orderItem) {
        logger.info("Adding order item: {}", orderItem);
        // Check if the selected product exists
        if (productDao.existsById(orderItem.getProduct().getId())) {
            // If the product exists, save the order item
            // Here, you can perform the operation to save the order item to the database
            
            // Decrease the product stock
            Product product = orderItem.getProduct();
            short currentStock = product.getUnitsInStock();
            short quantity = (short) orderItem.getQuantity();
            product.setUnitsInStock((short) (currentStock - quantity));
            productDao.save(product);
            
            logger.info("Order item has been added successfully.");
            return new SuccessResult("Order item has been added successfully.");
        } else {
            // If the product does not exist, return an error
            logger.error("Selected product does not exist.");
            return new ErrorResult("Selected product does not exist.");
        }
    }
}
