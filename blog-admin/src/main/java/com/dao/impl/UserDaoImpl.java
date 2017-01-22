package com.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.comm.dao.BaseDaoImpl;
import com.dao.IUser;
import com.entity.User;

/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月19日 下午10:46:42
	@email:1195726908@qq.com
	@version:v1.0
	@description:用户表的Dao实现类
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUser {

	@Override
	public User isExistUser(User validateUser) {
			Query query = getSession().createQuery("from User where userName=? and userPassword=?")
						.setString(0, validateUser.getUserName()).setString(1, validateUser.getUserPassword());
			User user = (User) query.uniqueResult();
		return user == null ? null:user;
	}
}


