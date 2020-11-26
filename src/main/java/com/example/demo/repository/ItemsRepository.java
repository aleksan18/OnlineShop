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
    public List<Items> fetchAllItems()
    {
        String query="SELECT * FROM items";
        RowMapper<Items> rm = new BeanPropertyRowMapper<>(Items.class);
        return jdbcTemplate.query(query, rm);
    }
    public List<Items> findItemsByName(String name){
        name=name.concat("%");
        String end="%";
        end=end.concat(name);
    String query="SELECT * FROM items WHERE name LIKE ?";
    RowMapper<Items> rm= new BeanPropertyRowMapper<>(Items.class);
    return jdbcTemplate.query(query,rm,end);
    }
    public List<Items> findItemsByTypes(String type){
        type=type.concat("%");
        String end="%";
        end=end.concat(type);
        String query="SELECT * FROM items WHERE type LIKE ?";
        RowMapper<Items> rm= new BeanPropertyRowMapper<>(Items.class);
        return jdbcTemplate.query(query,rm,type);
    }
    public Items findItemsByID(int id) {
            String query = "SELECT * FROM items WHERE id=?;";
            RowMapper<Items> rm = new BeanPropertyRowMapper<>(Items.class);
            return jdbcTemplate.queryForObject(query, rm, id);

    }
    public List<Items> findItemsBySalesID(int id) {
        try {
            String query = "SELECT items.*,sales_has_items.sales_id,sales_has_items.quantity FROM items JOIN sales_has_items ON sales_has_items.items_id=items.id WHERE sales_id=?;";
            RowMapper<Items> rm = new BeanPropertyRowMapper<>(Items.class);
            return  jdbcTemplate.query(query,rm,id);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }
    public boolean updateItems(Items items,int id){
        String query="UPDATE items SET size=?,price=?,name=?,stock=?,type=?,ratings=?,image=? WHERE id=?;";
        return jdbcTemplate.update(query,items.getSize(),items.getPrice(),items.getName(), items.getStock(),items.getType(),items.getRatings(),items.getSales_id(),items.getQuantity(),id)>0;
    }
    public boolean addItem(Items items){
        String query="INSERT INTO items VALUES(DEFAULT,?,?,?,?,?,?,?)";
      return  jdbcTemplate.update(query,items.getSize(),items.getPrice(),items.getName(), items.getStock(),items.getType(),items.getRatings(),items.getImage())>0;
    }
    public boolean deleteItem(int id)
    {
        String query = "DELETE FROM items WHERE id=?;";
        String query2="DELETE FROM sales_has_items WHERE items_id=?";
        jdbcTemplate.update(query2,id);
        return jdbcTemplate.update(query,id)>0;
    }
    public int getStock(int items_id){
      String query="SELECT items.stock WHERE id=?";
      RowMapper<Integer> rm= new BeanPropertyRowMapper<>(Integer.class);
      return jdbcTemplate.queryForObject(query,rm,items_id);
    }
    public boolean stockChange(int items_id,int quantity){
        String query="UPDATE items SET stock=stock-? WHERE id=?";
        return jdbcTemplate.update(query,quantity,items_id)>0;
    }
}
