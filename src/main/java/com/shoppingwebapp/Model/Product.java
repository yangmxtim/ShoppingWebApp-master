package com.shoppingwebapp.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private String name;
    private String img;
    private String address;
    private String type;
    private String phone;
    private String introduction;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product_detail> product_detail = new ArrayList<>();

    public Product() {
        super();
    }

    public Product(Integer product_id, String name, String img, String address, String type, String phone,
                   String introduction, List<Product_detail> product_detail) {
        super();
        this.product_id = product_id;
        this.name = name;
        this.img = img;
        this.address = address;
        this.type = type;
        this.phone = phone;
        this.introduction = introduction;
        this.product_detail = product_detail;
    }

    // Getters and setters

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<Product_detail> getProduct_detail() {
        return product_detail;
    }

    public void setProduct_detail(List<Product_detail> product_detail) {
        this.product_detail.clear();
        if (product_detail != null) {
            this.product_detail.addAll(product_detail);
        }
    }

    public void addProductDetail(Product_detail detail) {
        product_detail.add(detail);
        detail.setProduct(this);
    }

    public void removeProductDetail(Product_detail detail) {
        product_detail.remove(detail);
        detail.setProduct(null);
    }

    @Override
    public String toString() {
        return "Product [product_id=" + product_id + ", name=" + name + ", img=" + img + ", address=" + address
                + ", type=" + type + ", phone=" + phone + ", introduction=" + introduction + ", product_detail="
                + product_detail + "]";
    }
}
    