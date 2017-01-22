<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  	<link href="${ctx}/UEditer/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
  	<link href="${ctx}/css/frame/uediter.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/UEditer/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/javascript/admin.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/UEditer/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/UEditer/umeditor.js"></script>
    <script type="text/javascript" src="${ctx}/UEditer/lang/zh-cn/zh-cn.js"></script>
<br/>
<span style="color: gray;font-family: '黑体';font-size: 15px;">文章标题</span><br/>
<hr style=" height:2px;border:none;border-top:2px dotted gray;width: 815px;" align="left" />
<form id="articleForm" action="saveArticle" method="post">
<input type="hidden" id="articleText" name="articleText" value=""/>
<select name="articleType" id="select" style="height: 26px">
    <option value="1">原创</option>
	<option value="2">转载</option>
	<option value="3">翻译</option>
</select>
<input type="text" name="articleTitle" size="40" value="" id="title"/>
<br/>
<br/>
<span style="color: gray;font-family: '黑体';font-size: 15px;">文章内容</span><br/>
<hr style=" height:2px;border:none;border-top:2px dotted gray;width: 815px;" align="left" />
<!--style给定宽度可以影响编辑器的最终宽度-->
<script type="text/plain" id="myEditor" style="width:815px;height:240px;">
<p>我得说点什么........</p>
</script>
<div>
    <h3 id="focush2"></h3>
</div>
<script type="text/javascript" src="${ctx}/UEditer/editer.js">
</script>

<span style="color: gray;font-family: '黑体';font-size: 15px;">文章标签</span><br/>
<hr style=" height:2px;border:none;border-top:2px dotted gray;width: 815px;" align="left" />
<input type="hidden" name="articleText" id="article_">
<div style="float:left; width: 100%">
<c:forEach var="tag" items="${tags}" varStatus="status">
	<c:if test="${status.count==1}">
	<label><input name="articleTag.articleTagId" type="radio" value="${tag.articleTagId}" class="tagname_other" checked="checked" />${tag.articleTagName}</label> 
	</c:if> 
	<c:if test="${status.count!=1}">
	<label><input name="articleTag.articleTagId" type="radio" value="${tag.articleTagId}" class="tagname_other"/>${tag.articleTagName}</label> 
	</c:if> 
</c:forEach>
	<label><input name="articleTag.articleTagName" type="radio" value="" class="tagname_other" id="tagname_other"/>其它</label><input type="hidden" value="" id="othertag" placeholder="填写标签名" size="10" style="height: 20px; margin-left: 3px;"/> 
<hr style=" height:2px;border:none;border-top:2px dotted gray;width: 815px;" align="left" />
</div>
<div style="margin-top: 5px;"><input type="button" class="btn btn-info" value="发布文章" id="tj" style="width: 815px"/>&nbsp;<span style="color: red;font-family: 黑体;font-size: 12px">文章字数不得少于200</span></div>
</form>
<link rel="stylesheet" type="text/css" href="${ctx}/UEditer/sweetalert.css">
<script type="text/javascript" src="${ctx}/UEditer/sweetalert.min.js"></script>
<script type="text/javascript">
	$(function(){
	$("#tj").click(function(){
		var title = $("#title").val();
		var content = getContent();
		if(title==null||title=="")
		{
			swal("发表失败!", "文章题目还未填写", "error");
		}else if(getContentLength())
			{
				swal("发表失败!", "文章字数不能少于200", "error");
			}else{
			$("#articleText").val(content);
			$("#articleForm").submit();
		}
	})
	})
</script>