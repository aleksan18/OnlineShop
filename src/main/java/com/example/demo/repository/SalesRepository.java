package com.example.demo.repository;

import com.example.demo.model.Sales;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Sales> findSalesById(int id){
        String query="SELECt * FROM sales WHERE Id=?;";
        RowMapper<Sales> rm=new BeanPropertyRowMapper<>(Sales.class);
        return jdbcTemplate.query(query,rm,id);
    }
    public void updateSales(Sales sales,int id){
        String query="UPDATE sales SET completed=?,customers_Id=?,final_price=? WHERE Id=?;";
        jdbcTemplate.update(query,sales.isCompleted(),sales.getCustomers_id(),sales.getFinal_price(),id);
    }
    public void addSales(Sales sales){
        String query="INSERT INTO sales VALUES(DEFAULT,?,?,?,?;)";
        jdbcTemplate.update(query,sales.isCompleted(),sales.getCustomers_id(),sales.getFinal_price());
    }
    public void deleteSales(int id)
    {
        String query = "DELETE FROM sales WHERE Id=?;";
        jdbcTemplate.update(query,id);
    }
    public List<Sales> findSalesByCustomerId(int customer_id)
    {
        String query="SELECT * FROM sales WHERE customer_Id=?";
        RowMapper<Sales>rm=new BeanPropertyRowMapper<>(Sales.class);
        return jdbcTemplate.query(query,rm,customer_id);
    }
}
