package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Items;
import com.example.demo.model.ShoppingCart;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ItemsService;
import com.example.demo.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@SessionAttributes("listOfItems")
//as soon as I add an item to my shoppeing cart I create
public class HomeController {
/*
    @Autowired
    CustomerService customerService;
    @GetMapping({"","/","/index","/index.html"})
    public String index()
    {
        customerService.fetchAllCustomers();
        return "/index.html";
    }
*/
    @Autowired
    ItemsService itemsService;
    @Autowired
    SalesService salesService;
    @Autowired
    CustomerService customerService;

    List<ShoppingCart> shoppingCartList=new ArrayList<>();
    Customer customer;
    @GetMapping("/items")
    public ResponseEntity<Map<String,Object>> getAllItems(@RequestParam int page, @RequestParam int size){

        return new ResponseEntity<>(itemsService.fetchAllItems(page,size), HttpStatus.OK);
    }
    @GetMapping("/allitems")
    public String listItems(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Items> itemsPage = itemsService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("itemsPage", itemsPage);
        model.addAttribute("customer",customer);
        int totalPages = itemsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "allitems";
    }
    @GetMapping("/oneitem/{id}")
    public String oneItem(@PathVariable("id") int id,Model model)
    {

        model.addAttribute("oneItem",itemsService.findItemsByID(id));
        return "oneitem";
    }
    @PostMapping("/oneitem")
    public String addToBag(@ModelAttribute ShoppingCart shoppingCart)
    {
        shoppingCartList.add(new ShoppingCart(0,shoppingCart.getItems_id(),shoppingCart.getSize(),shoppingCart.getQuantity()));
        return  "redirect:/allitems";
    }
    @GetMapping("/shoppingcart")
    public String shoppingCart(Model model){
        model.addAttribute("listOfItems",shoppingCartList);
        return "shoppingcart";
    }
    @PostMapping("/buy")
    public String finishPurchase(@ModelAttribute Items items,@SessionAttribute("listOfItems") List<ShoppingCart> shoppingCartList){

   if(customer!=null) {
       salesService.createSales(shoppingCartList, customer);
       itemsService.stockChange(shoppingCartList);
   }
   else if(customer==null){
       return "redirect:/customerLogIn";
   }
    return "redirect:/allitems";
    }
    @GetMapping("/customerLogIn")
    public String logInPage(){
        return "customerLogIn";
    }
    @PostMapping("/logIn")
    public String logIn(@ModelAttribute("email") String email, @ModelAttribute("password")String password){
        if(password.equalsIgnoreCase(customerService.findCustomerByEmail(email).getPassword()))
        {
            customer=customerService.findCustomerByEmail(email);
            return "redirect:/allitems";
        }
        else if(customerService.findCustomerByEmail(email)==null){
            //check db if email exist if not then create the cust
            return "redirect:/customerCreation";
        }
       else return "redirect:/wrongLogIn";
    }
    @GetMapping("/customerCreation")
    public String createCustomer(){
        return "customerCreation";
    }
    @PostMapping("/makeCustomer")
    public String makeCustomer(@ModelAttribute Customer customer1)
    {
        customerService.addCustomer(customer1);
        return "redirect:/customerLogIn";
    }
    @GetMapping("/profile")
    public String customerProfile(Model model) {
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "profile";
        }
        else return "customerLogIn";
    }
    @GetMapping("/orders")
    public String customerOrders(Model model){
        model.addAttribute("orders",salesService.findSalesByCustomerId(customer));
        model.addAttribute("orders_info",salesService.findItemsBoughtByCustomer(salesService.findSalesByCustomerId(customer)));
        return "orders";
    }
}
