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
<link rel="stylesheet"
	href="${ctx}/javascript/frame/bootstrap/css/bootstrap.min.css">
<script src="${ctx}/javascript/frame/jQuery-2.2.0.min.js"></script>
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
	//弹出密码找回框
	function lookForPassword(){
		$('#myModal').modal();
	}
	//获取验证码：
	function getCheckCode(){
		var userEmail = $('#userEmail').val();
		if(userEmail!=""&&userEmail!=null){
		$.ajax({
			type:"post",
			url:"sendCheckCode",
			data:{"userEmail":userEmail},
			async:false,
			success:function(data){
				var obj = eval("("+data+")");
				$("#modalInfo").text(obj.info);
			}
		})
		}
	}
	//验证验证码
	function checkCode(){
		var userMailPsw = $("#userMailPsw").val();
		var userEmail = $("#userEmail").val();
		if(userEmail==""||userEmail==null){
			$("#modalInfo").text("邮箱号未填！");
		}
		if(userMailPsw==""||userMailPsw==null){
			$("#modalInfo").text("验证码未填！");
		}
		if(userMailPsw!=null && userMailPsw!="" && userEmail!=null &&userEmail!=""){
			$("#codeForm").submit();
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
				<P style="margin-right: 5px;">
					<span id="checkinfo" style="color: red;font-size: large;">${loginerr}</span>
					<SPAN style="float: right;"> <A
						style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
						href="javascript:checkForm()"><span id="link_login">登录</span></A><a
						href="javascript:lookForPassword()" style="margin-left: 5px;">密码找回</a>
					</SPAN>
				</P>
			</DIV>
		</form>
	</DIV>
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="quxiao close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title text-center">管理员密码找回</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" action="checkCode" id="codeForm">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">验证邮箱:</label>
							<div class="col-sm-7">
								<input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="已保留的邮箱号">
							</div>
							<button type="button" class="btn btn-info" onclick="getCheckCode()">获取验证码</button>
						</div>
						<div class="form-group" id="form-pwd">
							<label for="inputPassword3" class="col-sm-3 control-label">验证码:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="userMailPsw" id="userMailPsw"
									placeholder="验证码">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<p class="text-right"><span id="modalInfo" style="font-size: 15px"></span>
					<button type="button" class="quxiao btn btn-default"
						data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" onclick="checkCode()">验证</button></p>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</BODY>
<script src="${ctx}/javascript/frame/bootstrap/js/bootstrap.min.js"></script>
</html>