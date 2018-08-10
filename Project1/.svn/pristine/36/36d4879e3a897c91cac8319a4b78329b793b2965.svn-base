$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		savecustomerJob();
	}
});
function savecustomerJob() {
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
		url : "/sales/customerJob/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadJob();
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
			customerJobName : {
				required : true
			},
			customerJobDescription : {
				required : true
			}
		},
		messages : {
			customerJobName : {
				required : icon + "请输入岗位名称"
			},
			customerJobName : {
				customerJobDescription : icon + "请输入岗位描述"
			}
		}
	})
}