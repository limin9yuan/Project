var prefix = "/contract/contract";
$(function() {
	loadPayable();
	loadReceivable();
	loadPlan();
	getMultiProject_ajax()
});
function getMultiProject_ajax() {
	var tmpUrl = '/contract/project/getMultiProject_ajax/' + $("#contractId").val();
	var project="";
	$.ajax({
		url : tmpUrl,
		type : "get",
		data : {
			// 'projectId' : $("#projectId").val(),
		},
		success : function(data) {
			result = data.multiProject;
			var projectIds = "";
			for (var i = 0; i < result.length; i++) {
					project = project + "<div class='projectDiv' id=" + (result[i].projectId + "_1") +
								">" +result[i].projectId +" "+result[i].projectName+"<div style='float:right;margin-top:-8px;margin-right:5px'><img id=" +(result[i].projectId + "_1") +" onclick='deleteMultiProjects(\"" +  (result[i].projectId + "_1") +"\" )' src='../../../img/close.png'></div>"+"</div>";
					$('#multiProjects').html(project);
					if (projectIds == "") {
						projectIds = result[i].projectId
					}else {
						projectIds = projectIds + ","+result[i].projectId;
					}

					$('#projectId').val(projectIds);

			}
		}
	});
}
function loadPayable() {
	$('#payableTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/contract/payable/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#payableToolbar',
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
								contractId:$('#contractId').val()
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
									field : 'contractId',
									title : '合同编号'
								},
																{
									field : 'payableDate',
									title : '付款计划时间'
								},
																{
									field : 'payablePrice',
									title : '付款计划金额'
								},
																{
									field : 'payableRemarks',
									title : '备注'
								},
																{
									field : 'payableOperateTime',
									title : '操作时间'
								}
								]
					});
}
function reLoadPayable() {
	$('#payableTable').bootstrapTable('refresh');
}
function loadReceivable() {
	$('#receivableTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/contract/receivable/list", // 服务器数据的加载地址
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
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								contractId:$('#contractId').val()

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
									field : 'contractId',
									title : '合同编号'
								},
																{
									field : 'receivableDate',
									title : '收款计划时间'
								},
																{
									field : 'receivablePrice',
									title : '收款计划金额'
								},
																{
									field : 'receivableOwner',
									title : '收款负责人'
								},
																{
									field : 'receivableRemarks',
									title : '备注'
								},
																/*{
									field : 'receivableOperator',
									title : '操作人'
								},*/
																{
									field : 'receivableOperateTime',
									title : '操作时间'
								}
																 ]
					});
}
function reLoadReceivable() {
	$('#receivableTable').bootstrapTable('refresh');
}
function loadPlan() {
	$('#planTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/contract/plan/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#planToolbar',
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
								contractId:$('#contractId').val()

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
									field : 'contractId',
									title : '合同编号'
								},
																{
									field : 'planDeliveryDate',
									title : '计划交付时间'
								},
																{
									field : 'planDeliveryContent',
									title : '计划交付内容'
								},
																{
									field : 'planAttachment',
									title : '附件'
								},
																{
									field : 'planRemarks',
									title : '备注'
								},
																/*{
									field : 'planOperator',
									title : '操作人'
								},*/
																{
									field : 'planOperateTime',
									title : '操作时间'
								}
																 ]
					});
}
function reLoadPlan() {
	$('#planTable').bootstrapTable('refresh');
}
function resetPwd(id) {
}
