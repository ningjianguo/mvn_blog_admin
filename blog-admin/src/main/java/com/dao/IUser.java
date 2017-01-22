package com.dao;

import com.entity.User;

public interface IUser {
	/*用户是否存在*/
	public User isExistUser(User user);
}