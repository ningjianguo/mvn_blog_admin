package com.util;

import java.util.ResourceBundle;

/*
	@project:ningjianguo
	@author:Techape
	@date:2016年12月27日 下午10:44:38
	@email:1195726908@qq.com
	@version:v1.0
	@description:获取文件路径
 */
public class FilePathUtil { 
	static ResourceBundle rb ;
	
	static{
		rb = ResourceBundle.getBundle("file");
	}
	
	/**
	 * 由键获得对应的值
	 */
	public static String getValue(String key){
		return rb.getString(key);
	}
	
}


