package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * VideoTag entity. @author MyEclipse Persistence Tools
 */

public class VideoTag implements java.io.Serializable {

	// Fields

	private Integer videoTagId;
	private String videoTagName;
	private Set videos = new HashSet(0);

	// Constructors

	/** default constructor */
	public VideoTag() {
	}

	/** minimal constructor */
	public VideoTag(String videoTagName) {
		this.videoTagName = videoTagName;
	}

	/** full constructor */
	public VideoTag(String videoTagName, Set videos) {
		this.videoTagName = videoTagName;
		this.videos = videos;
	}

	// Property accessors

	public Integer getVideoTagId() {
		return this.videoTagId;
	}

	public void setVideoTagId(Integer videoTagId) {
		this.videoTagId = videoTagId;
	}

	public String getVideoTagName() {
		return this.videoTagName;
	}

	public void setVideoTagName(String videoTagName) {
		this.videoTagName = videoTagName;
	}

	public Set getVideos() {
		return this.videos;
	}

	public void setVideos(Set videos) {
		this.videos = videos;
	}

}