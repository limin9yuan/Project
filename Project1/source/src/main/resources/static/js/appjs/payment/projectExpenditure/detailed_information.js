var prefixDetailed_information = "/payment/projectExpenditure"
$(function() {
	$('#myTab a[href="#detailed_information"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",true);
		 $('#nextBtn').attr("disabled",false);
	 });
	$('#myTab a[href="#labor_cost"]').on('shown.bs.tab', function(e) {
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#details_of_reimbursement"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#details_of_procurement"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#summary_of_income_and_expenditure"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",true);
	 });
	loadDetailed_information();
	loadLaborCost();
	loadexpensesNormal();
	loadpurchase();
	loadreceived();
	loadSummaryOfIncomeAndExpenditure();
});

// 导出
function exportData(){
	projectExpenditureForm.projectManager.value = $('#projectManager').val();
	projectExpenditureForm.projectSales.value = $('#projectSales').val();
	projectExpenditureForm.projectId.value = $('#projectId').val();
	projectExpenditureForm.contractApplicantName.value = $('#contractApplicantName').val();
	$("#projectExpenditureForm").submit();
}
function loadLaborCost() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/payment/projectExpenditure/listLaborCost", // 服务器数据的加载地址
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
								offset:params.offset,
								projectId:$('#paramProjectId').val()
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
									field : 'employeeID',
									title : '工号'
								},
																{
									field : 'timeSheetName',
									title : '姓名'
								},
																{
									field : 'employeeLevel',
									title : '员工级别'
								},
																{
									field : 'employeeSalaryHour',
									title : '时薪'
								},
																{
									field : 'startDate',
									title : '投入开始时间'
								},
																{
									field : 'endDate',
									title : '投入结束时间'
								},
																{
									field : 'totalDay',
									title : '合计天数'
								},
																{
									field : 'workPercent',
									title : '投入百分比'
								},
																{
									field : 'totalWorkTime',
									title : '合计工时数'
								},
																{
									field : 'laborCost',
									title : '人工成本合计'
								}
								 ]
					});
}
function loadexpensesNormal() {
	$('#expensesNormalTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/approval/expensesNormal/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#expensesNormalToolbar',
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
								projectId:$('#paramProjectId').val()

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
									field : 'expensesNormal',
									title : '报销申请编号'
								},
																{
									field : 'customerId',
									title : '企业客户编号'
								},
																{
									field : 'businessId',
									title : '业务编号'
								},
																{
									field : 'projectId',
									title : '项目编号'
								},
																{
									field : 'expensesNormalName',
									title : '申请人姓名'
								},
																{
									field : 'expensesNormalReason',
									title : '支付原因'
								},
																{
									field : 'expensesNormalPrice',
									title : '报销金额'
								},
																{
									field : 'expensesNormalResult',
									title : '审票结果'
								},
																{
									field : 'expensesNormalRelatedid',
									title : '关联请款申请编号'
								},
																{
									field : 'expensesNormalRmarks',
									title : '备注'
								},
																{
									field : 'expensesNormalStatus',
									title : '审批状态'
								},
																{
									field : 'expensesNormalOperator',
									title : '操作人'
								},
																{
									field : 'expensesNormalOptTime',
									title : '修改时间'
								},
																{
									field : 'expensesNormalCreator',
									title : '创建人'
								},
																{
									field : 'expensesNormalCreateTime',
									title : '创建时间'
								}
																]
					});
}
function loadpurchase() {
	$('#purchaseTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/approval/purchase/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#purchaseToolbar',
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
								projectId:$('#paramProjectId').val()
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
									field : 'purchaseId',
									title : '采购申请编号'
								},{
									field : 'projectId',
									title : '项目编号'
								},{
									field : 'projectName',
									title : '项目名称'
								},{
									field : 'purchasePersonName',
									title : '申购人'
								},{
									field : 'purchaseDate',
									title : '申购时间'
								},{
									field : 'purchaseName',
									title : '物品名称'
								},{
									field : 'purchaseNumber',
									title : '数量'
								},{
									field : 'purchaseUnitPrice',
									title : '预算单价'
								},{
									field : 'purchaseTotalPrice',
									title : '总价'
								},{
									field : 'purchaseApprovalStatus',
									title : '审批状态'
								}
																 ]
					});
}
function loadreceived() {
	$('#receivedTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/payment/received/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#receivedToolbar',
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
								{
									field : 'receivedId',
									title : '回款信息编号'
								},
								{
									field : 'receivedTime',
									title : '收款时间'
								},
								{
									field : 'receivedPrice',
									title : '收款金额'
								},
								{
									field : 'receivedCardNumber',
									title : '收款账户'
								},
								{
									field : 'receivedType',
									title : '款项类型'
								},
								{
									field : '',
									title : '收款延迟时间'
								},
								{
									field : 'receivedContractStatus',
									title : '合同状态'
								},
								{
									field : '',
									title : '应收款金额'
								},
								{
									field : '',
									title : '回款率'
								},
								{
									field : '',
									title : '详细信息'
								}
																 ]
					});
}
function loadSummaryOfIncomeAndExpenditure() {
	$('#summaryOfIncomeAndExpenditureTable').bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/payment/summaryOfIncomeAndExpenditure/list", // 服务器数据的加载地址
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
function loadDetailed_information() {
	$.ajax({
		url : prefixDetailed_information + '/detailed_information_ajax/' + $("#paramProjectId").val(),
		type : "get",
		data : {
			'projectId' : $("#paramProjectId").val(),
		},
		success : function(data) {
			var result = data.project;
			/* 项目名称 自加载 */
			loadCrmDataValue("/project/project/listDic", "projectId",$("#paramProjectId").val());
			$("textarea[name='projectDescription']").val(result.projectDescription);
			$("input[name='budgetProfitRate']").val(result.budgetProfitRate);
			$("input[name='projectBeginDate']").val(result.projectBeginDate);
			$("input[name='projectEndDate']").val(result.projectEndDate);
			// 客户名称
			loadCrmDataValue("/sales/companyCustomer/listDic", "customerId", result.customerId);
			// 项目主管
			loadCrmDataValue("/payment/projectExpenditure/listDic", "projectOwner", result.projectOwner);
			// 项目经理
			loadCrmDataValue("/payment/projectExpenditure/listDicManager", "projectManager", result.projectManager);
			// 业务名称
			loadCrmDataValue("/sales/business/listDic", "businessId", result.businessId);
		}
	});
}
