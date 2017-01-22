package com.dao;

import java.util.List;

import com.entity.File;

/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月26日 下午10:42:11
	@email:1195726908@qq.com
	@version:v1.0
	@description:文件接口
 */
public interface IFile {
	/**
	 * 获得所有文件信息
	 */
	public List<File> getFileAllInfo();
	
	/**
	 * 更新文件信息
	 */
	public int updateFile(File file);
	
	/**
	 * 删除文件信息
	 */
	public int deleteFile(int fileId);
}


