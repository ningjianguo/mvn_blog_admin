	$(function(){
		$(".tagname_other").change(function(){
			if($(this).attr("name")=="articleTag.articleTagName" || $(this).attr("name")=="videoTag.videoTagName"){
				$(".tagname_other").not($(this)).removeAttr('checked');
				selectedOther();
			}else{
				$("#othertag").attr("type","hidden");
				$("#tagname_other").removeAttr('checked');
			}
		});
	});

//"其他"按钮是否被选中
function selectedOther(){
	$("#othertag").attr("type","text");
	$("#tagname_other").val("");
	$("#othertag").val("");
	var othertag = document.getElementById("othertag");
	othertag.oninput = function(){
		$("#tagname_other").val($("#othertag").val());
	}
}