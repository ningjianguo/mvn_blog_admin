package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.comm.dao.BaseDaoImpl;
import com.dao.IArticle;
import com.entity.Article;
import com.entity.ArticleTag;
import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.service.IArticleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月24日 下午11:13:55	
	@email:1195726908@qq.com
	@version:v1.0
	@description:博文业务层实现类
 */
@Service
public class ArticleServiceImpl extends BaseDaoImpl<Article> implements IArticleService {
	@Resource
	private IArticle articleDaoImpl;
	private JSONObject jobj;
	
	@Override
	public List<ArticleTag> getAllArticleTag() {
		return articleDaoImpl.getAllArticleTag();
	}
	
	@Override
	public void saveArticle(Article article) {
		Map session = ActionContext.getContext().getSession();
		User admin = (User)session.get("admin");
		article.setArticleStatu(2);
		article.setArticleReaderCount(0);
		article.setArticleTime(new Date());
		article.setArticleEditer(admin.getUserName());
	try {
			String tagName = article.getArticleTag().getArticleTagName();
			if(tagName!=null){
				if(!getAllArticleTag().contains(tagName)){
					ArticleTag tag = new ArticleTag();
					tag.setArticleTagName(tagName);
					getSession().save(tag);
					article.getArticleTag().setArticleTagId(getMaxTagId());
				}
			}
			save(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取文章标签的最大id值
	 */
	private int getMaxTagId(){
		Query query = getSession().createSQLQuery("select max(article_tag_id) from article_tag");
		Object obj = query.list().get(0);
		return Integer.valueOf(obj.toString());
	}

	@Override
	public String getAllArticle(int pageNo, int pageSize) {
		List<Article> articles = getPaging(pageNo,pageSize,"order by articleId desc");
		Map<String, Object> maps = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			for (Article article : articles) {
				maps = new HashMap<String, Object>();
				maps.put("articleId", article.getArticleId());
				maps.put("articleTitle", article.getArticleTitle());
				maps.put("articleTag.articleTagName", article.getArticleTag().getArticleTagName());
				maps.put("articleType", articleType(article.getArticleType()));
				maps.put("articleEditer", article.getArticleEditer());
				maps.put("articleTime", new SimpleDateFormat("yyyy-MM-dd").format(article.getArticleTime()));
				maps.put("articleStatu", releaseStatu(article.getArticleStatu().intValue()));
				maps.put("articleReaderCount", article.getArticleReaderCount().intValue());
				list.add(maps);
			}
			jobj = new JSONObject();
            jobj.accumulate("total",getArticleTotalSize());//total代表一共有多少数据  
            jobj.accumulate("rows", list);//row是代表显示的页的数据  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobj.toString();
	}

	@Override
	public int getArticleTotalSize() {
		return queryAllInfo().size();
	}
	
	/**
	 * 判断文章类型
	 */
	private String articleType(int num){
		String temp = null;
		switch (num) {
		case 1:
			temp = ARTICLE_SOURCE_SELF;
			break;

		case 2:
			temp = ARTICLE_SOURCE_OTHER;
			break;
			
		case 3:
			temp = ARTICLE_SOURCE_TRANSLATE;
			break;
		}
		return temp;
	}
	
	@Override
	public String getAllTag() {
		List<ArticleTag> tags = articleDaoImpl.getAllArticleTag();
		Map<String,Object> maps = null;
		List<Map<String,Object>> tagList = new ArrayList<Map<String,Object>>();
		for (ArticleTag articleTag : tags) {
			maps = new HashMap<String, Object>();
			maps.put("tagId", articleTag.getArticleTagId());
			maps.put("tagName", articleTag.getArticleTagName());
			tagList.add(maps);
		}
		return JSONArray.fromObject(tagList).toString();
	}

	@Override
	public String getAllTagType() {
		Map<String,Object> type1 = new HashMap<String, Object>();
		Map<String,Object> type2 = new HashMap<String, Object>();
		Map<String,Object> type3 = new HashMap<String, Object>();
		List<Map<String,Object>> typeList = new ArrayList<Map<String,Object>>();
		type1.put("typeName", ARTICLE_SOURCE_SELF);
		type1.put("typeId", 1);
		type2.put("typeName", ARTICLE_SOURCE_OTHER);
		type2.put("typeId", 2);
		type3.put("typeName", ARTICLE_SOURCE_TRANSLATE);
		type3.put("typeId", 3);
		typeList.add(type1);
		typeList.add(type2);
		typeList.add(type3);
		return JSONArray.fromObject(typeList).toString();
	}

	@Override
	public String getAllTagStatu() {
		Map<String,Object> statu1 = new HashMap<String, Object>();
		Map<String,Object> statu2 = new HashMap<String, Object>();
		List<Map<String,Object>> statuList = new ArrayList<Map<String,Object>>();
		statu1.put("statuName", FILE_STATU_RELEASE);
		statu1.put("statuId", 2);
		statu2.put("statuName", FILE_STATU_NORELEASE);
		statu2.put("statuId", 1);
		statuList.add(statu1);
		statuList.add(statu2);
		return JSONArray.fromObject(statuList).toString();
	}
	
	@Override
	public String updateArticle(Article article) {
		return JSONArray.fromObject(articleDaoImpl.updateArticle(article)).toString();
	}

	@Override
	public String deleteArticle(Article article) {
		jobj = new JSONObject();
		jobj.accumulate("success",articleDaoImpl.deleteArticle(article.getArticleId()));
		return jobj.toString();
	}

}


