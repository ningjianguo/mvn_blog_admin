	$(function(){
		$(".tagname_other").change(function(){
			if($(this).attr("name")=="articleTag.articleTagName"){
				$(".tagname_other").not($(this)).removeAttr('checked');
				selectedOther();
			}else{
				$("#othertag").attr("type","hidden");
				$("#tagname_other").removeAttr('checked');
			}
		});
		//初始化提示信息
		$("#alertinfo").hide();
	});

/**
 * 相册管理模块异步获取图片信息
 */
function ajaxImgInfo(url){
	$.ajax({
		type:"post",
		url:url,
		dataType:"json",
		data:null,
		error: function (request, message, ex) {
		alert("失败");
		},
		success: function (result) {
		for(var key in result[0]){//key为主键、result[0][key]为对应的相片名称
		var div = document.createElement("DIV");
		div.setAttribute("class", "col-xs-6 col-md-3");
		var a = document.createElement("A");
		var img = document.createElement("IMG");
		img.setAttribute("src", "../file/jjblog/images/"+result[0][key]+".jpg");//jjblog/images/"+result[0][key]+"
		img.style.cssText = "width:150px; height=220px;display:block";
		a.setAttribute("href", "#");
		a.setAttribute("class", "thumbnail");
		a.appendChild(img);
		div.appendChild(a);	
		$("#imgrow").append(div);	
		}	
			}
	})

}
		
/**
 * 博客管理模块异步加载资源
 */
function ajaxWzInfo(url){
	$.ajax({
				type:"post",
				url:url,
				dataType:"json",
				data:null,
				error: function (request, message, ex) {
					alert("失败");
				},
				success: function (result) {
					for(var key in result[0]){
						var tr_node = document.createElement("tr");
						var td_node;
						for (var i = 0; i < result[0][key].length; i++) {
							td_node = document.createElement("td");
							$(td_node).text(result[0][key][i]);
							$(tr_node).append(td_node);
						}
						var a_editer = document.createElement("a");
						var a_delete = document.createElement("a");
						td_node = document.createElement("td");
						$(a_editer).attr("href","javascript:ajaxEditer('"+key+"','"+result[0][key][0]+"','"+result[0][key][1]+"','"+result[0][key][2]+"')");
						$(a_editer).text("编辑")
						$(a_editer).attr("class","btn btn-info btn-sm");
						$(a_editer).css("margin-right","3px");
						$(a_delete).attr("class","btn btn-danger btn-sm");
						$(a_delete).attr("href","javascript:ajaxDelete('"+key+"')");
						$(a_delete).text("删除");
						$(td_node).append(a_editer);
						$(td_node).append(a_delete);
						$(tr_node).append(td_node);
						$(tr_node).attr("id","id"+key);
						$("#wztable").append(tr_node);
					}
				}
		});
}
/**
 * 博客管理模块----异步删除数据
 */
function ajaxDelete(id){
	swal({ 
	    title: "您确定要删除吗？", 
	    text: "您确定要删除这条数据？", 
	    type: "warning", 
	    showCancelButton: true,
	    cancelButtonText:"取消",
	    closeOnConfirm: false, 
	    confirmButtonText: "删除", 
	    confirmButtonColor: "#ec6c62", 
	    animation:"slide-from-top"
	}, function() { 
		$.ajax({
			type:"post",
			url:"wz\\deletewengz\\"+id,
			dataType:"json",
			data:null,
			error: function (request, message, ex) {
				swal("删除失败!", "请重新删除", "error");
			},
			success: function (result) {
				$("#id"+id).remove();
				$("#alertinfo").text("1条数据已被成功删除!");
				$("#alertinfo").fadeIn(3000,function(){
					$(this).fadeOut(4000);
				});

			}
		});
		swal.close();
	});
}
/**
 * 博客管理模块--------文章编辑
 */
function ajaxEditer(id,title,leixing,editer){
	$.ajax({
		type:"post",
		url:"wz\\adminQueryLeixing",
		dataType:"json",
		data:null,
		error: function (request, message, ex) {
			swal("加载失败!", "请重新编辑", "error");
		},
		success: function (result) {
			$("#wzleixings").empty();
			var option = document.createElement("option");
			$(option).text(leixing);
			$("#wzleixings").append(option);
			for(var i = 0; i < result.length; i++){
				if(result[i] != leixing){
					option = document.createElement("option");
					$(option).text(result[i]);
				}
				$("#wzleixings").append(option);
			}
		}
	});
	$("#wztitle").val(title);
	$("#wzediter").val(editer);
	$("#wzupdate").attr("onclick","javascript:updateForm("+id+")")
	$("#WzModal").modal('show');
}
function updateForm(id){//更新文章模块字段内容
	$.ajax({
		type:"post",
		url:"wz\\adminUpdate",
		dataType:"json",
		data:{'id':id,'title':$("#wztitle").val(),'editer':$("#wzediter").val(),'leixing':$('#wzleixings option:selected').val()},
		error: function (request, message, ex) {
		swal("发表失败!", "请重新发送", "error");
		},
		success: function (result) {
			$('#WzModal').modal('hide');   
			var td_nodes = $("#id"+id).find("td");
			td_nodes.eq(0).text($("#wztitle").val());
			td_nodes.eq(1).text($('#wzleixings option:selected').val());
			td_nodes.eq(2).text($("#wzediter").val());
			$("#alertinfo").text("1条数据已被成功更新!");
			$("#alertinfo").fadeIn(3000,function(){
				$(this).fadeOut(4000);
			});
		}
	});
}
/**
 * 博客管理模块-------文章书写模块
 */

//"其他"按钮是否被选中
function selectedOther(){
	$("#othertag").attr("type","text");
	$("#tagname_other").val("");
	$("#othertag").val("");
	othertag.oninput = function(){
		$("#tagname_other").val($("#othertag").val());
	}
}