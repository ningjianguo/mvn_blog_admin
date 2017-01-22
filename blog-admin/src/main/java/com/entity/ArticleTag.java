package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * ArticleTag entity. @author MyEclipse Persistence Tools
 */

public class ArticleTag implements java.io.Serializable {

	// Fields

	private Integer articleTagId;
	private String articleTagName;
	private Set articles = new HashSet(0);

	// Constructors

	/** default constructor */
	public ArticleTag() {
	}

	/** minimal constructor */
	public ArticleTag(String articleTagName) {
		this.articleTagName = articleTagName;
	}

	/** full constructor */
	public ArticleTag(String articleTagName, Set articles) {
		this.articleTagName = articleTagName;
		this.articles = articles;
	}

	// Property accessors

	public Integer getArticleTagId() {
		return this.articleTagId;
	}

	public void setArticleTagId(Integer articleTagId) {
		this.articleTagId = articleTagId;
	}

	public String getArticleTagName() {
		return this.articleTagName;
	}

	public void setArticleTagName(String articleTagName) {
		this.articleTagName = articleTagName;
	}

	public Set getArticles() {
		return this.articles;
	}

	public void setArticles(Set articles) {
		this.articles = articles;
	}

}