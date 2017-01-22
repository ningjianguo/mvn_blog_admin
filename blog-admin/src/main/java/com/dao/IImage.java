package com.dao;

import java.util.List;

import com.entity.Image;
import com.entity.ImageFolder;

/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月26日 下午10:42:11
	@email:1195726908@qq.com
	@version:v1.0
	@description:照片接口
 */
public interface IImage {
	/**
	 * 获得 所有相册名称
	 */
	public List<ImageFolder> getAllImageFolder();
	
	/**
	 * 获得相片所有信息
	 */
	public List<Image> getImageAllInfo();
	
	/**
	 * 更新相片信息
	 */
	public int updateImage(Image image);
	
	/**
	 * 删除相片信息
	 */
	public int deleteImage(int imageId);
	
	/**
	 * 更新相册
	 */
	public int updateImageFolder(Image image);
	
}


