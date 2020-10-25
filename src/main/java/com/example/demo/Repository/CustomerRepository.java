package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository{


    JdbcTemplate jdbcTemplate;
    public List<Customer> fetchAllCustomers()
    {
        String query="SELECT * FROM customer";
        RowMapper<Customer> rm = new BeanPropertyRowMapper<>(Customer.class);
        System.out.println(jdbcTemplate.query(query,rm));
        return jdbcTemplate.query(query, rm);
    }
}
