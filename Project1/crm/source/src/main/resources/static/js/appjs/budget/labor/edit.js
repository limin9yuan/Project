var prefixlabor = "/budget/labor"
$().ready(function() {
	validateRule();
	laborMapper_edit();
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
		url : "/budget/labor/update",
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
//修改——显示数据绑定
function laborMapper_edit(){
	$.ajax({
		url : prefixlabor + '/edit_ajax/' + $("#labor").val(),
		type : "get",
		data : {
			'LaborId' : $("#labor").val(),
		},
		success : function(data) {
			var result = data.labor;
			$("input[name='LaborId']").val(result.LaborId);
			$("input[name='employeeLevel']").val(result.employeeLevel);
			$("input[name='employeeSalaryHour']").val(result.employeeSalaryHour);
			$("input[name='laborRate']").val(result.laborRate);
			$("input[name='laborBeginTime']").val(result.laborBeginTime);
			$("input[name='laborEndTime']").val(result.laborEndTime);
			$("input[name='laborTotalDayNum']").val(result.laborTotalDayNum);
			$("input[name='laborTotalHourNum']").val(result.laborTotalHourNum);
			$("input[name='laborTotalCost']").val(result.laborTotalCost);
			$("input[name='laborGrandTotalCost']").val(result.laborGrandTotalCost);
			$("textarea[name='laborRemarks']").val(result.laborRemarks);
			$("select[name='employeeId']").val(result.employeeId);
			$("select[name='employeeId']").trigger("chosen:updated");
		}
	});
}