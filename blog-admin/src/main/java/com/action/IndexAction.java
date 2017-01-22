package com.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月21日 下午10:07:12
	@email:1195726908@qq.com
	@version:v1.0
	@description:前往首页
 */
@Controller
@Scope("prototype")
public class IndexAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	/*首页*/
	public String index(){
		return SUCCESS;
	}
	/*欢迎页*/
	public String welcome(){
		return SUCCESS;
	}
}


