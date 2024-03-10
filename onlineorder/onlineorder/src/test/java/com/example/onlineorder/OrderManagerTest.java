package com.example.onlineorder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.onlineorder.business.concretes.OrderManager;
import com.example.onlineorder.dataAccess.abstracts.OrderDao;
import com.example.onlineorder.dataAccess.abstracts.ProductDao;
import com.example.onlineorder.entities.concretes.Order;
import com.example.onlineorder.entities.concretes.OrderItem;
import com.example.onlineorder.entities.concretes.Product;

class OrderManagerTest {

    @Mock
    private OrderDao orderDao;

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private OrderManager orderManager;

    public OrderManagerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_ValidOrder_ShouldReturnSuccess() {
        // Given
        Order order = new Order();
        when(orderDao.save(any(Order.class))).thenReturn(order);

        // When
        var result = orderManager.createOrder(order);

        // Then
        assertTrue(result.isSuccess());
        assertEquals("The order has been created successfully.", result.getMessage());
    }

    @Test
    void updateOrder_ValidOrder_ShouldReturnSuccess() {
        // Given
        Order order = new Order();
        when(orderDao.save(any(Order.class))).thenReturn(order);

        // When
        var result = orderManager.updateOrder(order);

        // Then
        assertTrue(result.isSuccess());
        assertEquals("the order has been updated succesfully", result.getMessage());
    }

    @Test
    void deleteOrder_ExistingOrderId_ShouldReturnSuccess() {
        // Given
        int orderId = 1;

        // When
        var result = orderManager.deleteOrder(orderId);

        // Then
        assertTrue(result.isSuccess());
        assertEquals("the order has been deleted succesfully", result.getMessage());
        verify(orderDao, times(1)).deleteById(orderId);
    }

    @Test
    void getOrderById_ExistingOrderId_ShouldReturnOrder() {
        // Given
        int orderId = 1;
        Order order = new Order();
        when(orderDao.findById(orderId)).thenReturn(Optional.of(order));

        // When
        var result = orderManager.getOrderById(orderId);

        // Then
        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(order, result.getData());
    }

    @Test
    void getOrderById_NonExistingOrderId_ShouldReturnError() {
        // Given
        int orderId = 999;
        when(orderDao.findById(orderId)).thenReturn(Optional.empty());

        // When
        var result = orderManager.getOrderById(orderId);

        // Then
        assertFalse(result.isSuccess());
        assertNull(result.getData());
        assertEquals("Order not found", result.getMessage());
    }

    @Test
    void getAllOrders_ShouldReturnListOfOrders() {
        // Given
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        when(orderDao.findAll()).thenReturn(orders);

        // When
        var result = orderManager.getAllOrders();

        // Then
        assertTrue(result.isSuccess());
        assertEquals(2, result.getData().size());
    }

    @Test
    void cancelOrder_ExistingOrderId_ShouldReturnSuccess() {
        // Given
        int orderId = 1;
        Order order = new Order();
        order.setId(orderId);
        order.setCanceled(false);

        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        product.setUnitsInStock((short) 10);
        orderItem.setProduct(product);
        order.setOrderItems(List.of(orderItem));

        when(orderDao.findById(orderId)).thenReturn(Optional.of(order));

        // When
        var result = orderManager.cancelOrder(orderId);

        // Then
        assertTrue(result.isSuccess());
        assertTrue(order.isCanceled());
        verify(productDao, times(1)).save(product);
    }
}
