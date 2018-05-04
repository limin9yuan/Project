var prefixreceivable = "/contract/receivable"
$(function() {
	loadReceivable();
});

function loadReceivable() {
	$('#receivableTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefixreceivable + "/getContractId", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#receivableToolbar',
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
							//alert($("#recordId").val()==undefined ? $("#resultRecordId").val():$("#recordId").val());
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
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
									field : 'receivableId', 
									title : '收款计划ID' 
								},
																{
									field : 'contractId', 
									title : '合同编号' 
								},
								{
									field : 'businessId', 
									title : '业务编号' 
								},
																{
									field : 'receivableDate', 
									title : '收款计划时间' 
								},
																{
									field : 'receivablePrice', 
									title : '收款计划金额' 
								}/*,
																{
									field : 'receivableOwner', 
									title : '收款负责人' 
								},
																{
									field : 'receivableRemarks', 
									title : '备注' 
								}*/
																 ]
					});
}

function getReceivable() {
	var rows = $('#receivableTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length != 1) {
		layer.msg("请选择一条采购记录");
		return;
	}

    // 遍历所有选择的行数据，取每条数据对应的ID
    $.each(rows, function(i, row) {
    	//alert(row['purchaseId']);
        $("#receivableId",window.parent.document).val(row['receivableId']);
        $("#contractId",window.parent.document).val(row['contractId']);
        $("#businessId",window.parent.document).val(row['businessId']);
        $("#receivablePrice",window.parent.document).val(row['receivablePrice']);
        $("#receivableDate",window.parent.document).val(row['receivableDate']);
    });
    //alert(purchaseIds.text);
    closeWin();
}

$(".btn").click(function addReceivable() {
	layer.open({
		type : 2,
		title : '增加收款计划',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '95%' ],
		content :'/payment/received/receivableType' // iframe的url
	});
})
