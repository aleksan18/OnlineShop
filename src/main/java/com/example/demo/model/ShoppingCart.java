package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ShoppingCart {
    @Id
    private int sales_id;
    private int items_id;
    private String size;
    private int quantity;

    public ShoppingCart() {
    }

    public ShoppingCart(int sales_id, int items_id, String size, int quantity) {
        this.sales_id = sales_id;
        this.items_id = items_id;
        this.size = size;
        this.quantity = quantity;
    }

    public int getSales_id() {
        return sales_id;
    }

    public void setSales_id(int sales_id) {
        this.sales_id = sales_id;
    }

    public int getItems_id() {
        return items_id;
    }

    public void setItems_id(int items_id) {
        this.items_id = items_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return sales_id == that.sales_id &&
                items_id == that.items_id &&
                Objects.equals(size, that.size) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sales_id, items_id, size, quantity);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "sales_id=" + sales_id +
                ", items_id=" + items_id +
                ", size='" + size + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
