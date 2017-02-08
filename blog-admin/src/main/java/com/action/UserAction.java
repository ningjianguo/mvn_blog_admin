package com.action;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.comm.action.BaseAction;
import com.entity.User;



@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>
{
	private static final long serialVersionUID = 1L;
	
	private String userTargetMail;
	
	/**
	 * 前往登录页面
	 */
	public String forwardLogin()
	{
		return SUCCESS;
	}
	
	/**
	 * 登入
	 */
	@SuppressWarnings("unchecked")
	public String adminLogin(){
		User user = userServiceImpl.login(getModel());
		 if(getModel().getUserName() == null || getModel().getUserPassword() == null){
			request.setAttribute("loginerr", "请登录后再进入后台管理系统!");
			return "login";
		}else if(user != null){
			//登录成功则把user对象放到session中保存
			session.put("admin",user);
			return "index";
		}else{
			request.setAttribute("loginerr", "账号或密码错误!");
			return "login";
		}
	}
	
	/**
	 * 登出
	 */
	public String adminOutLogin(){
		if(!session.isEmpty()){
			session.clear();
		}
		return "login";
	}
	
	/**
	 * 修改用户信息
	 */
	public String updateUserInfo(){
		if(userServiceImpl.updateUser(getModel())){
			request.setAttribute("info", "用户信息修改成功!");
			return SUCCESS;
		}
		request.setAttribute("info", "用户信息修改失败!");
		return ERROR;
	}
	
	/**
	 * 前往修改密码界面
	 */
	public String forwardManageUser(){
		return SUCCESS;
	}
	public void setUserTargetMail(String userTargetMail) {
		this.userTargetMail = userTargetMail;
	}
	
}
