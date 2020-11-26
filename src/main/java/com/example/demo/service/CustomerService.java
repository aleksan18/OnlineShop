package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public List<Customer> fetchAllCustomers()
    {
        return customerRepository.fetchAllCustomers();
    }
    public void deleteCustomer(int id){
        customerRepository.deleteCustomer(id);
    }
    public void updateCustomer(Customer customer,int id){
        customerRepository.updateCustomer(customer,id);
    }
    public Customer findCustomerByEmail(String email){
      return customerRepository.findCustomerByEmail(email) ;
    }
    public Customer findCustomerById(int id){
        return customerRepository.findCustomerByID(id);
    }
    public void addCustomer(Customer customer){
        customerRepository.addCustomer(customer);
    }

}
