$().ready(function() {
	datetimepickerService();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		updatePlan();
	}
});
function updatePlan() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/contract/plan/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadPlan();
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
				required : icon + "请输入名字"
			}
		}
	})
}
// 计划交付时间
function datetimepickerService() {
	$('#planDeliveryDate').dattetimepicker({
		foemat : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}