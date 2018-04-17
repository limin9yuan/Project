var address = null;
var prefix = "/budget/budget"
$().ready(function() {
	$('#myTab a[href="#budgetInfo"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",true);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#labor"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadLabor();
	 });
	 $('#myTab a[href="#expenses"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadExpenses();
	 });
	 $('#myTab a[href="#budgetPurchase"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadPurchase();
	 });
	validateRule();
	var address = new addressResolve({
	    proId: 'province',
	    cityId: 'city',
	    areaId: 'area'
	  });
	address.init();
	budgetMapper_edit();

	//获取项目描述
	$("#projectId").bind("change", getProjectId);
});
function loadPurchase() {
	$('#purchaseTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/budget/budgetPurchase" + "/list", // 服务器数据的加载地址
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
								budgetId: $("#budgetId").val()==undefined ? $("#relsultBudgetId").val():$("#budgetId").val()
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
									field : 'purchaseType',
									title : '采购类型'
								},{
									field : 'purchasePrice',
									title : '采购金额预估'
								},{
									field : 'purchaseDescription',
									title : '预估说明'
								},{
									field : 'purchaseCustomerRate',
									title : '客户承担'
								},{
									field : 'purchaseProjectRate',
									title : '项目组承担'
								},{
									field : 'purchaseTotalPrice',
									title : '总计'
								},{
									field : 'purchaseRemarks',
									title : '备注'
								}
																 ]
					});
}
function reLoadExpenses() {
	$('#expensesTable').bootstrapTable('refresh');
}
function loadExpenses() {
	$('#expensesTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/budget/expenses" + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#expensesToolbar',
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
								budgetId: $("#budgetId").val()==undefined ? $("#relsultBudgetId").val():$("#budgetId").val()
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
									field : 'expensesType',
									title : '报销类型'
								},{
									field : 'expensesPlanPrice',
									title : '报销金额预估'
								},{
									field : 'expensesPlanDescription',
									title : '预估说明'
								},{
									field : 'expensesCustomerRate',
									title : '客户承担'
								},{
									field : 'expensesProjectRate',
									title : '项目组承担'
								},{
									field : 'expensesTotalPrice',
									title : '总计'
								},{
									field : 'expensesRemarks',
									title : '备注'
								}/*,
																{
									field : 'expensesOperator',
									title : '操作人'
								},
																{
									field : 'expensesOperateTime',
									title : '操作时间'
								},*/
																 ]
					});
}
function reLoadExpenses() {
	$('#expensesTable').bootstrapTable('refresh');
}
function loadLabor() {
	$('#laborTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/budget/labor" + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#laborToolbar',
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
								//budgetId: $("#budgetId").val()
								budgetId: $("#budgetId").val()==undefined ? $("#relsultBudgetId").val():$("#budgetId").val()
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
									field : 'employeeId',
									title : '员工编号'
								},{
									field : 'laborBeginTime',
									title : '投入开始时间'
								},{
									field : 'laborEndTime',
									title : '投入结束时间'
								},{
									field : 'laborTotalDayNum',
									title : '合计天数'
								},{
									field : 'laborRate',
									title : '投入百分比'
								},{
									field : 'laborTotalHourNum',
									title : '合计工时数'
								},{
									field : 'laborTotalCost',
									title : '人工成本合计'
								},{
									field : 'laborRemarks',
									title : '备注'
								}
																 ]
					});
}
function reLoadLabor() {
	$('#laborTable').bootstrapTable('refresh');
}
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function getProjectId(){
	$.ajax({
		url : '/budget/budget/getProjectId/' + $("#projectId").val(),
		type : "get",
		data : {
			'projectId' : $("#projectId").val()
		},
		success : function(data) {
			var result = data.project;
			$("textarea[name='projectDescription']").val(result.projectDescription);
		}
	});
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/budget/budget/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
		rules : {
			responsibleCenter : {
				required : true
			},
			deptId : {
				required : true,
				maxlength:50
			},
			projectSupervisor : {
				required : true
			},
			projectGategory : {
				required : true
			},
			projectId: {
				required : true
			},
			contractId: {
				required : true
			},
			customerId : {
				required : true
			},
			businessId: {
				required : true
			},
			budgetAccountReceivable: {
				required : true,
				maxlength:20
			},
			budgetTotalCost: {
				required : true,
				maxlength:20
			},
			budgetProfitRate: {
				required : true,
				maxlength:5
			},
			budgetConformance: {
				required : true,
				maxlength:1
			},
			budgetServiceRevenue: {
				required : true,
				maxlength:20
			},
			budgetTax: {
				required : true,
				maxlength:20
			},
			budgetServiceRevenueNet: {
				required : true,
				maxlength:20
			},
			budgetCost: {
				required : true,
				maxlength:20
			},
			budgetPurchaseCost: {
				required : true,
				maxlength:20
			},
			budgetLaborCost: {
				required : true,
				maxlength:20
			},
			budgetTravelCost: {
				required : true,
				maxlength:20
			},
			budgetProfit: {
				required : true,
				maxlength:20
			},
			province: {
				required : true
			},
			city: {
				required : true
			},
			area: {
				required : true
			},
			projectDescription: {
				required : true,
				maxlength:20
			}
		},
		messages : {
			responsibleCenter : {
				required : icon + "请选择负责中心"
			},
			deptId : {
				required : icon + "请选择部门"
			},
			projectSupervisor : {
				required : icon + "请选择项目主管"
			},
			projectGategory : {
				required : icon + "请选择项目类别"
			},
			projectId : {
				required : icon + "请选择项目名称"
			},
			contractId : {
				required : icon + "请选择合同名称"
			},
			customerId : {
				required : icon + "请选择客户名称"
			},
			businessId : {
				required : icon + "请选择业务名称"
			},
			budgetAccountReceivable : {
				required : icon + "请选择应收账款总额",
				maxlength:icon + "字符长度不能大于20"
			},
			budgetTotalCost : {
				required : icon + "请选择计划成本总额",
				maxlength:icon + "字符长度不能大于20"
			},
			budgetProfitRate : {
				required : icon + "请选择项目计划利润率",
				maxlength:icon + "字符长度不能大于5"
			},
			budgetConformance : {
				required : icon + "请选择计划是否合规",
				maxlength:icon + "字符长度不能大于1"
			},
			budgetServiceRevenue : {
				required : icon + "请选择服务收入（合同额）",
				maxlength:icon + "字符长度不能大于20"
			},
			budgetTax : {
				required : icon + "请选择税金",
				maxlength:icon + "字符长度不能大于20"
			},
			budgetServiceRevenueNet : {
				required : icon + "请选择服务净收入",
				maxlength:icon + "字符长度不能大于20"
			},
			budgetCost : {
				required : icon + "请选择费用和支持（含税）",
				maxlength:icon + "字符长度不能大于20"
			},
			budgetPurchaseCost : {
				required : icon + "请选择采购成本",
				maxlength:icon + "字符长度不能大于20"
			},
			budgetLaborCost : {
				required : icon + "请选择人工成本",
				maxlength:icon + "字符长度不能大于20"
			},
			budgetTravelCost : {
				required : icon + "请选择差旅成本",
				maxlength:icon + "字符长度不能大于20"
			},
			budgetProfit : {
				required : icon + "请选择利润",
				maxlength:icon + "字符长度不能大于20"
			},
			province : {
				required : icon + "请选择省"
			},
			city : {
				required : icon + "请选择市"
			},
			area : {
				required : icon + "请选择区"
			},
			projectDescription : {
				required : icon + "请选择项目描述",
				maxlength:icon + "字符长度不能大于20"
			}
		}
	})
}

