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
		//初始化数据
		loadImageData(0); //加载全部数据、对相册不分类
		//动态加载数据
		$("#iFolder").combobox({
			onChange : function(record) {
				loadImageData($(this).combobox('getValue'));
			}
		});

	});

	//加载表格数据、加载对应相册的数据
	function loadImageData(folderId) {
		$('#dg').datagrid({
			url : "loadImage?imageFolder.imageFolderId=" + folderId,
			pageSize : 20, //默认选择的分页是每页5行数据  
			pageList : [ 20, 25, 30, 40 ], //可以选择的分页集合  
			nowrap : true, //设置为true，当数据长度超出列宽时将会自动截取  
			//设置复选框属性
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : true
		});
	}

	//图片添加路径
	function imgFormatter(value, row, index) {
		if ('' != value && null != value) {
			return "<img onclick=displayImage(\"" + value + "\") style='width:50px;' src='../file/" + value + "' title='点击查看图片'/>";
		}
	}
	function displayImage(img) {
		var simg = "../file/" + img;
		$('#displayImage').dialog({
			title : '照片预览',
			width : 380,
			height : 500,
			resizable : true,
			closed : false,
			cache : false,
			modal : true
		});
		$("#simg").attr("src", simg).css('display', 'block');
		;
	}
</script>
<body>
	<table id="dg" title="照片管理" class="easyui-datagrid" style="height:540"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th
					data-options="field:'imageFileName', width:4, align:'center',formatter:imgFormatter">照片预览</th>
				<th field="imageFolder.imageFolderName" width="20" align="center">所属相册</th>
				<th field="imageUploadTime" width="20" align="center">上传时间</th>
				<th field="imageFolder.imageFolderStatu" width="20" align="center">照片状态</th>
				<th field="imageId" checkbox="true"></th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="forwardUploadImage" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">上传照片</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editImage()">编辑照片</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyImage()">移除照片</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-large-picture" plain="true"
			onclick="editImageFolder()">编辑相册</a> <input name="imageFolderName"
			class="easyui-combobox" id="iFolder" required="true" editable="false"
			style="width:100px" value="全部"
			data-options="valueField:'folderId',textField:'folderName',url:'loadImageFolder',panelHeight:'auto'" />
	</div>
	<div id="displayImage">
		<img id="simg" width="365px" height="460px" style="display: none;">
	</div>
	<div id="dlg" class="easyui-dialog" style="width:400px" closed="true"
		buttons="#dlg-buttons" data-options="modal:true">
		<form id="fm" method="post" novalidate
			style="margin:0;padding:20px 50px">
			<div
				style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">照片信息</div>
			<input name="imageId" id="imageId" type="hidden" />
			<div style="margin-bottom:10px">
				<input name="imageFolder.imageFolderName" class="easyui-combobox"
					id="imageFolder" required="true" editable="false" label="所属相册:"
					style="width:100%"
					data-options="valueField:'folderId',textField:'folderName',url:'loadImageFolder',panelHeight:'auto'">
			</div>
			<div style="margin-bottom:10px">
				<input name="imageFolder.imageFolderStatu" class="easyui-combobox"
					required="true" editable="false" id="imageStatu" label="发布状态:"
					style="width:100%" readonly="readonly">
			</div>
		</form>
	</div>
	<div id="dlg_imageFolder_edite" class="easyui-dialog"
		style="width:400px" closed="true" buttons="#dlg-buttons_folder_edite"
		data-options="modal:true">
		<form id="imageFolderForm" method="post" novalidate
			style="margin:0;padding:20px 50px">
			<div style="margin-bottom:10px">
				<input name="imageFolder.imageFolderName" class="easyui-combobox"
					id="imageFolder_edite" required="true" editable="false"
					label="相册名称:" style="width:100%"
					data-options="valueField:'folderId',textField:'folderName',url:'loadImageFolder',panelHeight:'auto'">
			</div>
			<div style="margin-bottom:10px">
				<input name="imageFolder.imageFolderStatu" class="easyui-combobox"
					required="true" editable="false" id="imageStatu_edite"
					label="发布状态:" style="width:100%"
					data-options="valueField:'statuId',textField:'statuName',url:'loadStatuImageFolder',panelHeight:'auto'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-validatebox easyui-textbox" name="message"
					label="相册描述:" required="true" maxlength="80" id="arr_content"
					validtype="length[0,80]" data-options="multiline:true"
					style="width: 100%;height: 110px;" invalidMessage="最大长度80位"></input>
			</div>
		</form>
	</div>
	<div id="dlg_imageFolder_add" class="easyui-dialog" style="width:400px"
		closed="true" buttons="#dlg-buttons_folder_add"
		data-options="modal:true">
		<form id="imageFolderForm" method="post" novalidate
			style="margin:0;padding:20px 50px">
			<div style="margin-bottom:10px">
				<input name="imageFolder.imageFolderName" class="easyui-textbox"
					id="imageFolder_add" required="true" label="相册名称:"
					style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="imageFolder.imageFolderStatu" class="easyui-combobox"
					required="true" editable="false" id="imageStatu_add" label="发布状态:"
					style="width:100%"
					data-options="valueField:'statuId',textField:'statuName',url:'loadStatuImageFolder',panelHeight:'auto'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-validatebox easyui-textbox" name="message"
					label="相册描述:" required="true" maxlength="80" id="folder_content"
					validtype="length[0,80]" data-options="multiline:true"
					style="width: 100%;height: 110px;" invalidMessage="最大长度80位"></input>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveImage()" style="width:90px">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width:90px">取消</a>
	</div>
	<div id="dlg-buttons_folder_edite">

		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add_" onclick="addImageFolder()" style="width:90px">新增相册</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-rubbish" onclick="destroyImageFolder()"
			style="width:90px">删除相册</a> <a href="javascript:void(0)"
			class="easyui-linkbutton c6" iconCls="icon-ok"
			onclick="saveImageFolder()" style="width:60px">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dlg_imageFolder_edite').dialog('close')"
			style="width:60px">取消</a>
	</div>
	<div id="dlg-buttons_folder_add">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="addImageFolderImpl()" style="width:60px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dlg_imageFolder_add').dialog('close')"
			style="width:60px">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		$(function() {
			$.extend($.messager.defaults, {
				ok : "确定",
				cancel : "取消",
				modal : true
			});
		})
		function editImage() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('center').dialog('setTitle',
					'编辑照片信息');
				$('#fm').form('load', row);
			}
		}
		function editImageFolder() {
			$('#dlg_imageFolder_edite').dialog('open').dialog('center').dialog('setTitle',
				'编辑相册信息');
		}
		function saveImage() {
			$.ajax({
				type : "post",
				url : "updateImage",
				dataType : "json",
				cache : false,
				data : {
					"imageId" : $('#imageId').val(),
					"imageFolder.imageFolderName" : $('#imageFolder').combobox('getText')
				},
				success : function(data) {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			})
		}
		function addImageFolder() {
			$('#dlg_imageFolder_edite').dialog('close');
			$('#dlg_imageFolder_add').dialog('open').dialog('center').dialog('setTitle',
				'新增相册');
		}
		function addImageFolderImpl() {
			$.ajax({
				type : "post",
				url : "validataImageFolder",
				data : {
					"imageFolder.imageFolderName" : $("#imageFolder_add").val()
				},
				dataType : "json",
				cache : false,
				success : function(data) {
					if (data == 1) {
						$.messager.confirm('警告', "该相册名已被占用,请重新输入");
					} else {
						$.ajax({
							type : "post",
							url : "addImageFolder",
							data : {
								"imageFolder.imageFolderName" : $("#imageFolder_add").val(),
								"imageFolder.imageFolderStatu":	$('#imageStatu_add').combobox('getValue'),
								"imageFolder.imageFolderDescription":$('#folder_content').val()
							},
							dataType : "json",
							cache : false,
							success : function(data) {
								$('#dlg_imageFolder_add').dialog('close'); // close the dialog
								$('#dg').datagrid('reload'); // reload the user data
							}
						})
					}
				}
			})
		}
	
		function saveImageFolder() {
			$.ajax({
				type : "post",
				url : "updateImageFolder",
				dataType : "json",
				cache : false,
				data : {
					"imageFolder.imageFolderStatu" : $('#imageStatu_edite').combobox('getValue'),
					"imageFolder.imageFolderId" : $('#imageFolder_edite').combobox('getValue'),
					"imageFolder.imageFolderDescription" : $('#arr_content').val()
				},
				success : function(data) {
					$('#dlg_imageFolder_edite').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			});
		}
		function destroyImageFolder() {
			if ($('#imageFolder_edite').combobox('getValue') != "") {
				$.messager.confirm('警告',
					"您确定要删除 " + $('#imageFolder_edite').combobox('getText') + " 相册吗?",
					function(r) {
						if (r) {
							$.post('deleteImageFolder', {
								'imageFolder.imageFolderId' : $('#imageFolder_edite').combobox('getValue')
							}, function(result) {
								if (result.success) {
									$('#dlg_imageFolder_edite').dialog('close'); // close the dialog
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
			} else {
				$.messager.confirm('警告', "您还没有选中任何相册");
			}
		}
		function destroyImage() {
			var checkedItems = $('#dg').datagrid('getChecked');
			var row = $('#dg').datagrid('getSelected');
			var items = new Array(); //创建一个数组
			$.each(checkedItems, function(index, item) {
				items.push(item.imageId);
			});
	
			if (row) {
				$.messager.confirm('警告',
					"您确定要删除这 " + items.length + " 数据吗?",
					function(r) {
						if (r) {
							for (index in items) {
								$.post('deleteImage', {
									imageId : items[index]
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
						}
					});
			} else {
				$.messager.confirm('警告', "您还没有选中对应行");
			}
		}
	</script>
</body>
</html>
