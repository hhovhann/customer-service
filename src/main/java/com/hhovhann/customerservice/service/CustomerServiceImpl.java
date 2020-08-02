package com.hhovhann.customerservice.service;

import com.hhovhann.customerservice.entity.Address;
import com.hhovhann.customerservice.entity.Customer;
import com.hhovhann.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /*
     * Get all customers
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /*
     * Get all customers from this city
     */
    @Override
    public List<Customer> getAllCustomersByCity(String city) {
        return customerRepository.findByAddressesCity(city).orElseThrow();
    }

    /*
     * Get all customers starting with the following prefix phone number
     */
    @Override
    public List<Customer> getAllCustomersByPhoneNumber(String prefix) {
        return customerRepository.findByPhoneNumberStartsWith(prefix).orElseThrow();
    }

    /*
     * Get customer with the id defined
     */
    @Override
    public Customer getCustomer(long customerId) {
        return customerRepository.findById(customerId).orElseThrow();
    }

    /*
     * Create a Customer
     */
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /*
     * Create an address for this customer
     */
    @Override
    public Customer createAddress(long customerId, Address address) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        customer.addAddress(address);
        return customerRepository.save(customer);
    }

    /*
     * Delete the address of the customer
     */
    @Override
    public Customer deleteCustomer(long customerId, int addressId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Address address = customer.getAddresses().stream().filter(currentAddress -> currentAddress.getId() == addressId).findFirst().orElseThrow();
        customer.removeAddress(address);
        return customerRepository.save(customer);
    }
}
