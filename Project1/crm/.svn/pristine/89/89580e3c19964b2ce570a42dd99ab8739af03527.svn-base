$().ready(function() {
	loadCrmData("/inner/innerOrgEmployee/listDic","employeeId");
	datetimepicker();
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
		ignore: ":hidden:not(select)",
		rules : {
			employeeId : {
				required : true
			},
			employeeLevel : {
				required : true
			},
			employeeSalaryHour : {
				required : true
			},
			laborRate : {
				required : true,
				maxlength:5
			},
			laborBeginTime: {
				required : true
			},
			laborEndTime: {
				required : true
			},
			laborTotalDayNum : {
				required : true,
				maxlength:4
			},
			laborTotalHourNum: {
				required : true,
				maxlength:10
			},
			laborTotalCost: {
				required : true,
				maxlength:12
			},
			laborGrandTotalCost: {
				required : true,
				maxlength:12
			}
		},
		messages : {
			employeeId : {
				required : icon + "请选择姓名"
			},
			employeeLevel : {
				required : icon + "请输入员工级别"
			},
			employeeSalaryHour : {
				required : icon + "请输入时薪"
			},
			laborRate : {
				required : icon + "请输入投入百分比",
				maxlength:icon + "字符长度不能大于5"
			},
			laborBeginTime : {
				required : icon + "投入开始时间不能为空"
			},
			laborEndTime : {
				required : icon + "投入结束时间不能为空"
			},
			laborTotalDayNum : {
				required : icon + "请输入合计天数",
				maxlength:icon + "字符长度不能大于4"
			},
			laborTotalHourNum : {
				required : icon + "请输入合计工时数",
				maxlength:icon + "字符长度不能大于10"
			},
			laborTotalCost : {
				required : icon + "请输入人工成本合计",
				maxlength:icon + "字符长度不能大于12"
			},
			laborGrandTotalCost : {
				required : icon + "请输入人工的成本总计",
				maxlength:icon + "字符长度不能大于12"
			}
		}
	})
}
function datetimepicker() {
	 $('#laborBeginTime').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    });  
	 $('#laborEndTime').datetimepicker({  
	        format: 'YYYY-MM-DD hh:mm',  
	        locale: moment.locale('zh-cn')  
	    }); 
}