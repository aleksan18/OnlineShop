package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    private int Id;
    private String first_name;
    private String second_name;
    private int age;
    private String email;
    private String password;
    private String phone;
    private String zip;
    private String city;
    private String country;
    private String address;

    public Customer() {
    }

    public Customer(int id, String first_name, String second_name, int age, String email, String password, String phone, String zip, String city, String country, String address) {
        Id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.address = address;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + Id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Id == customer.Id &&
                age == customer.age &&
                Objects.equals(first_name, customer.first_name) &&
                Objects.equals(second_name, customer.second_name) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(zip, customer.zip) &&
                Objects.equals(city, customer.city) &&
                Objects.equals(country, customer.country) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, first_name, second_name, age, email, password, phone, zip, city, country, address);
    }
}
