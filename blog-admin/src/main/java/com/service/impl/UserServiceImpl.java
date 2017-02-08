package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.comm.action.BaseAction;
import com.comm.dao.BaseDaoImpl;
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
public class UserServiceImpl extends BaseDaoImpl<User> implements IUserService {

	@Resource
	IUser userDaoImpl;

	@Override
	public User login(User user) {
		return userDaoImpl.isExistUser(user);
	}

	@Override
	public Boolean updateUser(User user) {
		if ("".equals(user.getUserRealityName()) || user.getUserRealityName() == null || "".equals(user.getUserPassword())
				|| user.getUserPassword() == null){
			return false;
		}
		getSession().update(user);
		return true;
	}

}
