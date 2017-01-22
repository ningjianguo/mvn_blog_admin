package com.dao;

import java.util.List;

import com.entity.Article;
import com.entity.ArticleTag;

/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月24日 下午10:56:25
	@email:1195726908@qq.com
	@version:v1.0
	@description:博文接口
 */
public interface IArticle {
	
	/**
	 * 获得所有博文的标签
	 * @return	博文标签集合
	 */
	public List<ArticleTag> getAllArticleTag();
	
	/**
	 * 删除文章
	 */
	public int deleteArticle(int articleId);
	
	/**
	 * 更新文章
	 */
	public int updateArticle(Article article);
	
}


