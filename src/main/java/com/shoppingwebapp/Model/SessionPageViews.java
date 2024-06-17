package com.shoppingwebapp.Model;


import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SessionPageViews {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String session_id;
	
	private String to_path;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date timestamp;
	
	private String month;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getTo_path() {
		return to_path;
	}

	public void setTo_path(String to_path) {
		this.to_path = to_path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	} 
}
