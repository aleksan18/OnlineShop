package com.example.demo.repository;

import com.example.demo.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //add items based on sales ID
    public List<Items> fetchAllItems()
    {
        String query="SELECT * FROM items";
        RowMapper<Items> rm = new BeanPropertyRowMapper<>(Items.class);
        System.out.println(jdbcTemplate.query(query,rm));
        return jdbcTemplate.query(query, rm);
    }
    public Items findItemsByID(int id) {
        try {

            String query = "SELECt * FROM items WHERE id=?;";
            RowMapper<Items> rm = new BeanPropertyRowMapper<>(Items.class);
            return jdbcTemplate.queryForObject(query, rm, id);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }
    public Items findItemsBySalesID(int id) {
        try {
            String query = "SELECt * FROM items WHERE sales_id=?;";
            RowMapper<Items> rm = new BeanPropertyRowMapper<>(Items.class);
            return jdbcTemplate.queryForObject(query, rm, id);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }
    public boolean updateItems(Items items,int id){
        String query="UPDATE items SET size=?,price=?,name=?,stock=?,type=?,ratings=?,sales_id=?,quantity=? WHERE id=?;";
        return jdbcTemplate.update(query,items.getSize(),items.getPrice(),items.getName(), items.getStock(),items.getType(),items.getRatings(),items.getSales_id(),items.getQuantity(),id)>0;
    }
    public boolean addItem(Items items){
        String query="INSERT INTO items VALUES(DEFAULT,?,?,?,?,?,?,?,?)";
      return  jdbcTemplate.update(query,items.getSize(),items.getPrice(),items.getName(), items.getStock(),items.getType(),items.getRatings(),items.getSales_id(),items.getQuantity())>0;
    }
    public boolean deleteItem(int id)
    {
        String query = "DELETE FROM items WHERE Id=?;";
        return jdbcTemplate.update(query,id)>0;
    }
}
