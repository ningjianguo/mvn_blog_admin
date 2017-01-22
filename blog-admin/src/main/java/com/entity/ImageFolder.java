package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * ImageFolder entity. @author MyEclipse Persistence Tools
 */

public class ImageFolder implements java.io.Serializable {

	// Fields

	private Integer imageFolderId;
	private String imageFolderName;
	private Integer imageFolderStatu;
	private String imageFolderDescription;
	private Set images = new HashSet(0);

	// Constructors

	/** default constructor */
	public ImageFolder() {
	}

	/** minimal constructor */
	public ImageFolder(String imageFolderName, Integer imageFolderStatu,String imageFolderDescription) {
		this.imageFolderName = imageFolderName;
		this.imageFolderStatu = imageFolderStatu;
		this.imageFolderDescription = imageFolderDescription;
	}

	/** full constructor */
	public ImageFolder(String imageFolderName, Integer imageFolderStatu,
			Set images) {
		this.imageFolderName = imageFolderName;
		this.imageFolderStatu = imageFolderStatu;
		this.images = images;
	}

	// Property accessors

	public Integer getImageFolderId() {
		return this.imageFolderId;
	}

	public void setImageFolderId(Integer imageFolderId) {
		this.imageFolderId = imageFolderId;
	}

	public String getImageFolderName() {
		return this.imageFolderName;
	}

	public void setImageFolderName(String imageFolderName) {
		this.imageFolderName = imageFolderName;
	}

	public Integer getImageFolderStatu() {
		return this.imageFolderStatu;
	}

	public void setImageFolderStatu(Integer imageFolderStatu) {
		this.imageFolderStatu = imageFolderStatu;
	}

	public Set getImages() {
		return this.images;
	}

	public void setImages(Set images) {
		this.images = images;
	}

	/**
	 * @return the imageFolderDescription
	 */
	public String getImageFolderDescription() {
		return imageFolderDescription;
	}

	/**
	 * @param imageFolderDescription the imageFolderDescription to set
	 */
	public void setImageFolderDescription(String imageFolderDescription) {
		this.imageFolderDescription = imageFolderDescription;
	}
	
}