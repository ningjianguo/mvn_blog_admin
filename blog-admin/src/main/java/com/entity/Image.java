package com.entity;

import java.util.Date;

/**
 * Image entity. @author MyEclipse Persistence Tools
 */

public class Image implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private ImageFolder imageFolder;
	private String imageName;
	private String imageFileName;
	private Date imageUploadTime;

	// Constructors

	/** default constructor */
	public Image() {
	}

	/** full constructor */
	public Image(ImageFolder imageFolder, String imageName,
			String imageFileName, Date imageUploadTime) {
		this.imageFolder = imageFolder;
		this.imageName = imageName;
		this.imageFileName = imageFileName;
		this.imageUploadTime = imageUploadTime;
	}

	// Property accessors

	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public ImageFolder getImageFolder() {
		return this.imageFolder;
	}

	public void setImageFolder(ImageFolder imageFolder) {
		this.imageFolder = imageFolder;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageFileName() {
		return this.imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Date getImageUploadTime() {
		return this.imageUploadTime;
	}

	public void setImageUploadTime(Date imageUploadTime) {
		this.imageUploadTime = imageUploadTime;
	}

}