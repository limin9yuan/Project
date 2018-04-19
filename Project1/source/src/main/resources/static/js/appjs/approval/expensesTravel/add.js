$().ready(function() {
	 loadCrmData("/sales/companyCustomer/listDic","customerId");
	 loadCrmData("/sales/business/listDic","businessId");
	 loadCrmData("/sales/salesProject/listAllDic","projectId");
	 loadCrmData("/inner/innerOrgEmployee/listDic","expensesTravelName");
	 loadCrmData("/contract/travel/listDic","expensesTravelTid");
	 loadCrmData("/contract/payout/listDic","expensesTravelPayid");

	 $('#myTab a[href="#cost"]').on('shown.bs.tab', function(e){

		 if($("#expensesTravelPayMode option").length==1){
			 loadDic("contract_payout_Means","expensesTravelPayMode");
		 }

	 });
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/approval/expensesTravel/save",
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
		ignore:":hidden:not(select)",
		rules : {

			//企业客户编号
			customerId : {
				required : true
			},
			//业务编号
			businessId:{
				required : true
			},
			//项目编号
			projectId:{
				required : true
			},
			//申请人姓名
			expensesTravelName:{
				required : true
			},
			//关联出差申请编号
			expensesTravelTid:{
				required : true
			},
			//关联请款申请编号
			expensesTravelPayid:{
				required : true
			},
			//出差任务
			expensesTravelTask:{
				required : true
			},
			//出差补助
			expensesTravelAllowance:{
				number:true,
				max:999999999
			},
			//其他费用金额
			expensesTravelPrice:{
				number:true,
				max:999999999
			},
			//预借金额
			expensesTravelPayPrice:{
				required : true,
				number:true,
				max:99999999
			},
			//结算方式
			expensesTravelPayMode:{
				required : true
			},
			//收款人银行
			expensesTravelBank:{
				required : true
			},
			//卡号
			expensesTravelCardNum:{
				required : true,
				digits:true,
				max:9999999999999999999999999
			}

		},
		messages : {
			//企业客户编号
			customerId : {
				required : icon + "请选择企业客户编号！"
			},
			//业务编号
			businessId:{
				required : icon + "请选择业务编号！"
			},
			//项目编号
			projectId:{
				required : icon + "请选择项目编号！"
			},
			//申请人姓名
			expensesTravelName:{
				required : icon + "请选择申请人姓名！"
			},
			//关联出差申请编号
			expensesTravelTid:{
				required : icon + "请选择关联出差申请编号！"
			},
			//关联请款申请编号
			expensesTravelPayid:{
				required : icon + "情选择关联请款申请编号！"
			},
			//出差任务
			expensesTravelTask:{
				required : icon + "请输入出差任务！"
			},
			//出差补助
			expensesTravelAllowance:{
				number:icon + "请输入合法的数字小数、整数！",
				max:$.validator.format("出差补助不能大于8位数99999999！")
			},
			//其他费用金额
			expensesTravelPrice:{
				number:icon + "请输入合法的数字小数、整数！",
				max:$.validator.format("其他费用金额不能大于8位数99999999！")
			},
			//预借金额
			expensesTravelPayPrice:{
				required : icon + "请输入预借金额！",
				number:icon + "请输入合法的数字小数、整数！",
				max:$.validator.format("预借金额不能大于8位数99999999！")
			},
			//结算方式
			expensesTravelPayMode:{
				required : icon + "请选择结算方式！"
			},
			//收款人银行
			expensesTravelBank:{
				required : icon + "请输入收款人银行！"
			},
			//卡号
			expensesTravelCardNum:{
				required : icon + "请输入卡号！",
				digits:icon + "请输入合法的数字小数、整数！",
				max:$.validator.format("卡号不能大于25位数！")
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
