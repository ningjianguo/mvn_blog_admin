package com.test.bean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
	@project:blog-admin
	@author:Techape
	@date:2017年1月22日 下午10:21:14
	@email:1195726908@qq.com
	@version:v1.0
	@description:
*/
public class CrudTest {
	private Session session;
	
	@Before
	public void createSession(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	
	public void testQuery(){
		System.out.println(session);
	}
	
	@After
	public void destroySession(){
		session.close();
	}
}


