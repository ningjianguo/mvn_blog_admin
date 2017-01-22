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
<link rel="stylesheet" href="${ctx}/javascript/frame/page/fenye.css" />
<script type="text/javascript"
	src="${ctx}/javascript/jquery-3.1.1.min.js"></script>
</head>

<body>
	<div class="bs-example">
		<div class="row">
			<c:forEach var="tag" items="${tags}">
				<div class="col-xs-6 col-md-2">
					<div class="thumbnail">
						<a href="../file/${tag.imageFileName}" class="thumbnail"> <img
							src="../file/${tag.imageFileName}" alt="image"
							 />
						</a>
					</div>
				</div>
			</c:forEach>
			<script type="text/javascript" src="${ctx}/javascript/frame/page/fenye.js"></script>
			<div id="div_pager" align="center"></div>
			<!-- <script type="text/javascript" src="${ctx}/javascript/frame/page/fenyeshow.js"></script>
			-->
			<script type="text/javascript">
				/*图片放大特效*/
				$(function(){
				$('.thumbnail a').hover(function() {
					if ($(this).find('img:animated').length) return;
					$(this).animate({marginTop: '18px'}, 300);
					$(this).find('img').animate({width: '600px'}, 300);
				}, function() {
					$(this).animate({marginTop: '0px'}, 200);
					$(this).find('img').animate({width: '150px'}, 200);
				});
			})
			
				/*分页插件的加载*/
				var folderId = ${iPage.folderId};
				var totalRecords = ${iPage.totalRecords};
				var totalPage = ${iPage.totalPage};
				var pageNo = ${iPage.pageNo}; //这里设置参数名
				if (!pageNo) {
					pageNo = 1;
				}
				//生成分页控件 根据分页的形式在这里设置
				kkpager.init({
					pno : pageNo,
					//总页码
					total : totalPage,
					//总数据条数
					totalRecords : totalRecords,
					getLink : function(n) {
						return "lookImage?imagePageNo=" + n + "&imageFolderId=" + folderId; //参数名跟上面相同
					}
				});
				kkpager.generPageHtml();
			</script>
		</div>
	</div>
</body>
</html>
