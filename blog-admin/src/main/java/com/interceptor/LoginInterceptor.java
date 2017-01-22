package com.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
 @project:ningjianguo
 @author:Techape
 @date:2016年12月20日 下午10:28:56
 @email:1195726908@qq.com
 @version:v1.0
 @description:登录拦截器
 */
public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;


	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		// 取得Session。
		ActionContext ac = invocation.getInvocationContext();
		Map session = (Map) ac.get(ServletActionContext.SESSION);
		if (session.isEmpty()) {
			ac.put("loginerr", "请登录后再进入后台管理系统!");
			return "login";
		} else {
			User user = (User) session.get("admin");
			if (user== null) {
				ac.put("loginerr", "请登录后再进入后台管理系统!");
				return "login";
			} else {
				return invocation.invoke();
			}
		}
	}

}
