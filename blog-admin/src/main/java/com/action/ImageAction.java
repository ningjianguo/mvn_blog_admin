package com.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.comm.action.BaseAction;
import com.entity.Image;
import com.entity.ImagePage;
import com.util.FilePathUtil;

/*
 @project:ningjianguo
 @author:Techape
 @date:2016年12月26日 下午9:44:42
 @email:1195726908@qq.com
 @version:v1.0
 @description:视频控制器类
 */
@Controller
@Scope("prototype")
public class ImageAction extends BaseAction<Image> {

	private static final long serialVersionUID = 1L;

	private int imagePageNo;
	private int imageFolderId;
	private File[] images;
	private String[] imagesContentType;
	private String[] imagesFileName;

	/**
	 * 前往相片上传页面
	 */
	public String forwardUploadImage() {
		request.setAttribute("tags", imageServiceImpl.getAllImageFolderName());
		return "upload";
	}

	/**
	 * 查看相册
	 */
	public String lookFolderforImage() {
		request.setAttribute("tags", imageServiceImpl.getAllImageFolderName());
		return "lookImageFolder";
	}

	/**
	 * 查看相片
	 */
	public String lookImage() {
		ImagePage iPage = new ImagePage();
		iPage.setFolderId(imageFolderId);
		iPage.setPageNo(imagePageNo);

		ImagePage imagePage = imageServiceImpl.imageCount(imageFolderId);
		imagePage.setFolderId(imageFolderId);
		imagePage.setPageNo(imagePageNo);
		request.setAttribute("tags", imageServiceImpl.getImageInfo(iPage));// 1\2
		request.setAttribute("iPage", imagePage);
		return "lookImage";
	}

	/**
	 * 前往查看相片页面
	 */
	public String toLookImage() {
		int folderId = getModel().getImageFolder().getImageFolderId();

		// 打开页面默认访问第一页数据
		ImagePage page = new ImagePage();
		page.setFolderId(folderId);
		page.setPageNo(1);

		request.setAttribute("tags", imageServiceImpl.getImageInfo(page));
		ImagePage imagePage = imageServiceImpl.imageCount(folderId);
		imagePage.setFolderId(folderId);
		request.setAttribute("iPage", imagePage);
		return "lookImage";
	}

	/**
	 * 前往照片管理页面
	 * 
	 * @return
	 */
	public String forwardManageImage() {
		return "manage";
	}

	/**
	 * 加载照片信息
	 */
	public String loadImage() {
		printJsonStringToBrowser(
				imageServiceImpl.getImageAllInfo(Integer.parseInt(page), Integer.parseInt(rows), getModel().getImageFolder().getImageFolderId()));
		return null;
	}

	/**
	 * 相片上传
	 */
	public String uploadImage() {
		for (int i = 0; i < images.length; i++) {
			Image image = getModel();
			String imageName = imagesFileName[i].split("\\.")[0];
			String imageContentType = "." + imagesContentType[i].split("/")[1];
			image.setImageName(imageName);
			if (imageServiceImpl.uploadImage(image, imageContentType)) {
				try {
					FileUtils.copyFile(images[i], new File(FilePathUtil.getValue("uploadFilePath") + "/"
							+ imageServiceImpl.getMaxImageId() + imageContentType));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lookFolderforImage();
	}

	/**
	 * 修改照片信息
	 */
	public String updateImage() {
		printJsonStringToBrowser(imageServiceImpl.updateImage(getModel()));
		return null;
	}

	/**
	 * 删除照片信息
	 */
	public String deleteImage() {
		printJsonStringToBrowser(imageServiceImpl.deleteImage(getModel()));
		return null;
	}
	/**
	 * 修改相册信息
	 */
	public String updateImageFolder(){
		printJsonStringToBrowser(imageServiceImpl.updateImageFolder(getModel()));
		return null;
	}
	/**
	 * 删除相册
	 */
	public String deleteImageFolder(){
		printJsonStringToBrowser(imageServiceImpl.deleteImageFolder(getModel()));
		return null;
	}
	/**
	 * 加载相册发布状态
	 */
	public String loadStatuImageFolder() {
		printJsonStringToBrowser(imageServiceImpl.getImageFolderStatu());
		return null;
	}

	/**
	 * 加载相册名
	 */
	public String loadImageFolder() {
		printJsonStringToBrowser(imageServiceImpl.getAllImageFolder());
		return null;
	}
	/**
	 * 验证相册名
	 */
	public String validataImageFolder(){
		printJsonStringToBrowser(imageServiceImpl.validataImageFolder(getModel().getImageFolder().getImageFolderName()));
		return null;
	}
	/**
	 * 增加相册
	 */
	public String addImageFolder(){
		printJsonStringToBrowser(imageServiceImpl.addImageFolder(getModel().getImageFolder()));
		return null;
	}
	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(File[] images) {
		this.images = images;
	}

	/**
	 * @param imagesContentType
	 *            the imagesContentType to set
	 */
	public void setImagesContentType(String[] imagesContentType) {
		this.imagesContentType = imagesContentType;
	}

	/**
	 * @param imagesFileName
	 *            the imagesFileName to set
	 */
	public void setImagesFileName(String[] imagesFileName) {
		this.imagesFileName = imagesFileName;
	}

	/**
	 * @param imagePageNo
	 *            the imagePageNo to set
	 */
	public void setImagePageNo(int imagePageNo) {
		this.imagePageNo = imagePageNo;
	}

	/**
	 * @param imageFolderId
	 *            the imageFolderId to set
	 */
	public void setImageFolderId(int imageFolderId) {
		this.imageFolderId = imageFolderId;
	}
}