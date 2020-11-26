package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Items {
    @Id
    private int Id;
    private int sales_id;
    private String size;
    private double price;
    private String name;
    private int stock;
    private String type;
    private int ratings;
    private int quantity;
    private String image;

    public Items() {
    }

    public Items(int id,int sales_id, String size, double price, String name, int stock, String type, int ratings, int quantity,String image) {
        Id = id;
        this.sales_id = sales_id;
        this.size = size;
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.type = type;
        this.ratings = ratings;
        this.quantity = quantity;
        this.image=image;
    }

    public int getSales_id() {
        return sales_id;
    }

    public void setSales_id(int sales_id) {
        this.sales_id = sales_id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
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
        Items items = (Items) o;
        return Id == items.Id &&
                sales_id == items.sales_id &&
                Double.compare(items.price, price) == 0 &&
                stock == items.stock &&
                ratings == items.ratings &&
                quantity == items.quantity &&
                Objects.equals(size, items.size) &&
                Objects.equals(name, items.name) &&
                Objects.equals(type, items.type) &&
                Objects.equals(image, items.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, sales_id, size, price, name, stock, type, ratings, quantity, image);
    }

    @Override
    public String toString() {
        return "Items{" +
                "Id=" + Id +
                ", sales_id=" + sales_id +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", type='" + type + '\'' +
                ", ratings=" + ratings +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                '}';
    }
}
