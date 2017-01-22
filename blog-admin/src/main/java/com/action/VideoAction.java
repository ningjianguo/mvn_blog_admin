package com.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.comm.action.BaseAction;
import com.entity.Video;
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
public class VideoAction extends BaseAction<Video> {

	private static final long serialVersionUID = 1L;

	private File video;
	private String videoContentType;
	private String videoFileName;
	private String videoCategories;
	private String videoName;

	/**
	 * 前往视频上传页面
	 */
	public String forwardUploadVideo() {
		request.setAttribute("tags", videoServiceImpl.getAllVideoTag());
		return "upload";
	}

	/**
	 * 前往视频管理页面
	 * 
	 * @return
	 */
	public String forwardManageVideo() {
		return "manage";
	}

	/**
	 * 加载视频信息
	 */
	public String loadVideo() {
		printJsonStringToBrowser(videoServiceImpl.getVideoAllInfo(Integer.parseInt(page), Integer.parseInt(rows)));
		return null;
	}

	/**
	 * 视频上传
	 */
	public String uploadVideo() {
		if (videoServiceImpl.uploadVideo(getModel())) {
			try {
				FileUtils.copyFile(video,
						new File(FilePathUtil.getValue("uploadFilePath") + "/"
								+ getModel().getVideoName() + "_blog_"
								+ videoServiceImpl.getMaxVideoId() + "."
								+ videoContentType.split("/")[1]));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return displayVideo();
	}

	/**
	 * 通过父目录获取子目录
	 */

	public String videoCategory() {
		videoCategories = videoServiceImpl.getCategories(getModel()
				.getVideoTag().getVideoTagId());
		return SUCCESS;
	}

	/**
	 * 展示视频
	 */
	public String displayVideo() {
		request.setAttribute("tags", videoServiceImpl.getAllVideoTag());
		int videoId = videoServiceImpl.getMaxVideoId();
		request.setAttribute("videoId", videoId);
		return "display";
	}

	/**
	 * 根据videoId获取videoName
	 */
	public String jsonVideo() {
		videoName = videoServiceImpl.getVideoName(getModel().getVideoId());
		return SUCCESS;
	}

	/**
	 * 加载视频标签
	 */
	public String loadTagVideo() {
		printJsonStringToBrowser(videoServiceImpl.getAllTagType());
		return null;
	}

	/**
	 * 加载视频发布状态
	 */
	public String loadStatuVideo() {
		printJsonStringToBrowser(videoServiceImpl.getVideoStatu());
		return null;
	}

	/**
	 * 修改视频信息
	 */
	public String updateVideo() {
		printJsonStringToBrowser(videoServiceImpl.updateVideo(getModel()));
		return null;
	}

	/**
	 * 删除视频信息
	 */
	public String deleteVideo() {
		printJsonStringToBrowser(videoServiceImpl.deleteVideo(getModel()));
		return null;
	}

	/**
	 * @return the video
	 */
	public File getVideo() {
		return video;
	}

	/**
	 * @param video
	 *            the video to set
	 */
	public void setVideo(File video) {
		this.video = video;
	}

	/**
	 * @return the videoContentType
	 */
	public String getVideoContentType() {
		return videoContentType;
	}

	/**
	 * @param videoContentType
	 *            the videoContentType to set
	 */
	public void setVideoContentType(String videoContentType) {
		this.videoContentType = videoContentType;
	}

	/**
	 * @return the videoFileName
	 */
	public String getVideoFileName() {
		return videoFileName;
	}

	/**
	 * @param videoFileName
	 *            the videoFileName to set
	 */
	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	/**
	 * @return the videoCategories
	 */
	public String getVideoCategories() {
		return videoCategories;
	}

	/**
	 * @return the videoName
	 */
	public String getVideoName() {
		return videoName;
	}

}
