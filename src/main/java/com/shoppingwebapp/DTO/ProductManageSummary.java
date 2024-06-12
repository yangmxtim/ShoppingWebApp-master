package com.shoppingwebapp.DTO;

public class ProductManageSummary {
	
	private Integer id;
	private String name;
	private String img;
	private Double minPrice;
	private Double maxPrice;
	private String type;
	private Double avgRate;
	private Integer commentCount;

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(Double avgRate) {
		this.avgRate = avgRate;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "ProductManageSummary [id=" + id + ", name=" + name + ", img=" + img + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + ", avgRate=" + avgRate + ", commentCount=" + commentCount + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
