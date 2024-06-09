package com.shoppingwebapp.Model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Stock {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Date date;
	
	private Integer quantity;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_detail_id")
    private Product_detail product_detail;

	public Stock() {
		super();
	}

	public Stock(Integer id, Date date, Integer quantity, Product_detail product_detail) {
		super();
		this.id = id;
		this.date = date;
		this.quantity = quantity;
		this.product_detail = product_detail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product_detail getProduct_detail() {
		return product_detail;
	}

	public void setProduct_detail(Product_detail product_detail) {
		this.product_detail = product_detail;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", date=" + date + ", quantity=" + quantity + ", product_detail=" + product_detail
				+ "]";
	}
	
	
}
