package com.example.onlineorder.business.concretes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlineorder.business.abstracts.CustomerService;
import com.example.onlineorder.core.utilities.results.DataResult;
import com.example.onlineorder.core.utilities.results.ErrorDataResult;
import com.example.onlineorder.core.utilities.results.ErrorResult;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.core.utilities.results.SuccessDataResult;
import com.example.onlineorder.core.utilities.results.SuccessResult;
import com.example.onlineorder.dataAccess.abstracts.CustomerDao;
import com.example.onlineorder.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService {
    private final CustomerDao customerDao;
    private final Logger logger = LoggerFactory.getLogger(CustomerManager.class);

    @Autowired
    public CustomerManager(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public DataResult<List<Customer>> getAllCustomers() {
        logger.info("Fetching all customers.");
        List<Customer> customers = customerDao.findAll();
        return new SuccessDataResult<>(customers, "Customers listed successfully");
    }

    @Override
    public DataResult<Customer> getCustomerById(int id) {
        logger.info("Fetching customer by id: {}", id);
        Customer customer = customerDao.findById(id).orElse(null);
        if (customer == null) {
            logger.warn("Customer not found with id: {}", id);
            return new ErrorDataResult<>("Customer not found");
        }
        logger.info("Customer found with id: {}", id);
        return new SuccessDataResult<>(customer, "Customer found successfully");
    }

    @Override
    public Result addCustomer(Customer customer) {
        logger.info("Adding customer: {}", customer);
        customerDao.save(customer);
        return new SuccessResult("Customer added successfully");
    }

    @Override
    public Result updateCustomer(Customer customer) {
        logger.info("Updating customer: {}", customer);
        if (customer.getId() == 0) {
            logger.error("Customer id cannot be empty for updating customer");
            return new ErrorResult("Customer id cannot be empty for updating customer");
        }
        customerDao.save(customer);
        return new SuccessResult("Customer updated successfully");
    }

    @Override
    public Result deleteCustomer(int id) {
        logger.info("Deleting customer with id: {}", id);
        Customer customer = customerDao.findById(id).orElse(null);
        if (customer == null) {
            logger.warn("Customer not found with id: {}", id);
            return new ErrorResult("Customer not found");
        }
        customerDao.delete(customer);
        return new SuccessResult("Customer deleted successfully");
    }
}
