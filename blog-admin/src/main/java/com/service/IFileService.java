package com.service;

import com.entity.File;

/**
	@project:ningjianguo
	@author:Techape
	@date:2016年12月26日 下午10:55:21
	@email:1195726908@qq.com
	@version:v1.0
	@description:文件业务接口
 */
public interface IFileService {
	/**
	 * 获得所有文件的状态(json格式)
	 */
	public String getFileStatu();
	/**
	 * 文件上传
	 * @param File f 对象
	 * @param fileContentType 文件类型
	 * @return true(成功)、false(失败)
	 */
	public Boolean uploadFile(File f,String fileContentType);
	
	
	/**
	 * 获得文件所有信息
	 * @return Json格式文件信息
	 */
	public String getFileAllInfo(int pageNo, int pageSize);
	
	/**
	 * 获得文件条目数
	 */
	public int getFileTotalSize();

	/**
	 * 更新文件信息
	 * @return json格式文件信息
	 */
	public String updateFile(File file);

	/**
	 * 删除文件
	 */
	public String deleteFile(File file);
	
	/**
	 * 获得最大ID
	 * @return
	 */
	public int getMaxFileId();
}
	
