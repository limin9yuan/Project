$().ready(function() {
//	********************************* 公司ID取值 **********************************************
	var tmpCustomerId = $("#customerId", window.parent.document).val() == undefined ? 
			$("#customerIds", window.parent.document).val() : $("#customerId", window.parent.document).val()
				if (tmpCustomerId ==-1) {
					parent.layer.msg("请先保存基本信息");
					return;
				}
				$('#customerId').val(tmpCustomerId);
	loadCrmData("/sales/customerChildCompany/listDic/"+tmpCustomerId, "childCompanyId");
//	****************************************************************************************
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		savecustomerDept();
	}
});
function savecustomerDept() {
	var tmpCustomerId = $("#customerId", window.parent.document).val() == undefined ? 
			$("#customerIds", window.parent.document).val() : $("#customerId", window.parent.document).val()
				if (tmpCustomerId ==-1) {
					parent.layer.msg("请先保存所有带红色*号的信息");
					return;
				}
				$('#customerId').val(tmpCustomerId);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/customerDept/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadDept();
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
			customerDeptName : {
				required : true
			},
			customerDeptDescription : {
				required : true
			},
			
//			customerDeptParentDept : {
//				required : true
//			},
			childCompanyId : {
				required : true
			}
		},
		messages : {
			customerDeptName : {
				required : icon + "请输入部门名称"
			},
			customerDeptDescription : {
				required : icon + "请输入部门描述"
			},
			
//			customerdeptparentdept : {
//				required : icon + "请输入上级部门"
//			},
			childCompanyId : {
				required : icon + "请输入子公司编号"
			}
		}
	})
}