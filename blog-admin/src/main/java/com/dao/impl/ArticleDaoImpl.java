package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.comm.dao.BaseDaoImpl;
import com.dao.IArticle;
import com.entity.Article;
import com.entity.ArticleTag;

/*
 @project:ningjianguo
 @author:Techape
 @date:2016年12月24日 下午11:02:02
 @email:1195726908@qq.com
 @version:v1.0
 @description:博文接口实现类
 */
@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements IArticle {

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleTag> getAllArticleTag() {
		Query query = getSession().createQuery("from ArticleTag");
		return query.list();
	}

	@Override
	public int deleteArticle(int articleId) {
		try {
			Article article = (Article) getSession().get(Article.class, articleId);
			getSession().delete(article);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateArticle(Article article) {
		int count = 0;
		Session session = getSession();
		try {
			int tagId = queryTagId(article.getArticleTag().getArticleTagName());
			Article updateArticle = (Article) session.get(Article.class,article.getArticleId());
			updateArticle.setArticleTitle(article.getArticleTitle());
			updateArticle.getArticleTag().setArticleTagId(tagId);
			updateArticle.setArticleType(article.getArticleType());
			updateArticle.setArticleStatu(article.getArticleStatu());
			session.clear();//清除缓存，避免缓存干扰数据的更新
			session.update(updateArticle);
			session.flush();//必须刷新才能更新到数据库,否则只是更新缓存
			count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 根据标签名取得标签id
	 * @param tagName 标签名
	 * @return 标签ID
	 */
	private int queryTagId(String tagName){
		ArticleTag tag = null;
		try {
			Query query = getSession().createQuery("from ArticleTag where articleTagName=:tagName").setParameter("tagName", tagName);
			tag =  (ArticleTag) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag.getArticleTagId();
	}
}
