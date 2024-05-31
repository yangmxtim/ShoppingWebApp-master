package com.shoppingwebapp.Model;
import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class Product_detail {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer product_detail_id;
	
	private Integer product_id;
	
	private String tag;
	
	private Integer price;
	
	private Integer quantity;
	
	private String departure_location;
	
	private String destination_location;
	
	private Date departure_time;
	
	private Date arrive_time;
	
	

	public Product_detail() {
		super();
	}

	public Product_detail(Integer product_detail_id, Integer product_id, String tag, Integer price, Integer quantity,
			String departure_location, String destination_location, Date departure_time, Date arrive_time) {
		super();
		this.product_detail_id = product_detail_id;
		this.product_id = product_id;
		this.tag = tag;
		this.price = price;
		this.quantity = quantity;
		this.departure_location = departure_location;
		this.destination_location = destination_location;
		this.departure_time = departure_time;
		this.arrive_time = arrive_time;
	}

	public Integer getProduct_detail_id() {
		return product_detail_id;
	}

	public void setProduct_detail_id(Integer product_detail_id) {
		this.product_detail_id = product_detail_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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

	@Override
	public String toString() {
		return "Product_detail [product_detail_id=" + product_detail_id + ", product_id=" + product_id + ", tag=" + tag
				+ ", price=" + price + ", quantity=" + quantity + ", departure_location=" + departure_location
				+ ", destination_location=" + destination_location + ", departure_time=" + departure_time
				+ ", arrive_time=" + arrive_time + "]";
	}
	
	
}
