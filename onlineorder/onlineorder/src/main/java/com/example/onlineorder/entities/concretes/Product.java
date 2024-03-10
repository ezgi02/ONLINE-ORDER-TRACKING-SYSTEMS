package com.example.onlineorder.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;//getter setter
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
/*This class represents product information for online ordering */
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="units_in_stock")
	private short unitsInStock;//Current stock quantity
	
	@Column(name="quantity_per_unit")
	private String quantityPerUnit;//Explanation
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;

}
