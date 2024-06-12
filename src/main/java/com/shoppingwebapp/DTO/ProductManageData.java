package com.shoppingwebapp.DTO;

public class ProductManageData {

	private Integer id;
	private String name;
	private String img;
	private Double minp;
	private Double maxp;
	private Double arate;
	private Integer comments;

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

	public Double getMinp() {
		return minp;
	}

	public void setMinp(Double minp) {
		this.minp = minp;
	}

	public Double getMaxp() {
		return maxp;
	}

	public void setMaxp(Double maxp) {
		this.maxp = maxp;
	}

	public Double getArate() {
		return arate;
	}

	public void setArate(Double arate) {
		this.arate = arate;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "ProductManageData [id=" + id + ", name=" + name + ", img=" + img + ", minp=" + minp + ", maxp=" + maxp
				+ ", arate=" + arate + ", comments=" + comments + "]";
	}
}
