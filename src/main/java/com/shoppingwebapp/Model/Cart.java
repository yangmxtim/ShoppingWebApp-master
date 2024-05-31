package com.shoppingwebapp.Model;

import jakarta.persistence.*;

@Entity
public class Cart {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cart_item_id;
	
	private Integer user_id;
	
	private Integer product_detail_id;
	
	private Integer quantity;

	

	public Cart() {
		super();
	}

	public Cart(Integer cart_item_id, Integer user_id, Integer product_detail_id, Integer quantity) {
		super();
		this.cart_item_id = cart_item_id;
		this.user_id = user_id;
		this.product_detail_id = product_detail_id;
		this.quantity = quantity;
	}

	public Integer getCart_item_id() {
		return cart_item_id;
	}

	public void setCart_item_id(Integer cart_item_id) {
		this.cart_item_id = cart_item_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getProduct_detail_id() {
		return product_detail_id;
	}

	public void setProduct_detail_id(Integer product_detail_id) {
		this.product_detail_id = product_detail_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Cart [cart_item_id=" + cart_item_id + ", user_id=" + user_id + ", product_detail_id="
				+ product_detail_id + ", quantity=" + quantity + "]";
	}

	
}
