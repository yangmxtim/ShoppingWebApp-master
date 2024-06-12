package com.shoppingwebapp.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private String name;
    private String img;
    private String address;
    private String phone;
    private String type;
    private String tag; 
    private String introduction;
    private String facility;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonBackReference
	private List<Product_detail> product_detail = new ArrayList<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private List<Comment> comment = new ArrayList<>();

    public Product() {
        super();
    }



	public Product(Integer product_id, String name, String img, String address, String phone, String type, String tag, String introduction, String facility) {
		this.product_id = product_id;
		this.name = name;
		this.img = img;
		this.address = address;
		this.phone = phone;
		this.type = type;
		this.tag = tag;
		this.introduction = introduction;
		this.facility = facility;
	}
//public Product(Integer product_id, String name, String img, String address, String phone, String type, String tag,
//			   String introduction, String facility, List<Product_detail> product_detail, List<Comment> comment) {
//	super();
//	this.product_id = product_id;
//	this.name = name;
//	this.img = img;
//	this.address = address;
//	this.phone = phone;
//	this.type = type;
//	this.tag = tag;
//	this.introduction = introduction;
//	this.facility = facility;
//	this.product_detail = product_detail;
//	this.comment = comment;
//}
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public List<Product_detail> getProduct_detail() {
		return product_detail;
	}

	public void setProduct_detail(List<Product_detail> product_detail) {
		this.product_detail = product_detail;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}



	@Override
	public String toString() {
		return "Product{" +
				"facility='" + facility + '\'' +
				", introduction='" + introduction + '\'' +
				", tag='" + tag + '\'' +
				", type='" + type + '\'' +
				", phone='" + phone + '\'' +
				", address='" + address + '\'' +
				", img='" + img + '\'' +
				", name='" + name + '\'' +
				", product_id=" + product_id +
				'}';
	}
}