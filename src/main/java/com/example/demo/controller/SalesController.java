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

    @GetMapping("/")
    public String fetchAllSales(Model model){
        List<Sales> salesList = salesService.fetchAllSales();
        model.addAttribute("items", salesList);

        return "/index";
    }
    @GetMapping
    public Sales findSalesById(@PathVariable("id") int id){
        return salesService.findSalesById(id);
    }
    @DeleteMapping(path="{id}")
    public String deleteSales(@PathVariable("id") int id){
        salesService.deleteSales(id);
        return "redirect:/sales";
    }
    @PostMapping
    public String addSales(@ModelAttribute Sales sales){
        salesService.addSales(sales);
        return "redirect:/sales";
    }
    @PutMapping(path = "{id}")
    public String updateSales(@RequestBody Sales sales,@PathVariable("id") int id) {
        salesService.updateSales(sales,id);
        return "redirect:/movies/getOne/";
    }
    public List<Sales> findSalesByCustomerId(Customer customer){
        return salesService.findSalesByCustomerId(customer.getId());
    }
}
