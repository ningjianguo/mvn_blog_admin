package com.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.comm.action.BaseAction;
import com.entity.User;
import com.service.IUserService;



@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>
{
	private static final long serialVersionUID = 1L;
	
	@Resource
	IUserService userServiceImpl;
	
	/*前往登录页面*/
	public String forwardLogin()
	{
		return SUCCESS;
	}
	
	/*登录*/
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
	
	/*登出*/
	public String adminOutLogin(){
		if(!session.isEmpty()){
			session.clear();
		}
		return "login";
	}
}
