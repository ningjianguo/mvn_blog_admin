<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>lookImage</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${ctx}/javascript/frame/bootstrap/css/bootstrap.min.css" />
</head>

<body>
	<div class="bs-example">
		<div class="row">
		<c:forEach var="tag" items="${tags}">
			<div class="col-xs-6 col-md-2">
				<div class="thumbnail">
					<a href="toLookImage?imageFolder.imageFolderId=${tag.imageFolderId}" class="thumbnail"> <img
						src="images/imageFolder.png" alt="imageFolder">
					</a>
					<div class="caption">
						<h3>${tag.imageFolderName}</h3>
						<p>${tag.imageFolderDescription}</p>
					</div>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>
</body>
</html>
