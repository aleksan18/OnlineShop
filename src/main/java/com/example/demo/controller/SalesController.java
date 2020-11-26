package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Items;
import com.example.demo.model.Sales;
import com.example.demo.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping("/index")
    public String fetchAllSales(Model model){
        List<Sales> salesList = salesService.fetchAllSales();
        model.addAttribute("sales", salesList);

        return "/index";
    }

    @DeleteMapping("/Sales/{id}")
    public String deleteSales(@PathVariable("id") int id){
        salesService.deleteSales(id);
        return "redirect:/sales";
    }
    @PostMapping
    public String addSales(@ModelAttribute Sales sales){
        salesService.addSales(sales);
        return "redirect:/Sales";
    }
    @PutMapping(path = "{id}")
    public String updateSales(@RequestBody Sales sales,@PathVariable("id") int id) {
        salesService.updateSales(sales,id);
        return "redirect:/sales/getOne/";
    }
    @GetMapping("/Sales/{id}")
    public Sales findSalesById(@PathVariable("id") int id){
        return salesService.findSalesById(id);
    }

    @GetMapping("/Sales/{id}/{customer}")
    public List<Sales> findSalesByCustomerId(@PathVariable("id") int id ,@RequestBody Customer customer){
        return salesService.findSalesByCustomerId(customer);
    }
}
