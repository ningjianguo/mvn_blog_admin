package com.service;

import java.util.List;

import com.entity.Article;
import com.entity.ArticleTag;

/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月24日 下午11:10:46
	@email:1195726908@qq.com
	@version:v1.0
	@description:博文业务层接口
 */
public interface IArticleService {
	
	/**
	 * 文章出处类型
	 * @param ARTICLE_SOURCE_SELF 原创
	 */
	final static String ARTICLE_SOURCE_SELF = "原创";
	
	/**
	 * 文章出处类型
	 * @param ARTICLE_SOURCE_OTHER 转载
	 */
	final static String ARTICLE_SOURCE_OTHER = "转载";
	
	/**
	 * 文章出处类型
	 * @param ARTICLE_SOURCE_TRANSLATE 翻译
	 */
	final static String ARTICLE_SOURCE_TRANSLATE = "翻译";
	/**
	 * 获得所有博文的标签
	 */
	public List<ArticleTag> getAllArticleTag();
	
	/**
	 * 获得所有博文的标签(json格式)
	 */
	public String getAllTag();
	/**
	 * 获得所有博文的类型(json格式)
	 */
	public String getAllTagType();
	/**
	 * 获得所有博文的状态(json格式)
	 */
	public String getAllTagStatu();
	/**
	 * 保存博文
	 * @return true(保存成功)、false(保存失败)
	 */
	public Boolean saveArticle(Article article);
	
	/**
	 * @param pageNo	第几页
	 * @param pageSize	每页显示数量
	 * 返回所有博文(json格式)
	 */
	public String getAllArticle(int pageNo, int pageSize);
	
	/**
	 * 返回文章总数
	 */
	public int getArticleTotalSize();
	
	/**
	 * 更新一篇文章
	 */
	public String updateArticle(Article article);
	
	/**
	 * 删除一篇文章
	 */
	public String deleteArticle(Article article);
	
}


