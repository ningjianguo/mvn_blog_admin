package com.entity;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String userPassword;
	private String userRealityName;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String userName, String userPassword ,String userRealityName) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRealityName = userRealityName;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the userRealityName
	 */
	public String getUserRealityName() {
		return userRealityName;
	}

	/**
	 * @param userRealityName the userRealityName to set
	 */
	public void setUserRealityName(String userRealityName) {
		this.userRealityName = userRealityName;
	}
	
}