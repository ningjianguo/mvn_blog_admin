package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.comm.dao.BaseDaoImpl;
import com.dao.IVideo;
import com.entity.Video;
import com.entity.VideoTag;

/*
 @project:ningjianguo
 @author:Techape
 @date:2016年12月26日 下午10:44:01
 @email:1195726908@qq.com
 @version:v1.0
 @description:视频接口实现类
 */
@Repository
public class VideoDaoImpl extends BaseDaoImpl<Video> implements IVideo {

	@SuppressWarnings("unchecked")
	@Override
	public List<VideoTag> getAllVideoTag() {
		try {
			Query query = getSession().createQuery("from VideoTag");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Video> getCategories(int p_categoryId) {
		try {
			Query query = getSession().createQuery("from Video where videoTag.videoTagId=?");
			query.setInteger(0, p_categoryId);
			List<Video> categories = query.list();
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Video> getVideAllInfo() {
		return queryAllInfo();
	}
	
	@Override
	public int updateVideo(Video video) {
		int count = 0;
		Session session = getSession();
		try {
			Video updateVideo = (Video) session.get(Video.class, video.getVideoId());
			updateVideo.setVideoName(video.getVideoName());
			updateVideo.getVideoTag().setVideoTagId(queryTagId(video.getVideoTag().getVideoTagName()));
			updateVideo.setVideoStatu(video.getVideoStatu());
			session.clear();
			session.update(updateVideo);
			session.flush();
			count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 根据标签名取得标签id
	 * @param tagName 标签名
	 * @return 标签ID
	 */
	private int queryTagId(String tagName){
		Query query = getSession().createQuery("from VideoTag where videoTagName=?").setString(0, tagName);
		VideoTag videoTag = (VideoTag) query.uniqueResult();
		return videoTag.getVideoTagId();
	}
	
	@Override
	public int deleteVideo(int videoId) {
		int count = 0;
		try {
			Session session = getSession();
			Video deleteVideo = (Video) session.get(Video.class, videoId);
			session.delete(deleteVideo);
			count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
