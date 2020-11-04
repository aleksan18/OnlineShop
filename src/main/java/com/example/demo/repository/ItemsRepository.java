package com.example.demo.repository;

import com.example.demo.model.Items;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemsRepository {
JdbcTemplate jdbcTemplate;
    public List<Items> fetchAllItems()
    {
        String query="SELECT * FROM items";
        RowMapper<Items> rm = new BeanPropertyRowMapper<>(Items.class);
        System.out.println(jdbcTemplate.query(query,rm));
        return jdbcTemplate.query(query, rm);
    }
    public List<Items> findItemsByID(int id){
        String query="SELECt * FROM items WHERE Id=?;";
        RowMapper<Items> rm=new BeanPropertyRowMapper<>(Items.class);
        return jdbcTemplate.query(query,rm,id);
    }
    public void updateItems(Items items,int id){
        String query="UPDATE items SET size=?,price=?,name=?,stock=?,type=?,ratings=?,sales_id=?,quantity=? WHERE Id=?;";
        jdbcTemplate.update(query,items.getSize(),items.getPrice(),items.getName(), items.getStock(),items.getType(),items.getRatings(),items.getSales_id(),items.getQuantity(),id);
    }
    public void addItem(Items items){
        String query="INSERT INTO items VALUES(DEFAULT,?,?,?,?,?,?,?,?;)";
        jdbcTemplate.update(query,items.getSize(),items.getPrice(),items.getName(), items.getStock(),items.getType(),items.getRatings(),items.getSales_id(),items.getQuantity());
    }
    public void deleteItem(int id)
    {
        String query = "DELETE FROM items WHERE Id=?;";
        jdbcTemplate.update(query,id);
    }
}
