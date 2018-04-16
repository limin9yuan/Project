var prefixsummaryOfIncomeAndExpenditure = "/payment/summaryOfIncomeAndExpenditure"
$(function() {
	loadsummaryOfIncomeAndExpenditure();
});

function loadsummaryOfIncomeAndExpenditure() {
	$('#summaryOfIncomeAndExpenditureTable').bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefixsummaryOfIncomeAndExpenditure + "/list", // 服务器数据的加载地址
				        clickToSelect: true,
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#summaryOfIncomeAndExpenditureToolbar',
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
								limit: params.limit,
								offset:params.offset,
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
// 								{
// 									checkbox : true
// 								}, 
								{
									field : 'laborTotalCost',
									title : '工时成本'
								}, {
									field : 'budgetPurchaseCost',
									title : '费用及采购成本'
								}, {
									field : 'income',
									title : '合同完工收入'
								}, {
									field : 'budgetProfit',
									title : '利润'
								}, {
									field : 'service',
									title : '服务'
								},{
									field : 'reimbursement',
									title : '报销'
								}, {
									field : 'purchase',
									title : '采购'
								}, {
									field : 'budgetTotalCost',
									title : '总计'
								},{
									field : 'budgetProfitRate', 
									title : '计划利润率' 
								},{
									field : 'actual', 
									title : '实际利润率' 
								}
											 ]
					});
}
function reLoadsummaryOfIncomeAndExpenditure() {
	$('#summaryOfIncomeAndExpenditureTable').bootstrapTable('refresh');
}
