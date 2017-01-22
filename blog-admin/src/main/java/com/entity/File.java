package com.entity;

import java.util.Date;

/**
 * File entity. @author MyEclipse Persistence Tools
 */

public class File implements java.io.Serializable {

	// Fields

	private Integer fileId;
	private String fileName;
	private String fileZipName;
	private Integer fileStatu;
	private Date fileUploadTime;
	private Integer fileDownloadCount;

	// Constructors

	/** default constructor */
	public File() {
	}

	/** full constructor */
	public File(String fileName, String fileZipName, Integer fileStatu,
			Date fileUploadTime, Integer fileDownloadCount) {
		this.fileName = fileName;
		this.fileZipName = fileZipName;
		this.fileStatu = fileStatu;
		this.fileUploadTime = fileUploadTime;
		this.fileDownloadCount = fileDownloadCount;
	}

	// Property accessors

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileZipName() {
		return this.fileZipName;
	}

	public void setFileZipName(String fileZipName) {
		this.fileZipName = fileZipName;
	}

	public Integer getFileStatu() {
		return this.fileStatu;
	}

	public void setFileStatu(Integer fileStatu) {
		this.fileStatu = fileStatu;
	}

	public Date getFileUploadTime() {
		return this.fileUploadTime;
	}

	public void setFileUploadTime(Date fileUploadTime) {
		this.fileUploadTime = fileUploadTime;
	}

	public Integer getFileDownloadCount() {
		return this.fileDownloadCount;
	}

	public void setFileDownloadCount(Integer fileDownloadCount) {
		this.fileDownloadCount = fileDownloadCount;
	}

}