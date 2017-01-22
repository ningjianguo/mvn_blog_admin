package com.entity;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer categoryId;
	private String categoryName;
	private Integer categoryParentid;
	private String categoryIcon;
	private String categoryUrl;

	public Category() {
	}

	public Category(String categoryName, Integer categoryParentid, String categoryIcon, String categoryTarget) {
		this.categoryName = categoryName;
		this.categoryParentid = categoryParentid;
		this.categoryIcon = categoryIcon;
	}

	public Category(String categoryName, Integer categoryParentid, String categoryIcon, String categoryUrl,
			String categoryTarget) {
		this.categoryName = categoryName;
		this.categoryParentid = categoryParentid;
		this.categoryIcon = categoryIcon;
		this.categoryUrl = categoryUrl;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryParentid() {
		return this.categoryParentid;
	}

	public void setCategoryParentid(Integer categoryParentid) {
		this.categoryParentid = categoryParentid;
	}

	public String getCategoryIcon() {
		return this.categoryIcon;
	}

	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}

	public String getCategoryUrl() {
		return this.categoryUrl;
	}

	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}
}
