package com.service;

import java.util.List;

import com.entity.Video;
import com.entity.VideoTag;

/**
	@project:ningjianguo
	@author:Techape
	@date:2016年12月26日 下午10:55:21
	@email:1195726908@qq.com
	@version:v1.0
	@description:视频业务接口
 */
public interface IVideoService {
	/**
	 * 获得所有视频的类型(json格式)
	 */
	public String getAllTagType();
	/**
	 * 获得所有视频的状态(json格式)
	 */
	public String getVideoStatu();
	/**
	 * 获得所有视频标签
	 */
	public List<VideoTag> getAllVideoTag();
	
	/**
	 * 视频上传
	 * @param Video v 对象
	 * @return true(成功)、false(失败)
	 */
	public Boolean uploadVideo(Video v);
	
	/**
	 * 获取视频条目
	 * @param p_categoryId 视频父目录Id
	 * @return 视频条目集合---json格式字符串
	 */
	public String getCategories(int p_categoryId);
	
	/**
	 * 获得最近上传的一部视频
	 */
	public int getMaxVideoId();
	
	/**
	 * 获得视频名称
	 * @param videoId 视频ID
	 * @return videoName
	 */
	public String getVideoName(int videoId);
	
	/**
	 * 获得视频所有信息
	 * @return Json格式视频信息
	 */
	public String getVideoAllInfo(int pageNo, int pageSize);
	
	/**
	 * 获得视频条目数
	 */
	public int getVideoTotalSize();

	/**
	 * 更新视频信息
	 * @return json格式视频信息
	 */
	public String updateVideo(Video video);

	/**
	 * 删除视频
	 */
	public String deleteVideo(Video video);
}
	
