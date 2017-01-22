package com.service.impl;

import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.comm.dao.BaseDaoImpl;
import com.dao.IImage;
import com.entity.Image;
import com.entity.ImageFolder;
import com.entity.ImagePage;
import com.service.IImageService;
import com.util.FilePathUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 @project:ningjianguo
 @author:Techape
 @date:2017年1月16日 下午3:36:31
 @email:1195726908@qq.comzo
 @version:v1.0
 @description:照片业务层实现类
 */
@Service
public class ImageServiceImpl extends BaseDaoImpl<Image> implements IImageService {

	@Resource
	private IImage imageDaoImpl;

	@Override
	public List<ImageFolder> getAllImageFolderName() {
		return imageDaoImpl.getAllImageFolder();
	}

	@Override
	public Boolean uploadImage(Image v, String imageContentType) {
		int newImageId = getMaxImageId();
		v.setImageUploadTime(new Date());
		v.setImageFileName((newImageId + 1) + imageContentType);
		try {
			save(v);
			if (newImageId == 0 || getMaxImageId() != newImageId + 1) {
				getSession().createSQLQuery("update image set image_file_name=:filename where image_file_name=:name")
						.setString("filename", getMaxImageId() + imageContentType)
						.setString("name", (newImageId + 1) + imageContentType).executeUpdate();

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String getCategories(int p_categoryId) {
		return null;
	}

	@Override
	public int getMaxImageId() {
		try {
			Query query = getSession().createSQLQuery("select ifnull(max(image_id),0) from image");
			BigInteger videoId = (BigInteger) query.list().get(0);
			return videoId.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String getImageFolderStatu() {
		Map<String, Object> statu1 = new HashMap<String, Object>();
		Map<String, Object> statu2 = new HashMap<String, Object>();
		List<Map<String, Object>> statuList = new ArrayList<Map<String, Object>>();
		statu1.put("statuName", FILE_STATU_RELEASE);
		statu1.put("statuId", 2);
		statu2.put("statuName", FILE_STATU_NORELEASE);
		statu2.put("statuId", 1);
		statuList.add(statu1);
		statuList.add(statu2);
		return JSONArray.fromObject(statuList).toString();
	}

	@Override
	public String getImageName(int ImageId) {
		return null;
	}

	@Override
	public String getImageAllInfo(int pageNo, int pageSize, int folderId) {
		List<Image> images = folderId == 0 ? getPaging(pageNo, pageSize, null)
				: getPaging(pageNo, pageSize, "where imageFolder.imageFolderId=" + folderId);
		Map<String, Object> maps = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONObject jobj = null;
		try {
			for (Image image : images) {
				maps = new HashMap<String, Object>();
				maps.put("imageId", image.getImageId());
				maps.put("imageFileName", image.getImageFileName());
				maps.put("imageUploadTime", new SimpleDateFormat("yyyy-MM-dd").format(image.getImageUploadTime()));
				maps.put("imageFolder.imageFolderName", image.getImageFolder().getImageFolderName());
				maps.put("imageFolder.imageFolderStatu", releaseStatu(image.getImageFolder().getImageFolderStatu()));
				list.add(maps);
			}
			jobj = new JSONObject();
			jobj.accumulate("total", getImageTotalSize(folderId));// total代表一共有多少数据
			jobj.accumulate("rows", list);// row是代表显示的页的数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobj.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getImageTotalSize(int folderId) {
		String sql = folderId == 0 ? ("from Image") : ("from Image where imageFolder.imageFolderId=" + folderId);
		List<Image> images = getSession().createQuery(sql).list();
		return images.size();
	}

	@Override
	public String updateImage(Image image) {
		return JSONArray.fromObject(imageDaoImpl.updateImage(image)).toString();
	}

	@Override
	public String deleteImage(Image image) {
		JSONObject jobj = new JSONObject();
		// 删除硬盘中的视频文件
		try {
			Query query = getSession().createQuery("from Image where imageId=:id").setInteger("id", image.getImageId());
			Image deleteImage = (Image) query.uniqueResult();
			File desFile = new File(FilePathUtil.getValue("uploadFilePath") + "/" + deleteImage.getImageFileName());
			if (desFile.exists()) {
				if (desFile.delete()) {
					jobj.accumulate("success", imageDaoImpl.deleteImage(image.getImageId()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobj.toString();
	}

	@Override
	public List<Image> getImageInfo(ImagePage iPage) {
		return getPaging(iPage.getPageNo(), IMAGE_PAGE_SIZE, "where imageFolder.imageFolderId=" + iPage.getFolderId());// 设置每页显示照片数量为30张
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImagePage imageCount(int imageFolderId) {
		List<Image> images = getSession().createQuery("from Image where imageFolder.imageFolderId=" + imageFolderId)
				.list();
		ImagePage iPage = new ImagePage();
		int totalRecords = images.size();
		int totalPages = totalRecords % IMAGE_PAGE_SIZE == 0 ? (totalRecords / IMAGE_PAGE_SIZE)
				: (totalRecords / IMAGE_PAGE_SIZE) + 1;
		iPage.setPageRecords(IMAGE_PAGE_SIZE);
		iPage.setTotalRecords(totalRecords);
		iPage.setTotalPage(totalPages);
		return iPage;
	}

	@Override
	public String getAllImageFolder() {
		List<ImageFolder> folders = imageDaoImpl.getAllImageFolder();
		Map<String, Object> maps = null;
		List<Map<String, Object>> tagList = new ArrayList<Map<String, Object>>();
		for (ImageFolder folder : folders) {
			maps = new HashMap<String, Object>();
			maps.put("folderId", folder.getImageFolderId());
			maps.put("folderName", folder.getImageFolderName());
			tagList.add(maps);
		}
		return JSONArray.fromObject(tagList).toString();
	}

	@Override
	public String updateImageFolder(Image image) {
		return JSONArray.fromObject(imageDaoImpl.updateImageFolder(image)).toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String deleteImageFolder(Image image) {
		JSONObject jobj = new JSONObject();
		Session session = getSession();
		try {
			List<Image> images = session.createQuery("from Image where imageFolder.imageFolderId=?")
					.setInteger(0, image.getImageFolder().getImageFolderId()).list();
			for (Image img : images) {
				Image deleteImage = (Image) session.createQuery("from Image where imageId=:id")
						.setInteger("id", img.getImageId()).uniqueResult();
				File desFile = new File(FilePathUtil.getValue("uploadFilePath") + "/" + deleteImage.getImageFileName());
				if (desFile.exists()) {
					if (desFile.delete()) {
						session.delete(deleteImage);
						session.beginTransaction().commit();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageFolder iFolder = new ImageFolder();
		iFolder.setImageFolderId(image.getImageFolder().getImageFolderId());
		session.delete(iFolder);
		session.beginTransaction().commit();
		jobj = jobj.accumulate("success", "success");
		return jobj.toString();
	}

	@Override
	public String validataImageFolder(String folderName) {
		ImageFolder imageFolder = (ImageFolder) getSession().createQuery("from ImageFolder where imageFolderName=?")
				.setString(0, folderName).uniqueResult();
		int flag = imageFolder == null ? 0 : 1;
		return JSONArray.fromObject(flag).toString();
	}

	@Override
	public String addImageFolder(ImageFolder imageFolder) {
		Query query = getSession().createSQLQuery("insert into image_folder(image_folder_name,image_folder_statu,image_folder_description) values(?,?,?)");
		query.setString(0, imageFolder.getImageFolderName())
			 .setInteger(1, imageFolder.getImageFolderStatu())
			 .setString(2, imageFolder.getImageFolderDescription())
			 .executeUpdate();
		return JSONArray.fromObject(1).toString();
	}

}
