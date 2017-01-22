package com.comm.dao;

import java.util.List;

public abstract interface IBaseDao<T> {
	/**
	 * @param FILE_SPLIT_SIGN  文件存储时的格式分隔符
	 */
	final static String FILE_SPLIT_SIGN = "_blog_";
	
	/**
	 * 文件发布状态
	 * @param FILE_STATU_RELEASE 已发布
	 */
	final static String FILE_STATU_RELEASE = "已发布";
	
	/**
	 * 文件发布状态
	 * @param FILE_STATU_NORELEASE 未发布
	 */
	final static String FILE_STATU_NORELEASE = "未发布";
	
	public abstract void update(T paramT);

	public abstract void save(T paramT);

	public abstract void delete(T paramT);

	/**
	 * 分页功能的实现
	 * 
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页显示数量
	 * @param queryLimit
	 *            查询限制条件
	 * @return
	 */
	public List<T> getPaging(int pageNo, int pageSize, String queryLimit);
}
