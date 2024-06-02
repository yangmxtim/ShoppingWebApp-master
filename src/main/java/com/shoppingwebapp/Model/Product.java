package com.shoppingwebapp.Model;

import java.util.List;

import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer product_id;

    private String name;
    
    private String img;

    private String address;

    private String type;

    private String introduction;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product_detail> product_detail;
    
    

	public Product() {
		super();
	}

	public Product(Integer product_id, String name, String address, String type, String introduction) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.address = address;
		this.type = type;
		this.introduction = introduction;
	}

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
		this.product_detail = product_detail;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", name=" + name + ", address=" + address + ", type=" + type
				+ ", introduction=" + introduction + "]";
	}
	
	
}
    