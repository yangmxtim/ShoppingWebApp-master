package com.shoppingwebapp.Model;

import jakarta.persistence.*;

@Entity
public class Img {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String url;
	
	private Integer product_id;
	
	

	public Img() {
		super();
	}

	public Img(Integer id, String name, String url, Integer product_id) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.product_id = product_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "Img [id=" + id + ", name=" + name + ", url=" + url + ", product_id=" + product_id + "]";
	}
	
	
}
