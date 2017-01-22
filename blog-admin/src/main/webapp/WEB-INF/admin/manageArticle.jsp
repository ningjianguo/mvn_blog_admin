<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>manageArticle</title>
<link rel="stylesheet" href="${ctx}/easyui/thems/bootstrap/easyui.css" />
<link rel="stylesheet" href="${ctx}/easyui/css/icon.css" />
<link rel="stylesheet" href="${ctx}/easyui/css/color.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/easyui/css/user_defined.css">
<script type="text/javascript" src="${ctx}/easyui/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/easyui/js/jquery.easyui.min.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			url : "loadArticle",
			pageSize : 15,//默认选择的分页是每页5行数据  
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合  
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		});
	})
</script>
<body>
	<table id="dg" title="综合管理" class="easyui-datagrid" style="height:540"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="articleTitle" width="30" align="center">文章标题</th>
				<th field="articleTag.articleTagName" width="20" align="center">文章标签</th>
				<th field="articleType" width="20" align="center">文章类型</th>
				<th field="articleEditer" width="20" align="center">文章作者</th>
				<th field="articleTime" width="20" align="center">发表时间</th>
				<th field="articleStatu" width="20" align="center">发布状态</th>
				<th field="articleReaderCount" width="10" align="center">阅读次数</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="editerArticle" class="easyui-linkbutton" iconCls="icon-add"
			plain="true">添加博文</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editArticle()">编辑博文</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyArticle()">移除博文</a>
	</div>

	<div id="dlg" class="easyui-dialog" style="width:400px" closed="true"
		buttons="#dlg-buttons" data-options="modal:true">
		<form id="fm" method="post" novalidate
			style="margin:0;padding:20px 50px">
			<div
				style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">文章信息</div>
				<input name="articleId" id="articleId" type="hidden"/>
			<div style="margin-bottom:10px">
				<input name="articleTitle" id="articleTitle" class="easyui-textbox"
					required="true" label="文章标题:" style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="articleTag.articleTagName" class="easyui-combobox" id="articleTag"
					required="true" editable="false" label="文章标签:" style="width:100%"
					data-options="valueField:'tagId',textField:'tagName',url:'loadTagArticle',panelHeight:'auto'">
			</div>
			<div style="margin-bottom:10px">
				<input name="articleType" class="easyui-combobox" required="true"
					editable="false" id="articleType" label="文章类型:" style="width:100%"
					data-options="textField:'typeName',valueField:'typeId',url:'loadTypeArticle',panelHeight:'auto'">
			</div>
			<div style="margin-bottom:10px">
				<input name="articleStatu" class="easyui-combobox" required="true"
					editable="false" id="articleStatu" label="发布状态:" style="width:100%"
					data-options="valueField:'statuId',textField:'statuName',url:'loadStatuArticle',panelHeight:'auto'">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveArticle()" style="width:90px">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width:90px">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function editArticle() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('center').dialog('setTitle',
						'编辑文章');
				$('#fm').form('load', row);
			}
		}
		function saveArticle() {
			switch ($('#articleType').combobox('getText')) {
			case "原创":
				$('#articleType').val(1);
				break;
			case "转载":
				$('#articleType').val(2);
				break;
			case "翻译":
				$('#articleType').val(3);
				break;
			}
			switch ($('#articleStatu').combobox('getText')) {
			case "已发布":
				$('#articleStatu').val(2);
				break;
			case "未发布":
				$('#articleStatu').val(1);
				break;
			}
			$.ajax({
				type:"post",
				url:"updateArticle",
				dataType:"json",
				cache:false,
				data:{
					"articleId":$('#articleId').val(),
					"articleTitle":$('#articleTitle').val(),
					"articleTag.articleTagName":$('#articleTag').combobox('getText'),
					"articleType":$('#articleType').val(),
					"articleStatu":$('#articleStatu').val()
					},
				success:function(data){
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			})
		}
		function destroyArticle() {
			var row = $('#dg').datagrid('getSelected');
			$.extend($.messager.defaults,{
                ok:"确定",
                cancel:"取消",
                modal:true
            });
			if (row) {
				$.messager.confirm('警告',
						"您确定要删除: "+row.articleTitle+" 吗?",
						function(r) {
							if (r) {
								$.post('deleteArticle', {
									articleId : row.articleId
								}, function(result) {
									if (result.success) {
										$('#dg').datagrid('reload'); // reload the user data
									} else {
										$.messager.show({ // show error message
											title : '错误',
											msg : '删除失败，请重新删除!'
										});
									}
								}, 'json');
							}
						});
			}
		}
	</script>
</body>
</html>
