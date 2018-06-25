$().ready(function() {
	datetimepickerService();
	validateRule();
	plan_edit();
});

$.validator.setDefaults({
	submitHandler : function() {
		updatePlan();
	}
});
function plan_edit(){
	$.ajax({
		url : '/contract/plan/edit_ajax/' + $("#planId").val(),
		type : "get",
		data : {
			'planId' : $("#planId").val(),
		},
		success : function(data) {
			var result = data.plan;
 			$("input[name='contractId']").val(result.contractId);
 			$("input[name='planDeliveryDate']").val(result.planDeliveryDate);
 			$("textarea[name='planDeliveryContent']").val(result.planDeliveryContent);
		}
	});
}
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
	$('#planDeliveryDate').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}
