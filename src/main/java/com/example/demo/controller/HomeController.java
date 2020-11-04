package com.example.demo.controller;

import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    CustomerService customerService;
    @GetMapping({"","/","/index","/index.html"})
    public String index()
    {
        customerService.fetchAllCustomers();
        return "index.html";
    }

}
