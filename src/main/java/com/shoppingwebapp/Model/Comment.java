package com.shoppingwebapp.Model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer comment_id;
	
	private String content;
	
	private String rate;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date date;
	
	private String name;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

	public Comment() {
		super();
	}	

	public Comment(Integer comment_id, String content, String rate, Date date, String name, Product product) {
		super();
		this.comment_id = comment_id;
		this.content = content;
		this.rate = rate;
		this.date = date;
		this.name = name;
		this.product = product;
	}

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", content=" + content + ", rate=" + rate + ", date=" + date
				+ ", name=" + name + ", product=" + product + "]";
	}

	
	
	
}