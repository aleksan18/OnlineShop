package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Items;
import com.example.demo.model.Sales;
import com.example.demo.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class SalesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //find the sales for the customer
    public List<Sales> fetchAllSales()
    {
        String query="SELECT * FROM sales";
        RowMapper<Sales> rm = new BeanPropertyRowMapper<>(Sales.class);
        System.out.println(jdbcTemplate.query(query,rm));
        return jdbcTemplate.query(query, rm);
    }
    public Sales findSalesById(int id){
        try {
            String query = "SELECt * FROM sales WHERE id=?;";
            RowMapper<Sales> rm = new BeanPropertyRowMapper<>(Sales.class);
            return jdbcTemplate.queryForObject(query, rm, id);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }
    public boolean updateSales(Sales sales,int id){
        String query="UPDATE sales SET completed=?,customers_Id=?,final_price=? WHERE id=?;";
       return jdbcTemplate.update(query,sales.isCompleted(),sales.getCustomers_id(),sales.getFinal_price(),id)>0;
    }
    //method for creating
    public Sales createSales(Customer customer, double endPrice){
        String query="INSERT INTO sales VALUES(DEFAULT,?,?,?)";
        String find_last_id=  "SELECT * FROM sales WHERE id=LAST_INSERT_ID()";
        RowMapper<Sales> rm=new BeanPropertyRowMapper<>(Sales.class);
        jdbcTemplate.update(query,"1",customer.getId(),endPrice);
        return jdbcTemplate.queryForObject(find_last_id,rm);
    }
    public boolean addSales(Sales sales,Items items){
           String combine_tables=  "INSERT INTO sales_has_items VALUES (?,?,?,?)";
       return jdbcTemplate.update(combine_tables,sales.getId(),items.getId(),items.getQuantity(),items.getSize())>0;
    }
    public boolean deleteSales(int id)
    {
        String query = "DELETE FROM sales WHERE id=?;";
        String query2="DELETE FROM sales_has_items WHERE sales_id=?";
        jdbcTemplate.update(query2,id);
       return jdbcTemplate.update(query,id)>0;
    }
    public List<Sales> findSalesByCustomerId(int customer_id)
    {
        String query="SELECT * FROM sales WHERE customers_id=?";
        RowMapper<Sales>rm=new BeanPropertyRowMapper<>(Sales.class);
        return jdbcTemplate.query(query,rm,customer_id);
    }

}
