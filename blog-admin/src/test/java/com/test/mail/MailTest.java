package com.test.mail;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/*
	@project:blog-admin
	@author:Techape
	@date:2017年1月24日 上午12:11:37
	@email:1195726908@qq.com
	@version:v1.0
	@description:测试邮件发送功能
*/
public class MailTest {
	
	private JavaMailSenderImpl email;
	private SimpleMailMessage message;
	
	@Before
	public void initMail() {
		email = new JavaMailSenderImpl();
		email.setHost("smtp.qq.com");
		email.setUsername("1195726908@qq.com");
		email.setPassword("ilqnthtdwbfghahb");// 授权码
		email.setPort(465);
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.ssl.enable", true);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.timeout", 25000);
		email.setJavaMailProperties(properties);
	}

	// 发送邮件
	@Test
	public void send(){
		message = new SimpleMailMessage();
		message.setFrom("1195726908@qq.com");
		message.setTo("2076741853@qq.com");
		message.setSubject("邮箱激活测试");
		message.setText("测试");
		email.send(message);
	}
}
