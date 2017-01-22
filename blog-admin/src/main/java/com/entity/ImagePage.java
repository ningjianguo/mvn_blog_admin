package com.entity;

import java.io.Serializable;

/*
	@project:ningjianguo
	@author:Techape
	@date:2017年1月19日 下午7:38:00
	@email:1195726908@qq.com
	@version:v1.0
	@description:照片分页实体类
*/
public class ImagePage implements Serializable{
	/**
	 * @param pageNo 当前页
	 * @param totalPage 总页码数
	 * @param pageRecords 每页记录数
	 * @param totalRecords 总记录数
	 * @param folderId 所属文件夹id
	 */
	private int pageNo;
	private int totalPage;
	private int pageRecords;
	private int totalRecords;
	private int folderId;
	
	public ImagePage() {
		super();
	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the folderId
	 */
	public int getFolderId() {
		return folderId;
	}

	/**
	 * @param folderId the folderId to set
	 */
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	/**
	 * @return the pageRecords
	 */
	public int getPageRecords() {
		return pageRecords;
	}

	/**
	 * @param pageRecords the pageRecords to set
	 */
	public void setPageRecords(int pageRecords) {
		this.pageRecords = pageRecords;
	}
	
}

