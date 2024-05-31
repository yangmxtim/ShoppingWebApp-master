package com.shoppingwebapp.Model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class comment {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer comment_id;
	private Integer product_id;
	private Integer user_id;
	private String content;
	private String rate;
	
	
	public comment() {
		super();
	}
	public comment(Integer comment_id, Integer product_id, Integer user_id, String content, String rate,
			Date comment_date) {
		super();
		this.comment_id = comment_id;
		this.product_id = product_id;
		this.user_id = user_id;
		this.content = content;
		this.rate = rate;
		this.comment_date = comment_date;
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	private Date comment_date;


	@Override
	public String toString() {
		return "comment [comment_id=" + comment_id + ", product_id=" + product_id + ", user_id=" + user_id
				+ ", content=" + content + ", rate=" + rate + ", comment_date=" + comment_date + "]";
	}
	
	
}
