package com.example.onlineorder.api.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.onlineorder.business.abstracts.CustomerService;
import com.example.onlineorder.core.utilities.results.DataResult;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.entities.concretes.Customer;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	  private final CustomerService customerService;

	    @Autowired
	    public CustomerController(CustomerService customerService) {
	        this.customerService = customerService;
	    }
	    @GetMapping("/getall")
	    public DataResult<List<Customer>> getAll() {
	        return customerService.getAllCustomers();
	    }

	    @GetMapping("/getbyid")
	    public DataResult<Customer> getById(@RequestParam int id) {
	        return customerService.getCustomerById(id);
	    }

	    @PostMapping("/add")
	    public Result add(@RequestBody Customer customer) {
	        return customerService.addCustomer(customer);
	    }

	    @PutMapping("/update")
	    public Result update(@RequestBody Customer customer) {
	        return customerService.updateCustomer(customer);
	    }

	    @DeleteMapping("/delete")
	    public Result delete(@RequestParam int id) {
	        return customerService.deleteCustomer(id);
	    }

}
