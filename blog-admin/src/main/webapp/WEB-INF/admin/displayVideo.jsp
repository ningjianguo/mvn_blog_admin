<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>displayVideo</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/frame/fz-video.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/frame/font/iconfont.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/frame/modernizr.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/frame/navigation.css">
<script type="text/javascript"
	src="${ctx}/javascript/frame/jQuery-2.2.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/frame/modernizr.js"></script>
</head>
<style>
.ctge {
	font-family: "黑体";
	font-weight: bolder;
}
</style>
<script>
	$(function() {
		if ('${param.videoId}' == "") {
			video($("#vn").text())
		} else {
			video('${param.videoId}');
		}
		//获取视频目录项
		$(".item-has-children")
				.click(
						function() {
							var p_category = $(this).find("a").attr("id");
							var ul;
							if ($(this).find("ul").text() == "") {
								$
										.ajax({
											type : "post",
											url : "videoCategory",
											data : "videoTag.videoTagId="+ p_category,
											async : false, //设置同步请求
											success : function(data) {
												var categories = eval("("
														+ data.videoCategories + ")");
												ul = "<ul class='sub-menu'>";
												for (var i = 0; i < categories.length; i++) {
													ul += "<li class='ctge'><a href='javascript:video("
															+ '"'
															+ categories[i].videoId
															+ '"'
															+ ")'><span style='margin-right: 20px;'>&gt;</span>"
															+ categories[i].videoName
															+ "</a></li>"
												}
												ul += "</ul"; 
											}
										});
												$(this).append(ul);
							}
						}); 
	});
	/*视频播放*/
	function video(videoId) {
		var videoName;
		var videoFileName;
		$.ajax({
			type:"post",
			url:"getVideoName",
			data:"videoId="+videoId,
			success:function(data){
				videoId = eval("("+data.videoName+")")[0][0];
				videoName = eval("("+data.videoName+")")[0][1];
				videoFileName = eval("("+data.videoName+")")[0][2];
				var fz_video = new createVideo("video", //容器的id
				{
					url : null, //视频地址
					autoplay : true
				//是否自动播放
				});
				fz_video.setUrl(getRootPath() + "/file/" + videoFileName +".mp4");
				$("#videoTitle").text(videoName);
			}
		})
	}

	/*获得项目根目录*/
	function getRootPath() {
		//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp  
		var curWwwPath = window.document.location.href;
		//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp  
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		//获取主机地址，如： http://localhost:8083  
		var localhostPaht = curWwwPath.substring(0, pos);
		return (localhostPaht);
	}

</script>

<body>
	<div style="display: none;" id="vn">${requestScope.videoId}</div>
	<header> <span id="videoTitle"
		style="color: #fff;font-weight: bolder; margin-left: 65px; font-size: large;">${requestScope.videoName}</span>
	<a id="cd-menu-trigger" href="#0"><span class="cd-menu-icon"></span></a>
	</header>
	<main class="cd-main-content"> <!-- put your content here -->
	<div id="video"></div>
	</main>
	<!-- cd-main-content -->

	<nav id="cd-lateral-nav">
	<ul class="cd-navigation">
		<c:forEach var="tag" items="${tags}">
			<li class="item-has-children"><a href="javascript:void(0)" id="${tag.videoTagId}">${tag.videoTagName}<span
					style="margin-left: 80px;">&lt;</span></a></li>
		</c:forEach>
		<!-- item-has-children -->
	</ul>
	<!-- cd-navigation --> </nav>

</body>
<script type="text/javascript" src="${ctx}/javascript/frame/fz-video.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/frame/navigation.js"></script>
</head>
</html>
