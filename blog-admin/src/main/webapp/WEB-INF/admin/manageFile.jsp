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
			url : "loadFile",
			pageSize : 5,//默认选择的分页是每页5行数据  
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合  
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		});
	})
</script>
<body>
	<table id="dg" title="文件管理" class="easyui-datagrid" style="height:540"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="fileName" width="30" align="center">文件名称</th>
				<th field="fileUploadTime" width="20" align="center">文件上传时间</th>
				<th field="fileDownloadCount" width="20" align="center">文件下载次数</th>
				<th field="fileStatu" width="20" align="center">文件状态</th>
				<th field="fileZipName" width="20" align="center">文件压缩包名</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="forwardUploadFile" class="easyui-linkbutton" iconCls="icon-add"
			plain="true">上传文件</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editFile()">编辑文件</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyFile()">移除文件</a>
	</div>

	<div id="dlg" class="easyui-dialog" style="width:400px" closed="true"
		buttons="#dlg-buttons" data-options="modal:true">
		<form id="fm" method="post" novalidate
			style="margin:0;padding:20px 50px">
			<div
				style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">文件信息</div>
				<input name="fileId" id="fileId" type="hidden"/>
			<div style="margin-bottom:10px">
				<input name="fileName" id="fileName" class="easyui-textbox"
					required="true" label="文件名称:" style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="fileStatu" class="easyui-combobox" required="true"
					editable="false" id="fileStatu" label="发布状态:" style="width:100%"
					data-options="valueField:'statuId',textField:'statuName',url:'loadStatuFile',panelHeight:'auto'">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveFile()" style="width:90px">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width:90px">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function editFile() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('center').dialog('setTitle',
						'编辑文件信息');
				$('#fm').form('load', row);
			}
		}
		function saveFile() {
			switch ($('#fileStatu').combobox('getText')) {
			case "未发布":
				$('#fileStatu').val(1);
				break;
			case "已发布":
				$('#fileStatu').val(2);
				break;
			}
			$.ajax({
				type:"post",
				url:"updateFile",
				dataType:"json",
				cache:false,
				data:{
					"fileId":$('#fileId').val(),
					"fileName":$('#fileName').val(),
					"fileStatu":$('#fileStatu').val()
					},
				success:function(data){
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			})
		}
		function destroyFile() {
			var row = $('#dg').datagrid('getSelected');
			$.extend($.messager.defaults,{
                ok:"确定",
                cancel:"取消",
                modal:true
            });
			if (row) {
				$.messager.confirm('警告',
						"您确定要移除: "+row.fileName+" 吗?",
						function(r) {
							if (r) {
								$.post('deleteFile', {
									fileId : row.fileId
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
