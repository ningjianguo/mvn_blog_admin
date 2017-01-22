package com.entity;

import java.util.Date;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */

public class Article implements java.io.Serializable {

	// Fields

	private Integer articleId;
	private ArticleTag articleTag;
	private String articleEditer;
	private String articleTitle;
	private String articleText;
	private Date articleTime;
	private Integer articleStatu;
	private Integer articleReaderCount;
	private Integer articleType;

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** full constructor */
	public Article(ArticleTag articleTag, String articleEditer,
			String articleTitle, String articleText, Date articleTime,
			Integer articleStatu, Integer articleReaderCount,
			Integer articleType) {
		this.articleTag = articleTag;
		this.articleEditer = articleEditer;
		this.articleTitle = articleTitle;
		this.articleText = articleText;
		this.articleTime = articleTime;
		this.articleStatu = articleStatu;
		this.articleReaderCount = articleReaderCount;
		this.articleType = articleType;
	}

	// Property accessors

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public ArticleTag getArticleTag() {
		return this.articleTag;
	}

	public void setArticleTag(ArticleTag articleTag) {
		this.articleTag = articleTag;
	}

	public String getArticleEditer() {
		return this.articleEditer;
	}

	public void setArticleEditer(String articleEditer) {
		this.articleEditer = articleEditer;
	}

	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleText() {
		return this.articleText;
	}

	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}

	public Date getArticleTime() {
		return this.articleTime;
	}

	public void setArticleTime(Date articleTime) {
		this.articleTime = articleTime;
	}

	public Integer getArticleStatu() {
		return this.articleStatu;
	}

	public void setArticleStatu(Integer articleStatu) {
		this.articleStatu = articleStatu;
	}

	public Integer getArticleReaderCount() {
		return this.articleReaderCount;
	}

	public void setArticleReaderCount(Integer articleReaderCount) {
		this.articleReaderCount = articleReaderCount;
	}

	public Integer getArticleType() {
		return this.articleType;
	}

	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}

}