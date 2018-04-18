var address = null;
var prefix = "/budget/budget";
$().ready(function() {
	loadDic("budget_Responsible_Center","responsibleCenter");
	loadDic("budget_project_gategory","projectGategory");
	loadCrmData("/system/sysDept/listDic","deptId");
	loadCrmData("/inner/innerOrgEmployee/listDic","projectSupervisor");
	loadCrmData("/inner/innerOrgEmployee/listDic","projectOwner");
	loadCrmData("/sales/salesProject/listAllDic","projectId");
	loadCrmData("/sales/companyCustomer/listDic","customerId");
	loadCrmData("/sales/business/listDic","businessId");
	loadCrmData("/contract/contract/listDic","contractId");
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

	//获取项目描述
	$("#projectId").bind("change", getProjectId);
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
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
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/budget/budget/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.budgetId > 0) {
				$('#relsultBudgetId').val(data.budgetId);
				parent.layer.msg("操作成功");
				//parent.reLoad();
				//var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				//parent.layer.close(index);

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
