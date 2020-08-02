package com.hhovhann.customerservice.service;

import com.hhovhann.customerservice.entity.Address;
import com.hhovhann.customerservice.entity.Customer;
import com.hhovhann.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
class CustomerServiceImplTest {

    @BeforeEach
    public void setUp() {
        Customer customerOne = new Customer();
        customerOne.setId(1L);
        customerOne.setFirstName("Alex");
        customerOne.setLastName("Boldwin");
        customerOne.setPhoneNumber("+37491919191");
        customerOne.setEmail("s@gmail.com");
        customerOne.setAddresses(null);


        Customer customerTwo = new Customer();
        customerOne.setId(2L);
        customerTwo.setFirstName("Mike");
        customerTwo.setLastName("Taison");
        customerTwo.setPhoneNumber("+37491919191");
        customerTwo.setEmail("mike@gmail.com");

        Address addressTwo = new Address();
        addressTwo.setId(2L);
        addressTwo.setCity("Yerevan");
        addressTwo.setCustomer(customerTwo);
        customerTwo.setAddresses(List.of(addressTwo));

        Customer customerThree = new Customer();
        customerThree.setId(3L);
        customerThree.setFirstName("Nick");
        customerThree.setLastName("Tailor");
        customerThree.setPhoneNumber("+37391919191");
        customerThree.setEmail("nick@gmail.com");
        customerThree.setAddresses(null);

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customerOne));
        Mockito.when(customerRepository.findAll()).thenReturn(List.of(customerOne, customerTwo, customerThree));
        Mockito.when(customerRepository.findByAddressesCity("Yerevan")).thenReturn(Optional.of(List.of(customerTwo)));
        Mockito.when(customerRepository.findByPhoneNumberStartsWith("+374")).thenReturn(Optional.of(List.of(customerOne, customerTwo)));
    }

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Get customer by existing customer id should find customer")
    public void whenValidCustomer_thenCustomerShouldBeFound() {
        Customer found = customerService.getCustomer(1);

        Assertions.assertEquals(found.getFirstName(), "Alex");
        Assertions.assertEquals(found.getLastName(), "Boldwin");
    }

    @Test
    @DisplayName("Get customer by not existing id will shouldn't find  customer")
    public void whenInValidCustomer_thenExceptionShouldBeThrownAndNoCustomerShouldBeFound() {
        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            customerService.getCustomer(2);
        });
        Assertions.assertTrue(exception.getMessage().contains("No value present"));
    }

    @Test
    @DisplayName("Get customers by existing city name should find all customers")
    public void whenValidCityName_thenAllCustomersShouldBeFound() {
        List<Customer> customers = customerService.getAllCustomersByCity("Yerevan");

        Assertions.assertEquals(customers.size(), 1);
        Assertions.assertEquals(customers.get(0).getFirstName(), "Mike");
    }

    @Test
    @DisplayName("Get customers by not existing city name shouldn't find all customers")
    public void whenInValidCityName_thenExceptionShouldBeThrownAndNoCustomersShouldBeFound() {
        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            customerService.getAllCustomersByCity("Ijevan");
        });
        Assertions.assertTrue(exception.getMessage().contains("No value present"));
    }

    @Test
    @DisplayName("Get customers by correct phone prefix should find all customers")
    public void whenValidPhonePrefix_thenAllCustomersShouldBeFound() {
        List<Customer> customers = customerService.getAllCustomersByPhoneNumber("+374");

        Assertions.assertEquals(customers.size(), 2);
        Assertions.assertEquals(customers.get(1).getFirstName(), "Mike");
    }

    @Test
    @DisplayName("Get customers by not correct phone prefix should find all customers")
    public void whenInValidPhonePrefix_thenExceptionShouldBeThrownAndNoCustomersShouldBeFound() {
        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            customerService.getAllCustomersByPhoneNumber("+098765");
        });
        Assertions.assertTrue(exception.getMessage().contains("No value present"));
    }
}