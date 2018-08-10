$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		saveChild();
	}
});
function saveChild() {
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
		url : "/sales/customerChildCompany/save",
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			childCompanyName : {
				required : true
			}
		},
		messages : {
			childCompanyName : {
				required : icon + "请输入子公司名称"
			}
		}
	})
}