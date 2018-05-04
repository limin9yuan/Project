var deptPrefix="/sales/customerDept"
$().ready(function() {
	
	dept_edit();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		
		updateDept();
	}
});
function updateDept() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/customerDept/update",
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
//修改--实现绑定数据
function dept_edit() {
	$.ajax({
		url :  deptPrefix+'/edit_ajax/' + $("#customerDeptId").val(),
		type : "get",
		data : {
			'customerDeptId' : $("#customerDeptId").val(),
		},
		success : function(data) {
			result = data.customerDept;
			//子公司编号
			loadCrmDataValue("/sales/customerChildCompany/listDic", "childCompanyId",result.childCompanyId);
			// 部门名称
			$("input[name='customerDeptName']").val(result.customerDeptName);
			// 部门描述
			$("input[name='customerDeptDescription']").val(result.customerDeptDescription);
			//上级部门
			$("input[name='customerDeptParentDept']").val(result.customerDeptParentDept);
			// 备注
			$("textarea[name='customerDeptRemarks']").val(result.customerDeptRemarks);

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
				required : icon + "请输入名字"
			}
		}
	})
}