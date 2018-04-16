var jobconPrefiux="/sales/customerJob"
$().ready(function() {
	jobcon_edit();
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
		url : "/sales/customerJob/update",
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

// 修改--实现绑定数据
function jobcon_edit() {
	$.ajax({
		url : jobconPrefiux + '/edit_ajax/' + $("#customerJobId").val(),
		type : "get",
		data : {
			'customerJobId' : $("#customerJobId").val(),
		},
		success : function(data) {
			result = data.CustomerJob;
			// 岗位描述
			$("input[name='customerJobDescription']").val(result.customerJobDescription);
			// 岗位名称
			$("input[name='customerJobName']").val(result.customerJobName);
			// 备注
			$("textarea[name='customerJobRemarks']").val(result.customerJobRemarks);

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