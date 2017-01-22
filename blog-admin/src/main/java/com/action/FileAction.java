package com.action;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.comm.action.BaseAction;
import com.entity.File;
import com.util.FilePathUtil;

/*
 @project:ningjianguo
 @author:Techape
 @date:2016年12月26日 下午9:44:42
 @email:1195726908@qq.com
 @version:v1.0
 @description:文件控制器类
 */
@Controller
@Scope("prototype")
public class FileAction extends BaseAction<File> {

	private static final long serialVersionUID = 1L;

	private java.io.File file;
	private String fileContentType;
	private String fileFileName;

	/**
	 * 前往文件上传页面
	 */
	public String forwardUploadFile() {
		return "upload";
	}

	/**
	 * 前往文件管理页面
	 * 
	 * @return
	 */
	public String forwardManageFile() {
		return "manage";
	}

	/**
	 * 加载视频信息
	 */
	public String loadFile() {
		printJsonStringToBrowser(fileServiceImpl.getFileAllInfo(Integer.parseInt(page), Integer.parseInt(rows)));
		return null;
	}

	/**
	 * 文件上传
	 */
	public String uploadFile() {
		fileContentType = transformType(this.getFileContentType());
		if (fileServiceImpl.uploadFile(getModel(),fileContentType)) {
			try {
				FileUtils.copyFile(
						file,
						new java.io.File(FilePathUtil
								.getValue("uploadFilePath")
								+ "/"
								+ getModel().getFileName()
								+ "_blog_"
								+ fileServiceImpl.getMaxFileId()
								+ "."
								+ fileContentType));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return forwardManageFile();
	}
	
	/**
	 * 文件类型格式转换
	 */
	public String transformType(String type){
		String string = type.split("/")[1];
		if("octet-stream".equals(string)){
			type = "rar";
		}else{
			type = string;
		}
		return type;
	}

	/**
	 * 加载文件发布状态
	 */
	public String loadStatuFile() {
		printJsonStringToBrowser(fileServiceImpl.getFileStatu());
		return null;
	}

	/**
	 * 修改文件信息
	 */
	public String updateFile() {
		printJsonStringToBrowser(fileServiceImpl.updateFile(getModel()));
		return null;
	}

	/**
	 * 删除文件信息
	 */
	public String deleteFile() {
		printJsonStringToBrowser(fileServiceImpl.deleteFile(getModel()));
		return null;
	}

	/**
	 * @return the file
	 */
	public java.io.File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(java.io.File file) {
		this.file = file;
	}

	/**
	 * @return the fileContentType
	 */
	public String getFileContentType() {
		return fileContentType;
	}

	/**
	 * @param fileContentType
	 *            the fileContentType to set
	 */
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * @return the fileFileName
	 */
	public String getFileFileName() {
		return fileFileName;
	}

	/**
	 * @param fileFileName
	 *            the fileFileName to set
	 */
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
}
