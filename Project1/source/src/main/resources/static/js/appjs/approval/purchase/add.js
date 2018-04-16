$().ready(function() {
	loadCrmData("/system/sysDept/listDic","purchaseDept");
	loadCrmData("/inner/innerOrgEmployee/listDic","purchasePerson");
	loadCrmData("/project/project/listDic","projectId");
	validateRule();
	datetimepicker();
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
		url : "/approval/purchase/save",
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
			projectId : {
				required : true
			},
			purchaseDept : {
				required : true
			},
			purchasePerson : {
				required : true
			},
			purchaseConsignee : {
				required : true,
				maxlength:6
			},
			purchaseDate: {
				required : true
			},
			purchaseDeliveryTime: {
				required : true
			},
			purchaseDeliveryPlace : {
				required : true,
				maxlength:50
			},
			purchasePhoneNumber: {
				required : true,
				maxlength:20
			},
			purchaseName: {
				required : true,
				maxlength:50
			},
			purchaseConfig: {
				required : true,
				maxlength:200
			},
			purchaseBrand: {
				required : true,
				maxlength:50
			},
			purchaseMode: {
				required : true,
				maxlength:50
			},
			purchaseUnit: {
				required : true,
				maxlength:10
			},
			purchaseNumber: {
				required : true,
				maxlength:10
			},
			purchaseUnitPrice: {
				required : true,
				maxlength:10
			},
			purchaseTotalPrice: {
				required : true,
				maxlength:10
			}/*
			purchaseApprovalStatus: {
			required : true
		},*/
		},
		messages : {
			projectId : {
				required : icon + "请选择项目名称"
			},
			purchaseDept : {
				required : icon + "请选择申购部门"
			},
			purchasePerson : {
				required : icon + "请选择申购人"
			},
			purchaseConsignee : {
				required : icon + "请输入收货人",
				maxlength:icon + "字符长度不能大于6"
			},
			purchaseDate : {
				required : icon + "申购时间不能为空"
			},
			purchaseDeliveryTime : {
				required : icon + "要求交货时间不能为空"
			},
			purchaseDeliveryPlace : {
				required : icon + "请输入交货地点",
				maxlength:icon + "字符长度不能大于50"
			},
			purchasePhoneNumber : {
				required : icon + "请输入联系电话",
				maxlength:icon + "字符长度不能大于20"
			},
			purchaseName : {
				required : icon + "请输入物品名称",
				maxlength:icon + "字符长度不能大于50"
			},
			purchaseConfig : {
				required : icon + "请输入规格/配置",
				maxlength:icon + "字符长度不能大于200"
			},
			purchaseBrand : {
				required : icon + "请输入品牌",
				maxlength:icon + "字符长度不能大于50"
			},
			purchaseMode : {
				required : icon + "请输入型号",
				maxlength:icon + "字符长度不能大于50"
			},
			purchaseUnit : {
				required : icon + "请输入单位",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseNumber : {
				required : icon + "请输入数量",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseUnitPrice : {
				required : icon + "请输入预算单价",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseTotalPrice : {
				required : icon + "请输入总价",
				maxlength:icon + "字符长度不能大于10"
			}/*
			purchaseApprovalStatus : {
			required : icon + "请选择订货情况"
		},*/
		}
	})
}
function datetimepicker() {
	 $('#purchaseDate').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    });  
	 $('#purchaseDeliveryTime').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    }); 
}