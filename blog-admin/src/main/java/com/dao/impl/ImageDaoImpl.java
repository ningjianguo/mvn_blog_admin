package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.comm.dao.BaseDaoImpl;
import com.dao.IImage;
import com.entity.Image;
import com.entity.ImageFolder;

/*
	@project:ningjianguo
	@author:Techape
	@date:2017年1月16日 下午3:26:08
	@email:1195726908@qq.com
	@version:v1.0
	@description:相片接口实现类
 */
@Repository
public class ImageDaoImpl extends BaseDaoImpl<Image> implements IImage {

	@SuppressWarnings("unchecked")
	@Override
	public List<ImageFolder> getAllImageFolder() {
		Query query = getSession().createQuery("from ImageFolder");
		return query.list();
	}

	@Override
	public List<Image> getImageAllInfo() {
		return queryAllInfo();
	}

	@Override
	public int updateImage(Image image) {
		int count = 0;
		Session session = getSession();
		try {
			Image updateImage = (Image) session.get(Image.class, image.getImageId());
			updateImage.getImageFolder().setImageFolderId(queryTagId(image.getImageFolder().getImageFolderName()));
			session.clear();
			session.update(updateImage);
			session.flush();
			count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteImage(int imageId) {
		int count = 0;
		try {
			Image deleteImage = (Image) getSession().get(Image.class, imageId);
			getSession().delete(deleteImage);
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
		Query query = getSession().createQuery("from ImageFolder where imageFolderName=?").setString(0, tagName);
		ImageFolder imageFolder = (ImageFolder) query.uniqueResult();
		return imageFolder.getImageFolderId();
	}

	@Override
	public int updateImageFolder(Image image) {
		int count = 0;
		Session session = getSession();
		String description = image.getImageFolder().getImageFolderDescription();
		try {
			ImageFolder updateImageFolder = (ImageFolder) session.get(ImageFolder.class, image.getImageFolder().getImageFolderId());
			updateImageFolder.setImageFolderStatu(image.getImageFolder().getImageFolderStatu());
			if(description !=""|| description !=null){
				updateImageFolder.setImageFolderDescription(image.getImageFolder().getImageFolderDescription());
			}
			session.clear();
			session.update(updateImageFolder);
			session.flush();
			count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}


