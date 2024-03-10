package com.example.onlineorder.business.abstracts;

import java.util.List;

import com.example.onlineorder.core.utilities.results.DataResult;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.entities.concretes.Customer;

public interface CustomerService {
	
	DataResult<List<Customer>> getAllCustomers();
    DataResult<Customer> getCustomerById(int id);
    Result addCustomer(Customer customer);
    Result updateCustomer(Customer customer);
    Result deleteCustomer(int id);
}
