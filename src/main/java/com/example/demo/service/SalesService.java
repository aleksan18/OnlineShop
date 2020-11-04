package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Sales;
import com.example.demo.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {
    @Autowired
    SalesRepository salesRepository;
    public List<Sales> fetchAllSales(){
        return salesRepository.fetchAllSales();
    }
    public List<Sales> findSalesById(int id){
        return salesRepository.findSalesById(id);
    }
    public void deleteSales(int id){
        salesRepository.deleteSales(id);
    }
    public void addSales(Sales sales){
        salesRepository.addSales(sales);
    }
    public void updateSales(Sales sales,int id) {
        salesRepository.updateSales(sales,id);
    }
    public List<Sales> findSalesByCustomerId(Customer customer){
        return salesRepository.findSalesByCustomerId(customer.getId());
    }
}
