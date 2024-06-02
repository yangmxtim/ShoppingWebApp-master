package com.shoppingwebapp.Model;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Product_detail {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer product_detail_id;
	
	private String tag;
	
	private Integer price;
	
	private Integer quantity;
	
	private String departure_location;
	
	private String destination_location;
	
	private Date departure_time;
	
	private Date arrive_time;
	
	private String img;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
	
	

	public Product_detail() {
		super();
	}

	public Product_detail(Integer product_detail_id, String tag, Integer price, Integer quantity,
			String departure_location, String destination_location, Date departure_time, Date arrive_time, String img,
			Product product) {
		super();
		this.product_detail_id = product_detail_id;
		this.tag = tag;
		this.price = price;
		this.quantity = quantity;
		this.departure_location = departure_location;
		this.destination_location = destination_location;
		this.departure_time = departure_time;
		this.arrive_time = arrive_time;
		this.img = img;
		this.product = product;
	}


	public Integer getProduct_detail_id() {
		return product_detail_id;
	}

	public void setProduct_detail_id(Integer product_detail_id) {
		this.product_detail_id = product_detail_id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDeparture_location() {
		return departure_location;
	}

	public void setDeparture_location(String departure_location) {
		this.departure_location = departure_location;
	}

	public String getDestination_location() {
		return destination_location;
	}

	public void setDestination_location(String destination_location) {
		this.destination_location = destination_location;
	}

	public Date getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}

	public Date getArrive_time() {
		return arrive_time;
	}

	public void setArrive_time(Date arrive_time) {
		this.arrive_time = arrive_time;
	}
	
	

	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}

	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	@Override
	public String toString() {
		return "Product_detail [product_detail_id=" + product_detail_id + ", tag=" + tag + ", price=" + price
				+ ", quantity=" + quantity + ", departure_location=" + departure_location + ", destination_location="
				+ destination_location + ", departure_time=" + departure_time + ", arrive_time=" + arrive_time
				+ ", img=" + img + ", product=" + product + "]";
	}


	
	
	
}
