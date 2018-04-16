var prefix = "/budget/budget";
var prefixlabor = "/budget/labor";
$().ready(function() {
	loadCrmData("/inner/innerOrgEmployee/listDic","employeeId");
	datetimepicker();
	validateRule();
	//员工级别,时薪计算
	$("#employeeId").bind("change", setEmployeeLevelSalary);
});
function setEmployeeLevelSalary(){
	$.ajax({
		url : '/budget/labor/getEmployeeLevelSalary/' + $("#employeeId").val(),
		type : "get",
		data : {
			'employeeId' : $("#employeeId").val()
		},
		success : function(data) {
			var result = data.employee;
			$("input[name='employeeLevel']").val(result.employeeLevel);
			$("input[name='employeeSalaryHour']").val(result.employeeSalaryHour);
		}
	});
}

$.validator.setDefaults({
	submitHandler : function() {
		saveLabor();
	}
});
function saveLabor() {
	
	var tmpBudgetId = $("#budgetId",window.parent.document).val()==undefined ? $("#relsultBudgetId",window.parent.document).val()
												:$("#budgetId",window.parent.document).val()
	if(tmpBudgetId==-1){
		parent.layer.msg("请先保存项目基本信息");
		return;
	}
	$('#budgetId').val(tmpBudgetId);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/budget/labor/save",
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
function calculateLaborCost(){
	var laborBeginTime = $('#laborBeginTime').data('date');
	var laborEndTime = $('#laborEndTime').data('date');
	var laborBeginDate = laborBeginTime.substr(0,10);
	var laborEndDate =laborEndTime.substr(0,10);
	var daysDifference = multiDaysCalculate(laborBeginDate, laborEndDate);
	var laborPerHourCost = $('#employeeSalaryHour').val();
	var laborHours = 0;
	var totalDays = 0;
	var totalLaborCost = 0;
	if (daysDifference == 1) {
		laborHours = oneDayLaborHour(laborBeginTime, laborEndTime);
		totalDays = laborHours/8;
		totalLaborCost = laborHours * laborPerHourCost;
	}
	if (daysDifference == 2) {
		laborHours = twoDayLaborHour(laborBeginTime, laborEndTime);
		totalDays = laborHours/8;
		totalLaborCost = laborHours * laborPerHourCost;
	}
	if (daysDifference > 2) {
		laborHours = (daysDifference - 2) * 8 + twoDayLaborHour(laborBeginTime, laborEndTime);
		totalDays = laborHours/8;
		totalLaborCost = laborHours * laborPerHourCost;
	}
	$("input[name='laborTotalDayNum']").val(totalDays.toFixed(2));
	$("input[name='laborTotalHourNum']").val(laborHours.toFixed(2));
	$("input[name='laborTotalCost']").val(totalLaborCost.toFixed(2));
	// alert("合计天数：" + totalDays + "合计工时数：" + laborHours + "人工成本合计：" + totalLaborCost);
}
function oneDayLaborHour(startDate, endDate) {
	//开始日期
	// var timeBegin = $('#laborBeginTime').data('date');
	var timeBegin = startDate;
	//开始小时
	var beginHour = parseInt(timeBegin.substr(11,2));
	//开始分钟
	var beginMinute = parseInt(timeBegin.substr(14,2));
	//结束日期
	// var timeEnd = $('#laborEndTime').data('date');
	var timeEnd = endDate;
	//结束小时
	var endHour = parseInt(timeEnd.substr(11,2));
	//结束分钟
	var endMinute = parseInt(timeEnd.substr(14,2));
	//上午总小时
	var amReslutHour = 0;
	//上午总分钟
	var amReslutMintue = 0;
	//下午总小时
	var pmReslutHour= 0;
	//下午总分钟
	var pmReslutMintue = 0;
	//上午小时总计
	var amReslut = 0;
	//下午小时总计
	var pmResult = 0;
	//一天小时总计
	var totalResult =0;

	//开始时间在当天上午 结束时间在当天上午
	if (beginHour >= 8 && beginHour <= 11 && endHour >= 8 && endHour <= 11) {
		//开始分钟小于等于结束分钟 else开始分钟大于结束分钟 则结束分钟要向小时借60
		if (beginMinute <= endMinute) {
			amReslutMintue = endMinute - beginMinute;
			amReslutHour = endHour - beginHour;
			amReslut = amReslutHour + amReslutMintue/60;
		}else {
			amReslutMintue = endMinute + 60 - beginMinute;
			amReslutHour = endHour - 1 - beginHour;
			amReslut = amReslutHour + amReslutMintue/60;
		}
	}
	//开始时间在当天上午 结束时间在当天下午
	if (beginHour >= 8 && beginHour <= 11 && endHour >= 13 && endHour <= 17) {
		//开始分钟大于30 要向小时借60 else小于30则不用
		if (beginMinute > 30){
			amReslutMintue = 90 - beginMinute;
			amReslutHour = 10 - beginHour;
			amReslut = amReslutHour + amReslutMintue/60;
		}else {
			amReslutMintue = 30 - beginMinute;
			amReslutHour = 11 - beginHour;
			amReslut = amReslutHour + amReslutMintue/60;

		}
		pmReslutMintue = endMinute;
		pmReslutHour = endHour - 13;
		pmResult = pmReslutHour + pmReslutMintue/60;
	}
	//开始时间在当天下午 结束时间在当天下午
	if (beginHour >= 13 && beginHour <= 17 && endHour >= 13 && endHour <= 17) {
		//开始分钟小于等于结束分钟 else开始分钟大于结束分钟 则结束分钟要向小时借60
		if (beginMinute <= endMinute) {
			pmReslutMintue = endMinute - beginMinute;
			pmReslutHour = endHour - beginHour;
			pmResult = pmReslutHour + pmReslutMintue/60;
		}else {
			pmReslutMintue = endMinute + 60 - beginMinute;
			pmReslutHour = endHour - 1 - beginHour;
			pmResult = pmReslutHour + pmReslutMintue/60;
		}
	}
	totalResult = amReslut + pmResult;
	return totalResult;
}
function twoDayLaborHour(startDate, endDate) {
	//开始日期
	// var timeBegin = $('#laborBeginTime').data('date');
	var timeBegin = startDate;
	//开始小时
	var beginHour = parseInt(timeBegin.substr(11,2));
	//开始分钟
	var beginMinute = parseInt(timeBegin.substr(14,2));
	//结束日期
	// var timeEnd = $('#laborEndTime').data('date');
	var timeEnd = endDate;
	//结束小时
	var endHour = parseInt(timeEnd.substr(11,2));
	//结束分钟
	var endMinute = parseInt(timeEnd.substr(14,2));
	//上午总小时
	var amReslutHour = 0;
	//上午总分钟
	var amReslutMintue = 0;
	//下午总小时
	var pmReslutHour= 0;
	//下午总分钟
	var pmReslutMintue = 0;
	//上午小时总计
	var amReslut = 0;
	//下午小时总计
	var pmResult = 0;
	//一天小时总计
	var totalResult =0;

	//开始时间第一天上午 结束时间第二天上午
	if (beginHour >= 8 && beginHour <= 11 && endHour >= 8 && endHour <= 11) {
		//开始分钟小于等于30 else开始分钟大于30 则开始分钟要向开始小时借60
		if (beginMinute <= 30) {
			amReslutMintue = 30 - beginMinute;
			amReslutHour = 11 - beginHour;
			amReslut = amReslutHour + amReslutMintue/60;
			pmResult = 4.5;
		}else {
			amReslutMintue = 90 - beginMinute;
			amReslutHour = 10 - beginHour;
			amReslut = amReslutHour + amReslutMintue/60;
			pmResult = 4.5;
		}
		//结束分钟小于等于30 else结束分钟大于30 则结束分钟要向结束小时借60
		if (endMinute <= 30) {
			amReslutMintue = amReslutMintue + (30 - endMinute);
			amReslutHour = amReslutHour + (11 - endHour);
			amReslut = amReslutHour + amReslutMintue/60;
		}else {
			amReslutMintue = amReslutMintue + (90 - endMinute);
			amReslutHour = amReslutHour + (10 - endHour);
			amReslut = amReslutHour + amReslutMintue/60;
		}
	}
	//开始时间第一天上午 结束时间第二天下午
	if (beginHour >= 8 && beginHour <= 11 && endHour >= 13 && endHour <= 17) {
		//开始分钟小于等于30 else开始分钟大于30 则开始分钟要向开始小时借60
		if (beginMinute <= 30) {
			amReslutMintue = 30 - beginMinute;
			amReslutHour = 11 - beginHour;
			amReslut = amReslutHour + amReslutMintue/60;
			pmResult = 4.5;
		}else {
			amReslutMintue = 90 - beginMinute;
			amReslutHour = 10 - beginHour;
			amReslut = amReslutHour + amReslutMintue/60;
			pmResult = 4.5;
		}
		pmReslutMintue = endMinute;
		pmReslutHour = endHour - 13;
		amReslut = amReslut + 3.5;
		pmResult = pmResult + (pmReslutHour + pmReslutMintue/60);
	}
	//开始时间第一天下午 结束时间第二天上午
	if (beginHour >= 13 && beginHour <= 17 && endHour >= 8 && endHour <= 11) {
		//开始分钟小于等于30 else开始分钟大于30 则开始分钟要向开始小时借60
 		if (beginMinute <= 30) {
 			pmReslutMintue = 30 - beginMinute;
			pmReslutHour = 17- beginHour;
			pmResult = pmReslutHour + pmReslutMintue/60;
 		}else {
 			pmReslutMintue = 90 - beginMinute;
			pmReslutHour = 16 - beginHour;
			pmResult = pmReslutHour + pmReslutMintue/60;
 		}
		amReslutMintue = endMinute;
		amReslutHour = endHour - 8;
		amReslut = amReslutHour + amReslutMintue/60;
	}
	//开始时间第一天下午 结束时间第二天下午
	if (beginHour >= 13 && beginHour <= 17 && endHour >= 13 && endHour <= 17) {
		//开始分钟小于等于30 else开始分钟大于30 则开始分钟要向开始小时借60
 		if (beginMinute <= 30) {
 			pmReslutMintue = 30 - beginMinute;
			pmReslutHour = 17- beginHour;
			pmResult = pmReslutHour + pmReslutMintue/60;
 		}else {
 			pmReslutMintue = 90 - beginMinute;
			pmReslutHour = 16 - beginHour;
			pmResult = pmReslutHour + pmReslutMintue/60;
 		}
		pmReslutMintue =endMinute;
		pmReslutHour =endHour - 13;
		pmResult = pmResult + pmReslutHour + pmReslutMintue/60;
		amReslut = 3.5;
	}
	totalResult = amReslut + pmResult;
	return totalResult;

}
function multiDaysCalculate(startDate, endDate) {
	// var laborBeginTime = $('#laborBeginTime').data('date');
	// var laborEndTime = $('#laborEndTime').data('date');
	// var laborBeginDate = laborBeginTime.substr(0,10);
	// var laborEndDate =laborEndTime.substr(0,10);
	var laborBeginDate = startDate;
	var laborEndDate = endDate;
	//开始日期
	var beginDate = new Date(laborBeginDate.replace(/-/g, "/"));
	//结束日期
	var endDate = new Date(laborEndDate.replace(/-/g, "/"));
	//日期差值,即包含周六日、以天为单位的工时，86400000=1000*60*60*24.
	var workDayVal = (endDate - beginDate)/86400000 + 1;
	//工时的余数
	var remainder = workDayVal % 7;
	//工时向下取整的除数
	var divisor = Math.floor(workDayVal / 7);
	var weekendDay = 2 * divisor;

	//起始日期的星期，星期取值有（1,2,3,4,5,6,0）
	var nextDay = beginDate.getDay();
	//从起始日期的星期开始 遍历remainder天
	for(var tempDay = remainder; tempDay>=1; tempDay--) {
	    //第一天不用加1
	    if(tempDay == remainder) {
	        nextDay = nextDay + 0;
	    } else if(tempDay != remainder) {
	        nextDay = nextDay + 1;
	    }
	    //周日，变更为0
	    if(nextDay == 7) {
	        nextDay = 0;
	    }

	    //周六日
	    if(nextDay == 0 || nextDay == 6) {
	        weekendDay = weekendDay + 1;
	    }
	}
	//实际工时（天） = 起止日期差 - 周六日数目。
	workDayVal = workDayVal - weekendDay;
	return workDayVal;
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
	        format: 'YYYY-MM-DD HH:mm',
	        locale: moment.locale('zh-cn'),
	    });
	 $('#laborEndTime').datetimepicker({
	        format: 'YYYY-MM-DD HH:mm',
	        locale: moment.locale('zh-cn'),
	    });
}