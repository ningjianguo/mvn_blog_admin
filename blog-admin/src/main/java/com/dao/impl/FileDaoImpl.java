package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.comm.dao.BaseDaoImpl;
import com.dao.IFile;
import com.entity.File;

/*
 @project:ningjianguo
 @author:Techape
 @date:2016年12月26日 下午10:44:01
 @email:1195726908@qq.com
 @version:v1.0
 @description:文件接口实现类
 */
@Repository
public class FileDaoImpl extends BaseDaoImpl<File> implements IFile {


	@Override
	public List<File> getFileAllInfo() {
		return queryAllInfo();
	}
	
	@Override
	public int updateFile(File file) {
		int count = 0;
		Session session = getSession();
		try {
			File updateFile = (File) session.get(File.class, file.getFileId());
			updateFile.setFileName(file.getFileName());
			updateFile.setFileStatu(file.getFileStatu());
			session.clear();
			session.update(updateFile);
			session.flush();
			count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	@Override
	public int deleteFile(int fileId) {
		int count = 0;
		try {
			Session session = getSession();
			File deleteFile = (File) session.get(File.class, fileId);
			session.delete(deleteFile);
			count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
