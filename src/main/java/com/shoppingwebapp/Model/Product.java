package com.shoppingwebapp.Model;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String address;

    private String type;

    private String price;

    @Lob
    private byte[] image;

    public Product() {}
    public Product(String name, String address, String type, String price, byte[] image) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.price = price;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}