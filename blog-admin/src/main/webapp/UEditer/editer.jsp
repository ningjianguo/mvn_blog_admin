<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  	<link href="${ctx}/UEditer/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/UEditer/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/javascript/admin.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/UEditer/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/UEditer/umeditor.js"></script>
    <script type="text/javascript" src="${ctx}/UEditer/lang/zh-cn/zh-cn.js"></script>
    <style type="text/css">
        h1{
            font-family: "微软雅黑";
            font-weight: normal;
        }
        .btn {
            display: inline-block;
            *display: inline;
            padding: 4px 12px;
            margin-bottom: 0;
            *margin-left: .3em;
            font-size: 14px;
            line-height: 20px;
            color: #333333;
            text-align: center;
            text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
            vertical-align: middle;
            cursor: pointer;
            background-color: #f5f5f5;
            *background-color: #e6e6e6;
            background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
            background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
            background-repeat: repeat-x;
            border: 1px solid #cccccc;
            *border: 0;
            border-color: #e6e6e6 #e6e6e6 #bfbfbf;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
            border-bottom-color: #b3b3b3;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffe6e6e6', GradientType=0);
            filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
            *zoom: 1;
            -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn:hover,
        .btn:focus,
        .btn:active,
        .btn.active,
        .btn.disabled,
        .btn[disabled] {
            color: #333333;
            background-color: #e6e6e6;
            *background-color: #d9d9d9;
        }

        .btn:active,
        .btn.active {
            background-color: #cccccc \9;
        }

        .btn:first-child {
            *margin-left: 0;
        }

        .btn:hover,
        .btn:focus {
            color: #333333;
            text-decoration: none;
            background-position: 0 -15px;
            -webkit-transition: background-position 0.1s linear;
            -moz-transition: background-position 0.1s linear;
            -o-transition: background-position 0.1s linear;
            transition: background-position 0.1s linear;
        }

        .btn:focus {
            outline: thin dotted #333;
            outline: 5px auto -webkit-focus-ring-color;
            outline-offset: -2px;
        }

        .btn.active,
        .btn:active {
            background-image: none;
            outline: 0;
            -webkit-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn.disabled,
        .btn[disabled] {
            cursor: default;
            background-image: none;
            opacity: 0.65;
            filter: alpha(opacity=65);
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }
    </style>
<br/>
<span style="color: gray;font-family: '黑体';font-size: 15px;">文章标题</span><br/>
<hr style=" height:2px;border:none;border-top:2px dotted gray;width: 815px;" align="left" />
<form id="article">
<select name="articleStatu" id="select" style="height: 26px">
    <option value="images/yc.png">原创</option>
	<option value="images/zf.png">转载</option>
	<option value="images/fy.png">翻译</option>
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
	<label><input name="articleTag.articleTagName" type="radio" value="${tag}" class="tagname_other" checked="checked" />${tag}</label> 
	</c:if> 
	<c:if test="${status.count!=1}">
	<label><input name="articleTag.articleTagName" type="radio" value="${tag}" class="tagname_other"/>${tag}</label> 
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
		var select = $("#select").val();
		var content = getContent();
		var  radio = $('input:radio:checked').val();
		if(title==null||title=="")
		{
			swal("发表失败!", "文章题目还未填写", "error");
		}else if(getContentLength())
			{
				swal("发表失败!", "文章字数不能少于200", "error");
			}else{
			$.ajax({
				type:"post",
				url:"wz/addwengz",
				dataType:"json",
				data:{'title':title,'select':select,'content':content,'radio':radio},
				error: function (request, message, ex) {
				swal("发表失败!", "请重新发送", "error");
				},
				success: function (result) {
				swal("发表成功!", "恭喜！", "success");
				}
			})
		}
	})
	})
</script>