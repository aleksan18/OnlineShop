package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    Customer customer=new Customer(1,"TEST","TEST2",23,"test@gmail","password","4593804514","2400","Copenhagen","Denmark","test address");
    //since in constructor I do not assign a id I have to get it from the database since when running the tests the id's change
    @BeforeEach
    void setUp() {
       customerRepository.addCustomer(customer);
    }

    @AfterEach
    void tearDown() {
        customer.setId(customerRepository.findCustomerByEmail(customer.getEmail()).getId());
        customerRepository.deleteCustomer(customer.getId());
    }

    @Test
    void fetchAllCustomers() {
      assertThat(customerRepository.fetchAllCustomers()).isNotEmpty();
    }

    @Test
    void findCustomerByID() {
    assertThat(customerRepository.findCustomerByID(customerRepository.findCustomerByEmail(customer.getEmail()).getId())).isNotNull();
    }

    @Test
    void updateCustomer() {
        assertThat(customerRepository.updateCustomer(customer,customer.getId()));
    }

    @Test
    void findCustomerByEmail() {
        assertThat(customerRepository.findCustomerByEmail(customer.getEmail())).isNotNull();
    }
}