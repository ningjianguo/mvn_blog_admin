/*
 * 此js作为显示分页框，位置放在<div id="div_pager"></div>的后面，否则不可以显示
 * */

var folderId = 0;
var totalPage = 20;
var totalRecords = 400;
var pageNo = getParameter('pageid'); //这里设置参数名
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
		return "lookImage?pageNo=" + n + "&folderId=" + folderId; //参数名跟上面相同
	}
});
kkpager.generPageHtml();