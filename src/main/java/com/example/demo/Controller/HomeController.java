package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    CustomerService customerService;
    @GetMapping("/")
    public String index()
    {
        customerService.fetchAllCustomers();
        return "index";
    }

}
