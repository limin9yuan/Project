var prefixContract = "/contract/contract"
$(function() {
	loadContract();
});

function loadContract() {
	$('#exampleContractTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefixContract + "/list", // 服务器数据的加载地址
				        clickToSelect: true,
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
									align : 'center',
									formatter : function(value, row, index) {
										if (row.contractApprovalStatus != null) {
											var a = '<a href="#" mce_href="#"  onclick="taskTrace(\''
											+ row.processInstanceId
											+ '\')">'
											+ row.contractApprovalStatus + '</a> ';
											return a;
										}
									},
									// field : 'contractApprovalStatus',
									title : '合同审批状态'
								},{
									field : 'contractName',
									title : '合同名称'
								}, {
									field : 'contractId',
									title : '合同编号'
								}, {
									field : 'contractType',
									title : '合同类型'
								}, {
									field : 'contractCategory',
									title : '合同种类'
								}, {
									field : 'contractTotalPrice',
									title : '合同总金额'
								},{
									field : 'contractSales',
									title : '销售负责人'
								}, {
									field : 'contractCommitTime',
									title : '提交评审时间'
								},{
									field : '',
									title : '详细信息'
								}
											 ]
					});
	
}
function taskTrace(processInstanceId){
	layer.open({
		type : 2,
		title : '流程跟踪',
		maxmin : true,
		shadeClose : false,
		area : [ '95%', '95%'],
		content : '/activiti/task/taskTrace/'+processInstanceId
	});
}

