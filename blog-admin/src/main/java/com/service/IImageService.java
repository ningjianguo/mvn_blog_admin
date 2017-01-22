package com.service;

import java.util.List;

import com.entity.Image;
import com.entity.ImageFolder;
import com.entity.ImagePage;

/**
	@project:ningjianguo
	@author:Techape
	@date:2016年12月26日 下午10:55:21
	@email:1195726908@qq.com
	@version:v1.0
	@description:照片业务接口
 */
public interface IImageService {
	
	final static int IMAGE_PAGE_SIZE = 30;//每页显示照片数量
	
	/**
	 * 获得所有相册名
	 */
	public List<ImageFolder> getAllImageFolderName();
	/**
	 * 获得相册中的相片
	 */
	public List<Image> getImageInfo(ImagePage iPage);
	/**
	 * 相片上传
	 * @param Image v 对象
	 * @return true(成功)、false(失败)
	 */
	public Boolean uploadImage(Image v,String imageContentType);
	
	/**
	 * 获取相片条目
	 * @param p_categoryId 相片父目录Id
	 * @return 相片条目集合---json格式字符串
	 */
	public String getCategories(int p_categoryId);
	
	/**
	 * 获得最近上传的一部相片
	 */
	public int getMaxImageId();
	
	/**
	 * 获得相片名称
	 * @param ImageId 相片ID
	 * @return ImageName
	 */
	public String getImageName(int ImageId);
	
	/**
	 * 获得相片所有信息
	 * @return Json格式相片信息
	 */
	public String getImageAllInfo(int pageNo, int pageSize, int folderId);
	
	/**
	 * 获得相片条目数
	 */
	public int getImageTotalSize(int folderId);

	/**
	 * 更新相片信息
	 * @return json格式相片信息
	 */
	public String updateImage(Image Image);

	/**
	 * 删除相片
	 */
	public String deleteImage(Image Image);
	
	/**
	 * 获取照片数据条数
	 */
	public ImagePage imageCount(int imageFolderId);
	
	/**
	 * @return 相册状态
	 */
	public String getImageFolderStatu();
	
	/**
	 * @return 相册名 (json格式)
	 */
	public String getAllImageFolder();
	
	/**
	 * 更新相册
	 */
	public String updateImageFolder(Image image);
	
	/**
	 * 删除相册
	 */
	public String deleteImageFolder(Image image);
	
	/**
	 * 新增相册
	 */
	public String addImageFolder(ImageFolder image);
	/**
	 * 验证相册名
	 */
	public String validataImageFolder(String folderName);
}
	
