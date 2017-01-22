<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理员登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="icon" href="${ctx}/images/favicon1.ico" type="image/x-icon">
<SCRIPT src="${ctx}/javascript/jquery-3.1.1.min.js"
	type="text/javascript"></SCRIPT>
<link rel="stylesheet" href="${ctx}/css/adminlogin.css" />
<SCRIPT type="text/javascript">
	$(function() {
		//得到焦点
		$("#password").focus(function() {
			$("#left_hand").animate({
				left : "150",
				top : " -38"
			}, {
				step : function() {
					if (parseInt($("#left_hand").css("left")) > 140) {
						$("#left_hand").attr("class", "left_hand");
					}
				}
			}, 2000);
			$("#right_hand").animate({
				right : "-64",
				top : "-38px"
			}, {
				step : function() {
					if (parseInt($("#right_hand").css("right")) > -70) {
						$("#right_hand").attr("class", "right_hand");
					}
				}
			}, 2000);
		});
		//失去焦点
		$("#password").blur(function() {
			$("#left_hand").attr("class", "initial_left_hand");
			$("#left_hand").attr("style", "left:100px;top:-12px;");
			$("#right_hand").attr("class", "initial_right_hand");
			$("#right_hand").attr("style", "right:-112px;top:-12px");
		});
		$(document).keydown(function(e) {
			if (e.keyCode == 13) {
				$("#link_login").click();
			}
		});
	});
	//验证表单
	function checkForm() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "" || password == "") {
			$("#checkinfo").text("");
			$("#checkinfo").text("请输入账号或密码!");
		} else {
			$("#loginform").submit();
		}
	}
</SCRIPT>
</HEAD>
<BODY>
	<DIV class="top_div"></DIV>
	<DIV
		style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
		<DIV style="width: 165px; height: 96px; position: absolute;">
			<DIV class="tou"></DIV>
			<DIV class="initial_left_hand" id="left_hand"></DIV>
			<DIV class="initial_right_hand" id="right_hand"></DIV>
		</DIV>
		<form action="adminLogin" method="post" id="loginform">
			<P style="padding: 30px 0px 10px; position: relative;">
				<SPAN class="u_logo"></SPAN> <INPUT class="ipt" type="text"
					id="username" name="userName" placeholder="请输入用户名" value="">
			</P>
			<P style="position: relative;">
				<SPAN class="p_logo"></SPAN> <INPUT class="ipt" id="password"
					name="userPassword" type="password" placeholder="请输入密码" value="">
			</P>
			<DIV
				style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
				<P style="margin: 0px 35px 20px 45px;">
					<span id="checkinfo" style="color: red;font-size: large;">${loginerr}</span>
					<SPAN style="float: right;"> <A 
						style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
						href="javascript:checkForm()"><span id="link_login">登录</span></A>
					</SPAN>
				</P>
			</DIV>
		</form>
	</DIV>
</BODY>
</html>