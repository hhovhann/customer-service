package com.hhovhann.customerservice.service;

import com.hhovhann.customerservice.entity.Address;
import com.hhovhann.customerservice.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    List<Customer> getAllCustomersByCity(String city);

    List<Customer> getAllCustomersByPhoneNumber(String prefix);

    Customer getCustomer(long customerId);

    Customer createCustomer(Customer customer);

    Customer createAddress(long customerId, Address address);

    Customer deleteCustomer(long customerId, int addressId);
}
