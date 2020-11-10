package com.example.demo.controller;

import com.example.demo.model.Items;
import com.example.demo.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @GetMapping("/")
    public String fetchAllItems(Model model) {
        List<Items> itemsList = itemsService.fetchAllItems();
        model.addAttribute("items", itemsList);

        return "/index";
    }
    @GetMapping
    public Items findItemsByID(@PathVariable("id") int id) {
        return itemsService.findItemsByID(id);
    }
    @PutMapping(path = "{id}")
    public void updateItems(@RequestBody Items items, @PathVariable("id") int id){
        itemsService.updateItems(items,id);
    }
    @DeleteMapping(path="{id}")
    public void deleteItems(@PathVariable("id") int id){
        itemsService.deleteItems(id);
    }
    @PostMapping
    public void addItems(@RequestBody Items items){
        itemsService.addItems(items);
    }
}

