package com.shoppingwebapp.Model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class form {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer form_id;
	private String name;
	private String email;
	private String topic;
	private String content;
	private Date finish_time;
	private String status;
	
	
	public form() {
		super();
	}
	public form(Integer form_id, String name, String email, String topic, String content, Date finish_time,
			String status) {
		super();
		this.form_id = form_id;
		this.name = name;
		this.email = email;
		this.topic = topic;
		this.content = content;
		this.finish_time = finish_time;
		this.status = status;
	}
	public Integer getForm_id() {
		return form_id;
	}
	public void setForm_id(Integer form_id) {
		this.form_id = form_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "form [form_id=" + form_id + ", name=" + name + ", email=" + email + ", topic=" + topic + ", content="
				+ content + ", finish_time=" + finish_time + ", status=" + status + "]";
	}
	
	
}
