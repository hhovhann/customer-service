package com.hhovhann.customerservice;

import com.hhovhann.customerservice.controller.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceApplicationTests {
    @Autowired
    private CustomerController customerController;

    @Test
    void contextLoads() {
        assertThat(customerController).isNotNull();
    }
}
