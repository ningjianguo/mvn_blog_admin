package com.comm.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDaoImpl<T> implements IBaseDao<T> {
	protected Class<T> domainClass;
	
	@Resource
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) getClass()
		.getGenericSuperclass();

		this.domainClass = ((Class) type.getActualTypeArguments()[0]);
	}
	/**
	 * 查询实体对应的数据库保存信息
	 * @return 数据库所有信息
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryAllInfo(){
		return getSession().createQuery("from "+domainClass.getSimpleName()).list();
	}
	public void update(T t) {
		getSession().update(t);
	}

	public void save(T t) {
		getSession().save(t);
	}

	public void delete(T t) {
		getSession().delete(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getPaging(int pageNo, int pageSize,String queryLimit) {
		try {
			String sql = " from "+ domainClass.getSimpleName()+(queryLimit == null ? "":" "+queryLimit);
			Query query = getSession().createQuery(sql);  
			//设置起点  
			query.setFirstResult(pageSize*(pageNo-1));  
			//设置每页显示多少个，设置多大结果。  
			query.setMaxResults(pageSize);  
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 判断文件的状态
	 */
	/**
	 * 判断文件发布状态
	 */
	protected String releaseStatu(int num) {
		String temp = null;
		switch (num) {
		case 1:
			temp = "未发布";
			break;

		case 2:
			temp = "已发布";
			break;
		}
		return temp;
	}

}
