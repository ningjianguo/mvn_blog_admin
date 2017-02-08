<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>user manage</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${ctx}/javascript/frame/bootstrap/css/bootstrap.min.css">
</head>

<body>
	<br/>
	<form class="form-horizontal" action="updateUserInfo" method="post">
		<input type="hidden" name="userId" value="${admin.userId}">
		<input type="hidden" name="userName" value="${admin.userName}">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-4 control-label">用户名</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" disabled="disabled" value="${admin.userName}">
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-4 control-label">姓名</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="userRealityName" name="userRealityName" value="${admin.userRealityName}">
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-4 control-label">密码</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="userPassword" name="userPassword"
					placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-6 col-sm-10">
				<button type="submit" class="btn btn-info">修改</button>
			</div>
		</div>
	</form>
	<p class="bg-info" style="text-align: center;">${info}</p>
</body>
</html>
