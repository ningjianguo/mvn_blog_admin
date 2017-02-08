package com.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.comm.action.BaseAction;
import com.entity.Mail;
import com.entity.User;

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
public class IndexAction extends BaseAction<Mail> {
	private static final long serialVersionUID = 1L;
	private String userEmail;
	private String userMailPsw;

	/* 首页 */
	public String index() {
		return SUCCESS;
	}

	/* 欢迎页 */
	public String welcome() {
		return SUCCESS;
	}

	/* 发送邮件 */
	public String sendCheckCode() {
		printJsonStringToBrowser(mailServiceImpl.questUserPassword(userEmail));
		return null;
	}

	/* 检查验证码是否正确 */
	public String checkCode() {
		String uuid = (String) session.get("uuid");
		User user = mailServiceImpl.queryUserInfo(userEmail);
		if (uuid.equals(userMailPsw)) {
			if (user != null) {
				// 登录成功则把user对象放到session中保存
				session.put("admin", user);
				return "index";
			}
		}
		request.setAttribute("loginerr", "验证失败!");
		return "login";
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserMailPsw(String userMailPsw) {
		this.userMailPsw = userMailPsw;
	}

}
