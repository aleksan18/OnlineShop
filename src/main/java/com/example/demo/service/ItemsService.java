package com.example.demo.service;

import com.example.demo.model.Items;
import com.example.demo.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {
    @Autowired
    ItemsRepository itemsRepository;

    public List<Items> fetchAllItems() {
        return itemsRepository.fetchAllItems();
    }

    public Items findItemsByID(int id) {
        return itemsRepository.findItemsByID(id);
    }

    public void updateItems(Items items, int id) {
        itemsRepository.updateItems(items,id);
    }
    public void deleteItems(int id){
        itemsRepository.deleteItem(id);
    }
    public void addItems(Items items){
        itemsRepository.addItem(items);
    }
}
