package com.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.comm.dao.BaseDaoImpl;
import com.entity.Mail;
import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.service.IMailService;

import net.sf.json.JSONObject;

/*
	@project:blog-admin
	@author:Techape
	@date:2017年1月24日 上午1:23:17
	@email:1195726908@qq.com
	@version:v1.0
	@description:邮箱实现类
*/
@Service
public class MailServiceImpl extends BaseDaoImpl<Mail> implements IMailService {

	@Override
	public String questUserPassword(String sender) {
		JSONObject jobj = new JSONObject();
		Mail mail = newMailInfo();
		JavaMailSenderImpl email = null;
		SimpleMailMessage message = null;
		email = new JavaMailSenderImpl();
		email.setHost("smtp.qq.com");
		email.setUsername(mail.getMailUserName());// 发送者邮箱帐号
		email.setPassword(mail.getMailUserPassword());// 发送者邮箱密码
		email.setPort(465);
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.ssl.enable", true);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.timeout", 25000);
		email.setJavaMailProperties(properties);
		message = new SimpleMailMessage();
		if (isUserEmailExist(sender)) {
			message.setFrom(mail.getMailUserName());
			message.setTo(sender);
			message.setSubject("密码找回服务");
			message.setText("密码找回密钥:" + randomUUID() + " 请复制密钥到验证码框，注意保密哦！");
			email.send(message);
			return jobj.accumulate("info", "密钥已发送至您的邮箱,请注意查收！").toString();
		} else {
			return jobj.accumulate("info", "邮箱未找到").toString();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Mail newMailInfo() {
		List<Mail> mails = getSession().createQuery("from Mail order by mailId desc").list();
		return mails.get(0);
	}

	/**
	 * 生成随机密钥
	 */
	@SuppressWarnings("unchecked")
	public String randomUUID() {
		String uuid = UUID.randomUUID().toString();
		Map session = (Map) ActionContext.getContext().getSession();
		session.put("uuid", uuid);
		return uuid;
	}

	/**
	 * 验证用户邮箱是否存在
	 * 
	 * @return false 不存在
	 * @return true 存在
	 */
	private boolean isUserEmailExist(String email) {
		Mail userMail = (Mail) getSession().createQuery("from Mail where mailUserName=?").setString(0, email)
				.uniqueResult();
		return userMail == null ? false : true;
	}

	@Override
	public User queryUserInfo(String userMail) {
		Mail mail = (Mail) getSession().createQuery("from Mail where mailUserName=?").setString(0, userMail)
				.uniqueResult();
		if(mail!=null){
			User user = mail.getUser();
			return mail.getUser();
		}
		return null;
	}

}
