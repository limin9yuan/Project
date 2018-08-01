$().ready(function() {
	validateRule();
	datetimepicker();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function datetimepicker() {
	$('#pickYear').datetimepicker({
		format : 'YYYY',
		locale : moment.locale('zh-cn')
	});
	$('#startWorkAM').datetimepicker({
		format : 'HH:mm',
		locale : moment.locale('zh-cn')
	});
	$('#endWorkAM').datetimepicker({
		format : 'HH:mm',
		locale : moment.locale('zh-cn')
	});
	$('#startWorkPM').datetimepicker({
		format : 'HH:mm',
		locale : moment.locale('zh-cn')
	});
	$('#endWorkPM').datetimepicker({
		format : 'HH:mm',
		locale : moment.locale('zh-cn')
	});
}
function save() {
    var pickYear = $('#currentYear').val();
    // alert("/workDay/workDay/saveWorkDay/" + pickYear);
	$.ajax({
		cache : true,
		type : "get",
		url : "/workDay/workDay/saveWorkDay/" + pickYear,
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
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}
