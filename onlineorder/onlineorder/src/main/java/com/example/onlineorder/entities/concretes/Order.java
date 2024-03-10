package com.example.onlineorder.entities.concretes;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderss")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","customer","orderItems"})
public class Order {
	 	@Id
	    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "order_id")
	    private int id;

	    @ManyToOne
	    @JoinColumn(name = "customer_id", nullable = false)
	    
	    private Customer customer;

	    @OneToMany(mappedBy = "order")
	    
	    private List<OrderItem> orderItems;

	    @Column(name = "order_date")
	    private LocalDateTime orderDate;

	    @Column(name = "total_amount")
	    private double totalAmount;
	    
	    @Column(name = "is_canceled")
	    private boolean isCanceled;

}
