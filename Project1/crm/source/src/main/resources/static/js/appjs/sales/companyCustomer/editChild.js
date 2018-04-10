var childPrefix = "/sales/customerChildCompany"
$().ready(function() {
	child_edit();
	validateRule();
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
		url : "/sales/customerChildCompany/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadChild();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

// 修改--实现绑定数据
function child_edit() {
	$.ajax({
		 url : childPrefix + '/edit_ajax/' + $("#childCompanyId").val(),
		type : "get",
		data : {
			'childCompanyId' : $("#childCompanyId").val(),
		},
		success : function(data) {
			result = data.CustomerChildCompany;
			//子公司编号
			 $("input[name='childCompanyId']").val(result.childCompanyId);
			 // 企业客户编号
			 $("input[name='customerId']").val(result.customerId);
			 //子公司名称
			 $("input[name='childCompanyName']").val(result.childCompanyName);
			 //备注
			 $("textarea[name='childCompanyRemarks']").val(result.childCompanyRemarks);
			
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