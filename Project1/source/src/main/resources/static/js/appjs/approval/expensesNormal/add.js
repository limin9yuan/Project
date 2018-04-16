$().ready(function() {
	loadCrmData("/sales/companyCustomer/listDic","customerId");
	 loadCrmData("/sales/business/listDic","businessId");
	 loadCrmData("/sales/salesProject/listDic","projectId");
	 loadCrmData("/inner/innerOrgEmployee/listDic","expensesNormalName");
	 loadCrmData("/contract/payout/listDic","expensesNormalRelatedid");
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
		url : "/approval/expensesNormal/save",
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
		businessId : {
			required : true
		},
		//项目编号
		projectId : {
			required : true
		},
		//申请人姓名
		expensesNormalName : {
			required : true
		},
		//审票结果
		expensesNormalResult : {
			required : true
		},
		//关联请款申请编号
		expensesNormalRelatedid : {
			required : true
		},
		//支付原因
		expensesNormalReason : {
			required : true
		},
		//报销金额
		expensesNormalPrice : {
			required : true,
			number:true,
			max:9999999
		}
	},
		messages : {
			//企业客户编号
			customerId : {
				required : icon + "请选择企业客户编号！"
		},
		//业务编号
		businessId : {
			required : icon + "请选择业务编号！"
		},
		//项目编号
		projectId : {
			required : icon + "请选择项目编号！"
		},
		//申请人姓名
		expensesNormalName : {
			required : icon + "请选择申请人姓名！"
		},
		//审票结果
		expensesNormalResult : {
			required : icon + "请输入审票结果！"
		},
		//关联请款申请编号
		expensesNormalRelatedid : {
			required : icon + "请选择关联请款申请编号！"
		},
		//支付原因
		expensesNormalReason : {
			required : icon + "请输入支付原因！"
		},
		//报销金额
		expensesNormalPrice : {
			required : icon + "请输入报销金额！",
			number:icon + "请输入正确的数字小数、整数！",
			max:$.validator.format("报销金额不能大于7位数(9999999)！")
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