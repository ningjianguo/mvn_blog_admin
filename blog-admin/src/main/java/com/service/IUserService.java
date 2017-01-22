package com.service;

import com.entity.User;

/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月19日 下午10:59:58
	@email:1195726908@qq.com
	@version:v1.0
	@description:用户服务层接口
 */
public interface IUserService {
	/*用户登录*/
	public User login(User user);
}


