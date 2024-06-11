package com.shoppingwebapp.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Product_detail {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer product_detail_id;
	
	private String name;
	
	private String img;
	
	private Integer price;
	
	private String introduction;
	
	private String specification;
	
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
//	@JsonBackReference
	private Product product;
	
	@OneToMany(mappedBy = "product_detail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stock = new ArrayList<>();

	public Product_detail() {
		super();
	}
	
//	public Product_detail(Integer product_detail_id, String name, String img, Integer price,
//			String introduction, String specification, Product product) {
//		super();
//		this.product_detail_id = product_detail_id;
//		this.name = name;
//		this.img = img;
//		this.price = price;
//		this.introduction = introduction;
//		this.specification = specification;
//		this.product = product;
//	}


	public Product_detail(Integer product_detail_id, String name, String img, Integer price, String introduction, String specification) {
		this.product_detail_id = product_detail_id;
		this.name = name;
		this.img = img;
		this.price = price;
		this.introduction = introduction;
		this.specification = specification;
	}

	public Integer getProduct_detail_id() {
		return product_detail_id;
	}

	public void setProduct_detail_id(Integer product_detail_id) {
		this.product_detail_id = product_detail_id;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Stock> getStock() {
		return stock;
	}

	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}

//	@Override
//	public String toString() {
//		return "Product_detail [product_detail_id=" + product_detail_id + ", name=" + name + ", img=" + img + ", price="
//				+ price +  ", introduction=" + introduction + ", specification="
//				+ specification + ", product=" + product + "]";
//	}

	@Override
	public String toString() {
		return "Product_detail{" +
				"product_detail_id=" + product_detail_id +
				", name='" + name + '\'' +
				", img='" + img + '\'' +
				", price=" + price +
				", introduction='" + introduction + '\'' +
				", specification='" + specification + '\'' +
				'}';
	}
}
