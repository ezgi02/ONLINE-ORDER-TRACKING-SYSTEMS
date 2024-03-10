package com.example.onlineorder.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineorder.entities.concretes.Order;

public interface OrderDao extends JpaRepository<Order,Integer>{

}
