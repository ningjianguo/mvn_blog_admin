package com.entity;

/**
 * Mail entity. @author MyEclipse Persistence Tools
 */

public class Mail implements java.io.Serializable {

	// Fields

	private Integer mailId;
	private User user;
	private String mailUserName;
	private String mailUserPassword;
	// Constructors

	/** default constructor */
	public Mail() {
	}

	/** full constructor */
	public Mail(User user, String mailUserName,String mailUserPassword) {
		this.user = user;
		this.mailUserName = mailUserName;
		this.mailUserPassword = mailUserPassword;
	}

	// Property accessors

	public Integer getMailId() {
		return this.mailId;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMailUserName() {
		return this.mailUserName;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public String getMailUserPassword() {
		return mailUserPassword;
	}

	public void setMailUserPassword(String mailUserPassword) {
		this.mailUserPassword = mailUserPassword;
	}

}