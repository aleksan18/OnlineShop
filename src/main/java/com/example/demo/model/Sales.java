package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Sales {
    @Id
    private int Id;
    private boolean completed;
    private int customers_id;
    private double final_price;

    public Sales() {
    }

    public Sales(int id, boolean completed, int customers_id, double final_price) {
        Id = id;
        this.completed = completed;
        this.customers_id = customers_id;
        this.final_price = final_price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(int customers_id) {
        this.customers_id = customers_id;
    }

    public double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(double final_price) {
        this.final_price = final_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return Id == sales.Id &&
                completed == sales.completed &&
                customers_id == sales.customers_id &&
                Double.compare(sales.final_price, final_price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, completed, customers_id, final_price);
    }

    @Override
    public String toString() {
        return "Sales{" +
                "Id=" + Id +
                ", completed=" + completed +
                ", customers_id=" + customers_id +
                ", final_price=" + final_price +
                '}';
    }
}
