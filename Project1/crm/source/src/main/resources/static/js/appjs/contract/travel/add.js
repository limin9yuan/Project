$().ready(function() {
	 $('#travelDepartureDate').datetimepicker({  
         format: 'YYYY-MM-DD',  
         locale: moment.locale('zh-cn')  
     });
	 $('#travelReturnDate').datetimepicker({  
         format: 'YYYY-MM-DD',  
         locale: moment.locale('zh-cn')  
     });
	 loadCrmData("/sales/companyCustomer/listDic","customerId");
	 loadCrmData("/sales/business/listDic","businessId");
	 loadCrmData("/sales/salesProject/listDic","projectId");
	 loadDic("sales_customer_visit_Mode","travelVisitMode");
	 loadDic("Travel_Plan_Cost_Type","travelPlanCostType");
	 loadCrmData("/inner/innerOrgEmployee/listDic","travelName");
	 loadCrmData("/inner/innerOrgEmployee/listDic","travelPartner");
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
		url : "/contract/travel/save",
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
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}