package com.example.onlineorder;

import com.example.onlineorder.business.concretes.OrderItemManager;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.dataAccess.abstracts.ProductDao;
import com.example.onlineorder.entities.concretes.OrderItem;
import com.example.onlineorder.entities.concretes.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
//import static org.

import static org.mockito.Mockito.when;

public class OrderItemManagerTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private OrderItemManager orderItemManager;

    public OrderItemManagerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addOrderItem_ProductExists_ShouldReturnSuccess() {
        // Given
        Product product = new Product();
        product.setId(1);
        product.setUnitsInStock((short) 10);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(5);

        when(productDao.existsById(product.getId())).thenReturn(true);

        // When
        Result result = orderItemManager.addOrderItem(orderItem);

        // Then
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals("Order item has been added successfully.", result.getMessage());
    }

    @Test
    void addOrderItem_ProductDoesNotExist_ShouldReturnError() {
        // Given
        Product product = new Product();
        product.setId(1);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);

        when(productDao.existsById(product.getId())).thenReturn(false);

        // When
        Result result = orderItemManager.addOrderItem(orderItem);

        // Then
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Selected product does not exist.", result.getMessage());
    }
}

