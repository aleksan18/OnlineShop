package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Items;
import com.example.demo.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService;
    @GetMapping("/")
    public String fetchAllCustomers(Model model)
    {
        List<Customer> customersList = customerService.fetchAllCustomers();
        model.addAttribute("customers", customersList);

        return "/index";
    }
    @DeleteMapping(path="{id}")
    public void deleteCustomer(@PathVariable("id") int id){
        customerService.deleteCustomer(id);
    }
    @PutMapping(path = "{id}")
    public void updateCustomer(@RequestBody Customer customer,@PathVariable("id") int id){
        customerService.updateCustomer(customer,id);
    }
    @GetMapping
    public Customer findCustomerByEmail(@PathVariable("email") String email){
        return customerService.findCustomerByEmail(email) ;
    }
    @GetMapping
    public Customer findCustomerById(@PathVariable("id") int id){
        return customerService.findCustomerById(id);
    }
    @PostMapping
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }
}
