package com.example.demo.repository;

import com.example.demo.model.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
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
    public boolean addSales(Sales sales){
        String query="INSERT INTO sales VALUES(DEFAULT,?,?,?)";
       return jdbcTemplate.update(query,sales.isCompleted(),sales.getCustomers_id(),sales.getFinal_price())>0;
    }
    public boolean deleteSales(int id)
    {
        String query = "DELETE FROM sales WHERE id=?;";
       return jdbcTemplate.update(query,id)>0;
    }
    public List<Sales> findSalesByCustomerId(int customer_id)
    {
        String query="SELECT * FROM sales WHERE customers_id=?";
        RowMapper<Sales>rm=new BeanPropertyRowMapper<>(Sales.class);
        return jdbcTemplate.query(query,rm,customer_id);
    }
}
