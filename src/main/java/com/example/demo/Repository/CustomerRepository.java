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
    public List<Customer> findCustomerByID(int id){
        String query="SELECt * FROM customer WHERE Id=?;";
        RowMapper<Customer> rm=new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.query(query,rm,id);
    }
    public void updateCustomer(Customer customer,int id){
        String query="UPDATE customer SET first_name=?,second_name=?,age=?,email=?,phone=?,zip=?,country=?,address=? WHERE Id=?;";
        jdbcTemplate.update(query,customer.getFirst_name(),customer.getSecond_name(),customer.getAge(), customer.getEmail(),customer.getPhone(),customer.getZip(),customer.getCountry(),customer.getAddress(),id);
    }
    public void addCustomer(Customer customer){
        String query="INSERT INTO customers VALUES(DEFAULT,?,?,?,?,?,?,?,?;)";
        jdbcTemplate.update(query,customer.getFirst_name(),customer.getSecond_name(),customer.getAge(), customer.getEmail(),customer.getPhone(),customer.getZip(),customer.getCountry(),customer.getAddress());
    }
    public void deleteCustomer(int id)
    {
        String query = "DELETE FROM customer WHERE Id=?;";
        jdbcTemplate.update(query,id);
    }
    public List<Customer> findCustomerByEmail(String email)
    {
        String query="SELECT * FROM customers WHERE email=?";
        RowMapper<Customer>rm=new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.query(query,rm,email);
    }
}
