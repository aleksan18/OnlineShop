package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Items;
import com.example.demo.model.Sales;
import com.example.demo.model.ShoppingCart;
import com.example.demo.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService {
    @Autowired
    SalesRepository salesRepository;
    @Autowired
    ItemsService itemsService;

    public List<Sales> fetchAllSales(){
        return salesRepository.fetchAllSales();
    }
    public Sales findSalesById(int id){
        return salesRepository.findSalesById(id);
    }
    public void deleteSales(int id){
        salesRepository.deleteSales(id);
    }
    public double endPrice(List<ShoppingCart> shoppingCartList){
        double end_price=0;

            for (ShoppingCart shoppingCart : shoppingCartList) {
                int quantity = shoppingCart.getQuantity();
                double price = itemsService.findItemsByID(shoppingCart.getItems_id()).getPrice();
                end_price= quantity*price;
            }

       return end_price;
    }
    public void addSales(Sales sales)
    {
        Items items=new Items();
        salesRepository.addSales(sales,items);
    }
    public void createSales(List<ShoppingCart>shoppingCartList,Customer customer){
        Items items;
        Sales sales=salesRepository.createSales(customer,endPrice(shoppingCartList));
        for(ShoppingCart shoppingCart: shoppingCartList) {
            items=itemsService.findItemsByID(shoppingCart.getItems_id());
            items.setSize(shoppingCart.getSize());
            items.setQuantity(shoppingCart.getQuantity());
            salesRepository.addSales(sales,items);

        }
    }
    public void updateSales(Sales sales,int id) {
        salesRepository.updateSales(sales,id);
    }
    public List<Sales> findSalesByCustomerId(Customer customer){
        return salesRepository.findSalesByCustomerId(customer.getId());
    }
    public List<Items> findItemsBoughtByCustomer(List<Sales> salesList){
        List<Items> itemsList=new ArrayList<>();
        for(Sales sales:salesList){
          itemsList=itemsService.findItemsBySalesId(sales.getId());
        }
        return itemsList;
    }
}
