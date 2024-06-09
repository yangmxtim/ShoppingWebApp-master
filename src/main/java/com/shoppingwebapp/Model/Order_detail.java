package com.shoppingwebapp.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Order_detail{

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer order_id;
	
	private Date order_date;
	
	private Integer total_amount;
	
	private String tradedesc;
	
	private String payment_method;
	
	private String payment_status;
	
	private Date payment_date;
	
	private String order_status;
	
	private String contact_name;
	
	private String contact_phone;
	
	private String contact_email;
	
	@OneToMany(mappedBy = "order_detail", cascade = CascadeType.ALL)
    private List<Orderitem> orderitem = new ArrayList<>();
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Member member;

	public Order_detail() {
		super();
	}

	public Order_detail(Integer order_id, Date order_date, Integer total_amount, String tradedesc,
			String payment_method, String payment_status, Date payment_date, String order_status, String contact_name,
			String contact_phone, String contact_email, List<Orderitem> orderitem, Member member) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
		this.total_amount = total_amount;
		this.tradedesc = tradedesc;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.payment_date = payment_date;
		this.order_status = order_status;
		this.contact_name = contact_name;
		this.contact_phone = contact_phone;
		this.contact_email = contact_email;
		this.orderitem = orderitem;
		this.member = member;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Integer getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}

	public String getTradedesc() {
		return tradedesc;
	}

	public void setTradedesc(String tradedesc) {
		this.tradedesc = tradedesc;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public List<Orderitem> getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(List<Orderitem> orderitem) {
		this.orderitem = orderitem;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Order_detail [order_id=" + order_id + ", order_date=" + order_date + ", total_amount=" + total_amount
				+ ", tradedesc=" + tradedesc + ", payment_method=" + payment_method + ", payment_status="
				+ payment_status + ", payment_date=" + payment_date + ", order_status=" + order_status
				+ ", contact_name=" + contact_name + ", contact_phone=" + contact_phone + ", contact_email="
				+ contact_email + ", orderitem=" + orderitem + ", member=" + member + "]";
	}

	
}
