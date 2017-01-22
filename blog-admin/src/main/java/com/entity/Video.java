package com.entity;

import java.util.Date;

/**
 * Video entity. @author MyEclipse Persistence Tools
 */

public class Video implements java.io.Serializable {

	// Fields

	private Integer videoId;
	private VideoTag videoTag;
	private String videoName;
	private String videoUploadEditer;
	private Date videoUploadTime;
	private Integer videoDownloadCount;
	private Integer videoStatu;
	private String videoFileName;

	// Constructors

	/** default constructor */
	public Video() {
	}

	/** full constructor */
	public Video(VideoTag videoTag, String videoName, String videoUploadEditer,
			Date videoUploadTime, Integer videoDownloadCount,Integer videoStatu,String videoFileName) {
		this.videoTag = videoTag;
		this.videoName = videoName;
		this.videoUploadEditer = videoUploadEditer;
		this.videoUploadTime = videoUploadTime;
		this.videoDownloadCount = videoDownloadCount;
		this.videoStatu = videoStatu;
		this.videoFileName = videoFileName;
	}

	// Property accessors

	public Integer getVideoId() {
		return this.videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	
	/**
	 * @return the videoFileName
	 */
	public String getVideoFileName() {
		return videoFileName;
	}

	/**
	 * @param videoFileName the videoFileName to set
	 */
	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public VideoTag getVideoTag() {
		return this.videoTag;
	}

	public void setVideoTag(VideoTag videoTag) {
		this.videoTag = videoTag;
	}

	public String getVideoName() {
		return this.videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoUploadEditer() {
		return this.videoUploadEditer;
	}

	public void setVideoUploadEditer(String videoUploadEditer) {
		this.videoUploadEditer = videoUploadEditer;
	}

	public Date getVideoUploadTime() {
		return this.videoUploadTime;
	}

	public void setVideoUploadTime(Date videoUploadTime) {
		this.videoUploadTime = videoUploadTime;
	}

	public Integer getVideoDownloadCount() {
		return this.videoDownloadCount;
	}

	public void setVideoDownloadCount(Integer videoDownloadCount) {
		this.videoDownloadCount = videoDownloadCount;
	}

	/**
	 * @return the videoStatu
	 */
	public Integer getVideoStatu() {
		return videoStatu;
	}

	/**
	 * @param videoStatu the videoStatu to set
	 */
	public void setVideoStatu(Integer videoStatu) {
		this.videoStatu = videoStatu;
	}
	
}