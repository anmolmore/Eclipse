package com.globom.product;


public class Product {
	Long id;
	String name;
	String description;
	Long categoryId;
	String uom;

	public Product(Long id, String name, String description, Long categoryId,
			String uom) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
		this.uom = uom;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public String toJson() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"").append("id").append("\"").append(":");
		sb.append(this.id).append(",");
		sb.append("\"").append("name").append("\"").append(":");
		sb.append("\"").append(this.name).append("\",");
		sb.append("\"").append("description").append("\"").append(":");
		sb.append("\"").append(this.description).append("\",");
		sb.append("\"").append("categoryId").append("\"").append(":");
		sb.append(this.categoryId).append(",");
		sb.append("\"").append("uom").append("\"").append(":");
		sb.append("\"").append(this.uom).append("\"");
		sb.append("}");
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
}
