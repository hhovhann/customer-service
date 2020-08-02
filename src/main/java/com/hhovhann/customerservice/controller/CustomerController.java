package com.hhovhann.customerservice.controller;

import com.hhovhann.customerservice.entity.Address;
import com.hhovhann.customerservice.entity.Customer;
import com.hhovhann.customerservice.service.CustomerService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customer", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.of(Optional.of((customerService.getAllCustomers())));
    }

    @GetMapping(path = "/city/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomersByCity(@PathVariable("name") String customerCity) {
        return ResponseEntity.of(Optional.of(customerService.getAllCustomersByCity(customerCity)));
    }

    @GetMapping(path = "/phone/{prefix}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomersByPhonePrefix(@NonNull @PathVariable("prefix") String prefix) {
        return ResponseEntity.of(Optional.of((customerService.getAllCustomersByPhoneNumber(prefix))));
    }

    @GetMapping(path = "/customer/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@NonNull @PathVariable("id") long customerId) {
        return ResponseEntity.of(Optional.of(customerService.getCustomer(customerId)));
    }

    @PostMapping(path = "/customer", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.of(Optional.of(customerService.createCustomer(customer)));
    }

    @PostMapping(path = "/customer/{id}/address", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createAddress(@NonNull @PathVariable("id") Long id, @NonNull @RequestBody Address address) {
        return ResponseEntity.of(Optional.of(customerService.createAddress(id, address)));
    }

    @DeleteMapping(path = "/customer/{id}/address/{address_id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> deleteAddress(@NonNull @PathVariable("id") Long customerId, @NonNull @PathVariable("address_id") Integer addressId) {
        return ResponseEntity.of(Optional.of(customerService.deleteCustomer(customerId, addressId)));
    }
}
