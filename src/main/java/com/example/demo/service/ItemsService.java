package com.example.demo.service;

import com.example.demo.model.Items;
import com.example.demo.model.ShoppingCart;
import com.example.demo.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemsService {
    @Autowired
    ItemsRepository itemsRepository;

   public List<Items> fetchAllItems() {

        List<Items> itemsList = itemsRepository.fetchAllItems();
            return itemsList;
    }
    public Page<Items> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Items> repoList=itemsRepository.fetchAllItems();
        List<Items> list;

        if (repoList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, repoList.size());
            list = repoList.subList(startItem, toIndex);
        }

        Page<Items> bookPage
                = new PageImpl<Items>(list, PageRequest.of(currentPage, pageSize), repoList.size());

        return bookPage;
    }
    public List<Items> findItemsByName(String name){ return  itemsRepository.findItemsByName(name);}
    public List<Items> findItemsByType(String type){return  itemsRepository.findItemsByTypes(type);}
    public Items findItemsByID(int id) {
        return itemsRepository.findItemsByID(id);
    }

    public void updateItems(Items items, int id) {
        itemsRepository.updateItems(items,id);
    }
    public void deleteItems(int id){
        itemsRepository.deleteItem(id);
    }
    public void stockChange(List<ShoppingCart> shoppingCartList){
        for(ShoppingCart shoppingCart:shoppingCartList){
            itemsRepository.stockChange(shoppingCart.getItems_id(),shoppingCart.getQuantity());
        }
    }
    public int getStock(int items_id){
        return itemsRepository.getStock(items_id);
    }
    public void addItems(Items items){
        itemsRepository.addItem(items);
    }
    public List<Items> findItemsBySalesId(int id){
        return itemsRepository.findItemsBySalesID(id);
    }
}
