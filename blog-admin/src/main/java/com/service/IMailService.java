package com.service;

import com.entity.Mail;
import com.entity.User;

/*
	@project:blog-admin
	@author:Techape
	@date:2017年1月24日 上午1:22:03
	@email:1195726908@qq.com
	@version:v1.0
	@description:邮箱接口
*/
public interface IMailService {
	
	/**
	 * 找回用户密码
	 * @param sender 发送目的邮箱
	 */
	public String questUserPassword(String sender);
	
	/**
	 * 得到最新的邮箱帐号密钥信息
	 */
	public Mail newMailInfo();
	
	/**
	 * @param userMail 用户邮箱号
	 * @return User对象
	 */
	public User queryUserInfo(String userMail);
}