//修改——显示数据绑定
function budgetMapper_edit(){
	$.ajax({
		url : prefix + '/edit_ajax/' + $("#budgetId").val(),
		type : "get",
		data : {
			'budgetId' : $("#budgetId").val()
		},
		success : function(data) {
			var result = data.budget;
			$("input[name='budgetId']").val(result.budgetId);
			$("input[name='budgetAccountReceivable']").val(result.budgetAccountReceivable);
			$("input[name='budgetTotalCost']").val(result.budgetTotalCost);
			$("input[name='budgetProfitRate']").val(result.budgetProfitRate);
			$("input[name='budgetConformance']").val(result.budgetConformance);
			$("input[name='budgetServiceRevenue']").val(result.budgetServiceRevenue);
			$("input[name='budgetTax']").val(result.budgetTax);
			$("input[name='budgetServiceRevenueNet']").val(result.budgetServiceRevenueNet);
			$("input[name='budgetCost']").val(result.budgetCost);
			$("input[name='budgetPurchaseCost']").val(result.budgetPurchaseCost);
			$("input[name='budgetLaborCost']").val(result.budgetLaborCost);
			$("input[name='budgetTravelCost']").val(result.budgetTravelCost);
			$("input[name='budgetProfit']").val(result.budgetProfit);
			$("textarea[name='projectDescription']").val(result.projectDescription);

			$("select[name='province']").val(result.province);
			$("select[name='province']").trigger("chosen:updated");
			$("select[name='city']").val(result.city);
			$("select[name='city']").trigger("chosen:updated");
			$("select[name='area']").val(result.area);
			$("select[name='area']").trigger("chosen:updated");
			loadDicValue("budget_Responsible_Center","responsibleCenter",result.responsibleCenter);
			loadDicValue("budget_project_gategory","projectGategory",result.projectGategory);
			loadCrmDataValue("/system/sysDept/listDic","deptId",result.deptId);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","projectSupervisor",result.projectSupervisor);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","projectOwner",result.projectOwner);
			loadCrmDataValue("/project/project/listDic","projectId",result.projectId);
			loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);
			loadCrmDataValue("/sales/business/listDic","businessId",result.businessId);
			loadCrmDataValue("/contract/contract/listDic","contractId",result.contractId);
		}
	});
}
function nextStepThis(tabId,totalStep,lastBtn,nextBtn){
	nextStep(tabId,totalStep,lastBtn,nextBtn);
	if(address ==null ){
			if( $('#'+tabId+' li:eq(2)').attr("class")=='active'){
				address = new addressResolve({
				    proId: 'province',
				    cityId: 'city',
				    areaId: 'area'
				  });
				address.init();
			}

	}
}
