$(function() {
	load();
});
function load() {
	$('#contractDeliversTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : "/ContractCreation/ContractCreation/contractDeliversList", // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				//search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						deliverCompanyId : $("#deliverCompanyId").val(),
						deliverCompanyName : $("#deliverCompanyName").val()
					};
				},
				// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
				// queryParamsType = 'limit' ,返回参数必须包含
				// limit, offset, search, sort, order 否则, 需要包含:
				// pageSize, pageNumber, searchText, sortName,
				// sortOrder.
				// 返回false将会终止请求
				columns : [
					{
						checkbox : true
					},
					{
						align : 'center',
						field : 'deliverCompanyId',
						title : '编号'
					},
					{
						align : 'center',
						field : 'deliverCompanyName',
						title : '名称'
					}
				]
			});
}
function reLoad() {
	$('#contractDeliversTable').bootstrapTable('refresh');
}

function selectData() {
	var row = $.map($('#contractDeliversTable').bootstrapTable('getSelections'), function(row) { //获取所选择行的数据
		return row;
	});
	//多条
	var name = "";
	var	id="";
	for (var i = 0; i < row.length; i++) {
		if (i == 0 || i == "0") {
			id +=row[i].deliverCompanyId+",";
			name += row[i].deliverCompanyName+"," ;
		} else {
			id += row[i].deliverCompanyId+",";
			name +=row[i].deliverCompanyName+",";
		}
	}
//	alert(name);
	parent.$("#deliverCompanyName").val(name);
	parent.$("#deliverCompanyId").val(id);

	closeWin();

}