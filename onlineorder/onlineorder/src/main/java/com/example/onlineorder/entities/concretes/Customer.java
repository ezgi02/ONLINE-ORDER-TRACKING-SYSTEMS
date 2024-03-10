package com.example.onlineorder.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customerss")
public class Customer {
	
	
	 	@Id
	    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "customer_id")
	    private int id;

	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "last_name")
	    private String lastName;
	    
	    @Column(name = "email")
	    private String email;

	    @Column(name = "phone_number")
	    private String phoneNumber;

	    @OneToMany(mappedBy = "customer")
	    @JsonIgnore 
	    private List<Order> orders;

}
