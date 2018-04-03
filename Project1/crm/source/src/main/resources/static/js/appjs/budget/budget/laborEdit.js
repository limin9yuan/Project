var prefixlabor = "/budget/labor"
$().ready(function() {
	datetimepicker();
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
				number:true,
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
				number:true,
				maxlength:4
			},
			laborTotalHourNum: {
				required : true,
				number:true,
				maxlength:10
			},
			laborTotalCost: {
				required : true,
				number:true,
				maxlength:12
			},
			laborGrandTotalCost: {
				required : true,
				number:true,
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
				number:icon + "请输入有效的数字",
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
				number:icon + "请输入有效的数字",
				maxlength:icon + "字符长度不能大于4"
			},
			laborTotalHourNum : {
				required : icon + "请输入合计工时数",
				number:icon + "请输入有效的数字",
				maxlength:icon + "字符长度不能大于10"
			},
			laborTotalCost : {
				required : icon + "请输入人工成本合计",
				number:icon + "请输入有效的数字",
				maxlength:icon + "字符长度不能大于12"
			},
			laborGrandTotalCost : {
				required : icon + "请输入人工的成本总计",
				number:icon + "请输入有效的数字",
				maxlength:icon + "字符长度不能大于12"
			}
		}
	})
}
function datetimepicker() {
	 $('#laborBeginTime').datetimepicker({  
	        format: 'YYYY-MM-DD hh:mm:ss', 
	        locale: moment.locale('zh-cn')  
	    });  
	 $('#laborEndTime').datetimepicker({  
	        format: 'YYYY-MM-DD hh:mm:ss', 
	        locale: moment.locale('zh-cn')  
	    }); 
}
//修改——显示数据绑定
function laborMapper_edit(){
	$.ajax({
		url : prefixlabor + '/edit_ajax/' + $("#laborId").val(),
		type : "get",
		data : {
			'laborId' : $("#laborId").val()
		},
		success : function(data) {
			var result = data.labor;
			$("input[name='laborId']").val(result.laborId);
			$("input[name='employeeLevel']").val(result.employeeLevel);
			$("input[name='employeeSalaryHour']").val(result.employeeSalaryHour);
			$("input[name='laborRate']").val(result.laborRate);
			$("input[name='laborBeginTime']").val(result.laborBeginTime);
			$("input[name='laborEndTime']").val(result.laborEndTime);
			$("input[name='laborTotalDayNum']").val(result.laborTotalDayNum);
			$("input[name='laborTotalHourNum']").val(result.laborTotalHourNum);
			$("input[name='laborTotalCost']").val(result.laborTotalCost);
			$("input[name='laborGrandTotalCost']").val(result.laborGrandTotalCost);
			$("input[name='laborRemarks']").val(result.laborRemarks);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","employeeId",result.employeeId);
		}
	});
}