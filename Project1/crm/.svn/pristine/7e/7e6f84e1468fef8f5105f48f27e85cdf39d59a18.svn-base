var prefixpurchaseManagement = "/payment/purchaseManagement"
$().ready(function() {
	//loadCrmData("/inner/innerOrgEmployee/listDic","purchasePerson");
	//loadCrmData("/sales/salesProject/listAllDic","projectId");
	validateRule();
	datetimepicker();
	purchaseManagementMapper_edit();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/payment/purchaseManagement/update",
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
			purchasePerson : {
				required : true
			},
			purchaseTime : {
				required : true
			},
			purchaseDeliveryTime : {
				required : true
			},
			purchaseDeliveryPlace: {
				required : true,
				maxlength:50
			},
			purchaseConsignee: {
				required : true,
				maxlength:6
			},
			purchasePhoneNumber : {
				required : true,
				maxlength:20
			},
			purchaseName: {
				required : true,
				maxlength:50
			},
			purchaseBrand: {
				required : true,
				maxlength:30
			},
			purchaseMode: {
				required : true,
				maxlength:30
			},
			purchaseConfig: {
				required : true,
				maxlength:200
			},
			purchaseUnit: {
				required : true,
				maxlength:20
			},
			purchaseUnitPrice: {
				required : true,
				maxlength:10
			},
			purchaseNumber: {
				required : true,
				maxlength:10
			},
			purchaseTotalPrice: {
				required : true,
				maxlength:10
			},/*
			purchaseOrderStatus: {
				required : true
			},*/
			purchasePaid: {
				required : true,
				maxlength:10
			},
			purchaseNotPaid: {
				required : true,
				maxlength:10
			}/*,
			purchaseFulfilmentStatus: {
				required : true
			}*/
		},
		messages : {
			projectId : {
				required : icon + "请选择项目名称"
			},
			purchasePerson : {
				required : icon + "请选择采购人"
			},
			purchaseTime : {
				required : icon + "采购时间不能为空"
			},
			purchaseDeliveryTime : {
				required : icon + "交货时间不能为空"
			},
			purchaseDeliveryPlace : {
				required : icon + "请输入交货地点",
				maxlength:icon + "字符长度不能大于50"
			},
			purchaseConsignee : {
				required : icon + "请输入收货人",
				maxlength:icon + "字符长度不能大于6"
			},
			purchasePhoneNumber : {
				required : icon + "请输入联系电话",
				maxlength:icon + "字符长度不能大于20"
			},
			purchaseName : {
				required : icon + "请输入物品名称",
				maxlength:icon + "字符长度不能大于50"
			},
			purchaseBrand : {
				required : icon + "请输入品牌",
				maxlength:icon + "字符长度不能大于30"
			},
			purchaseMode : {
				required : icon + "请输入型号",
				maxlength:icon + "字符长度不能大于30"
			},
			purchaseConfig : {
				required : icon + "请输入规格/配置",
				maxlength:icon + "字符长度不能大于200"
			},
			purchaseUnit : {
				required : icon + "请输入单位",
				maxlength:icon + "字符长度不能大于20"
			},
			purchaseUnitPrice : {
				required : icon + "请输入单价",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseNumber : {
				required : icon + "请输入数量",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseTotalPrice : {
				required : icon + "请输入总计",
				maxlength:icon + "字符长度不能大于10"
			},/*
			purchaseOrderStatus : {
				required : icon + "请选择订货情况"
			},*/
			purchasePaid : {
				required : icon + "请输入已付款",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseNotPaid : {
				required : icon + "请输入未付款",
				maxlength:icon + "字符长度不能大于10"
			}/*,
			purchaseFulfilmentStatus : {
				required : icon + "请选择发货情况"
			}*/
		}
	})
}
function datetimepicker() {
	 $('#purchaseTime').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    });  
	 $('#purchaseDeliveryTime').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    }); 
}
//修改——显示数据绑定
function purchaseManagementMapper_edit(){
	$.ajax({
		url : prefixpurchaseManagement + '/edit_ajax/' + $("#purchaseId").val(),
		type : "get",
		data : {
			'purchaseId' : $("#purchaseId").val()
		},
		success : function(data) {
			var result = data.purchaseManagement;
			$("input[name='purchaseId']").val(result.purchaseId);
			$("input[name='purchaseTime']").val(result.purchaseTime);
			$("input[name='purchaseDeliveryTime']").val(result.purchaseDeliveryTime);
			$("input[name='purchaseDeliveryPlace']").val(result.purchaseDeliveryPlace);
			$("input[name='purchaseConsignee']").val(result.purchaseConsignee);
			$("input[name='purchasePhoneNumber']").val(result.purchasePhoneNumber);
			$("input[name='purchaseName']").val(result.purchaseName);
			$("input[name='purchaseBrand']").val(result.purchaseBrand);
			$("input[name='purchaseMode']").val(result.purchaseMode);
			$("input[name='purchaseConfig']").val(result.purchaseConfig);
			$("input[name='purchaseUnit']").val(result.purchaseUnit);
			$("input[name='purchaseUnitPrice']").val(result.purchaseUnitPrice);
			$("input[name='purchaseNumber']").val(result.purchaseNumber);
			$("input[name='purchaseTotalPrice']").val(result.purchaseTotalPrice);
			$("input[name='purchaseOrderStatus']").val(result.purchaseOrderStatus);
			$("input[name='purchasePaid']").val(result.purchasePaid);
			$("input[name='purchaseNotPaid']").val(result.purchaseNotPaid);
			$("input[name='purchaseFulfilmentStatus']").val(result.purchaseFulfilmentStatus);
			$("textarea[name='purchaseRemarks']").val(result.purchaseRemarks);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","purchasePerson",result.purchasePerson);
			loadCrmDataValue("/sales/salesProject/listAllDic","projectId",result.projectId);
		}
	});
}
function datetimepicker() {
	 $('#purchaseTime').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    });  
	 $('#purchaseDeliveryTime').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    }); 
}