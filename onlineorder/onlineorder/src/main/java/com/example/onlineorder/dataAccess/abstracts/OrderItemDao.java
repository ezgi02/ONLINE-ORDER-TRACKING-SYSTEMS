package com.example.onlineorder.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineorder.entities.concretes.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem,Integer>{

}
