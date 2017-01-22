package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.IUser;
import com.entity.User;
import com.service.IUserService;

/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月19日 下午11:03:27
	@email:1195726908@qq.com
	@version:v1.0
	@description:用户服务层接口实现类
 */
@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	IUser userDaoImpl;
	
	@Override
	public User login(User user) {
		return userDaoImpl.isExistUser(user);
	}

}


