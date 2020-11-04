package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Customer> fetchAllCustomers()
    {
        String query="SELECT * FROM customers";
        RowMapper<Customer> rm = new BeanPropertyRowMapper<>(Customer.class);
        System.out.println(jdbcTemplate.query(query,rm));
        return jdbcTemplate.query(query, rm);
    }
    public Customer findCustomerByID(int id) {
        try {
            String query = "SELECt * FROM customers WHERE id=?;";
            RowMapper<Customer> rm = new BeanPropertyRowMapper<>(Customer.class);
            return jdbcTemplate.queryForObject(query, rm, id);
        }
    catch (EmptyResultDataAccessException e)
    {
        return null;
    }
    }
    public boolean updateCustomer(Customer customer,int id){
        String query="UPDATE customers SET first_name=?,second_name=?,age=?,email=?,phone=?,zip=?,country=?,address=? WHERE id=?;";
    return  jdbcTemplate.update(query,customer.getFirst_name(),customer.getSecond_name(),customer.getAge(), customer.getEmail(),customer.getPhone(),customer.getZip(),customer.getCountry(),customer.getAddress(),id)>0;
    }
    public boolean addCustomer(Customer customer){
        String query="INSERT INTO customers VALUES(DEFAULT,?,?,?,?,?,?,?,?)";
       return jdbcTemplate.update(query,customer.getFirst_name(),customer.getSecond_name(),customer.getAge(), customer.getEmail(),customer.getPhone(),customer.getZip(),customer.getCountry(),customer.getAddress())>0;
    }
    public boolean deleteCustomer(int id)
    {
        String query = "DELETE FROM customers WHERE id=?;";
        return jdbcTemplate.update(query,id)>0;
    }
    public Customer findCustomerByEmail(String email) {
        try {

            String query = "SELECT * FROM customers WHERE email=?";
            RowMapper<Customer> rm = new BeanPropertyRowMapper<>(Customer.class);
            return jdbcTemplate.queryForObject(query, rm, email);
        }
    catch (EmptyResultDataAccessException e)
    {
        return null;
    }
    }
}
