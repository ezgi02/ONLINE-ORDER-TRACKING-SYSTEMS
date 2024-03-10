package com.example.onlineorder.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineorder.entities.concretes.Customer;

public interface CustomerDao extends JpaRepository<Customer,Integer>{

}
