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
			url : "loadVideo",
			pageSize : 5,//默认选择的分页是每页5行数据  
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合  
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		});
	})
</script>
<body>
	<table id="dg" title="视频管理" class="easyui-datagrid" style="height:540"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="videoName" width="30" align="center">视频名称</th>
				<th field="videoTag.videoTagName" width="20" align="center">视频标签</th>
				<th field="videoUploadEditer" width="20" align="center">视频上传者</th>
				<th field="videoUploadTime" width="20" align="center">视频上传时间</th>
				<th field="videoDownloadCount" width="20" align="center">视频下载次数</th>
				<th field="videoStatu" width="20" align="center">视频状态</th>
				<th field="videoFileName" width="20" align="center">视频文件名称</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="forwardUploadVideo" class="easyui-linkbutton" iconCls="icon-add"
			plain="true">上传视频</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editVideo()">编辑视频</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyVideo()">移除视频</a>
	</div>

	<div id="dlg" class="easyui-dialog" style="width:400px" closed="true"
		buttons="#dlg-buttons" data-options="modal:true">
		<form id="fm" method="post" novalidate
			style="margin:0;padding:20px 50px">
			<div
				style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">视频信息</div>
				<input name="videoId" id="videoId" type="hidden"/>
			<div style="margin-bottom:10px">
				<input name="videoName" id="videoName" class="easyui-textbox"
					required="true" label="视频名称:" style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="videoTag.videoTagName" class="easyui-combobox" id="videoTag"
					required="true" editable="false" label="视频标签:" style="width:100%"
					data-options="valueField:'tagId',textField:'tagName',url:'loadTagVideo',panelHeight:'auto'">
			</div>
			<div style="margin-bottom:10px">
				<input name="videoStatu" class="easyui-combobox" required="true"
					editable="false" id="videoStatu" label="发布状态:" style="width:100%"
					data-options="valueField:'statuId',textField:'statuName',url:'loadStatuVideo',panelHeight:'auto'">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveVideo()" style="width:90px">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width:90px">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function editVideo() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('center').dialog('setTitle',
						'编辑视频信息');
				$('#fm').form('load', row);
			}
		}
		function saveVideo() {
			switch ($('#videoStatu').combobox('getText')) {
			case "未发布":
				$('#videoStatu').val(1);
				break;
			case "已发布":
				$('#videoStatu').val(2);
				break;
			}
			$.ajax({
				type:"post",
				url:"updateVideo",
				dataType:"json",
				cache:false,
				data:{
					"videoId":$('#videoId').val(),
					"videoName":$('#videoName').val(),
					"videoTag.videoTagName":$('#videoTag').combobox('getText'),
					"videoStatu":$('#videoStatu').val()
					},
				success:function(data){
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			})
		}
		function destroyVideo() {
			var row = $('#dg').datagrid('getSelected');
			$.extend($.messager.defaults,{
                ok:"确定",
                cancel:"取消",
                modal:true
            });
			if (row) {
				$.messager.confirm('警告',
						"您确定要移除: "+row.videoName+" 吗?",
						function(r) {
							if (r) {
								$.post('deleteVideo', {
									videoId : row.videoId
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
