<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>forwardVideo</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${ctx}/javascript/frame/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${ctx}/javascript/frame/bootstrap/css/fileinput.min.css">
<script type="text/javascript"
	src="${ctx}/javascript/frame/jQuery-2.2.0.min.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		initFileInput("input-1", "#");
		initForm();
	});
	//初始化fileinput控件（第一次初始化）
	function initFileInput(ctrlName, uploadUrl) {
		var control = $('#' + ctrlName);
		control.fileinput({
			language : 'zh', //设置语言
			//uploadUrl : uploadUrl, //上传的地址
			allowedFileExtensions : [ 'mp4' ],//接收的文件后缀
			showUpload : false, //是否显示上传按钮
			showCaption : false,//是否显示标题
			browseClass : "btn btn-primary", //按钮样式             
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			dropZoneEnabled : false,
			maxFileSize : 1048576,//单位为kb，如果为0表示不限制文件大小
		//enctype: 'multipart/form-data',
		});
	}

	//文件上传表单提交
	function initForm() {
		$("#sbm").attr("disabled", "disabled");
		document.getElementById("videoName").oninput = function() {
			if ($("#videoName").val() == "") {
				$("#sbm").attr("disabled", "disabled");
			} else {
				$("#sbm").removeAttr("disabled");
			}
		}
	}
	function submitForm() {
		$("#fileForm").submit();
	}
</script>
<body style="margin-top: 10px;">
	<form class="form-horizontal" id="fileForm" role="form"
		action="uploadVideo" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="videoName" class="col-sm-offset-1 col-sm-2 control-label">视频名称:</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="videoName"
					name="videoName" placeholder="请输入视频名">
			</div>
		</div>
		<div class="form-group">
			<label for="exampleInputFile"
				class="col-sm-offset-1 col-sm-2 control-label">视频路径:</label>
			<div class="col-sm-5">
				<input id="input-1" type="file" name="video">
				<p class="help-block">只允许上传后缀名为.mp4格式的视频</p>
			</div>
		</div>
		<div class="form-group">
			<label for="videoTag" class="col-sm-offset-1 col-sm-2 control-label">视频标签:</label>
			<div class="col-sm-9">
				<c:forEach var="tag" items="${tags}" varStatus="status">
					<c:if test="${status.count==1}">
						<label class="checkbox-inline"> <input type="radio"
							name="videoTag.videoTagId" id="optionsRadios3"
							value="${tag.videoTagId}" checked>${tag.videoTagName}
						</label>
					</c:if>
					<c:if test="${status.count!=1}">
						<label class="checkbox-inline"> <input type="radio"
							name="videoTag.videoTagId" id="optionsRadios3"
							value="${tag.videoTagId}">${tag.videoTagName}
						</label>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-5">
				<a href="javascript:submitForm()" class="btn btn-primary"
					style="width: 445px;" id="sbm">上传</a>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript"
	src="${ctx}/javascript/frame/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/frame/bootstrap/js/fileinput.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/frame/bootstrap/js/zh.js"></script>
</html>
