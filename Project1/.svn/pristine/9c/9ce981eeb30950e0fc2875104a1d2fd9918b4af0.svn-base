var prefixinnerOrgEmployee = "/inner/innerOrgEmployee"
$().ready(function() {
//所属中心，级别绑哪里？
//	loadCrmData("/inner/innerOrgEmployee/listDic","employeeCenter");
	datetimepicker();
	innerOrgEmployee_edit();
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
		url : "/inner/innerOrgEmployee/update",
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
					required : true,
					rangelength:[1,16]
				},
				employeeName : {
					required : true,
					rangelength:[1,6]
				},
				innerUserId : {
					required : true,
					rangelength:[1,16]
				},
				employeeJoinDate : {
					required : true
				},
				employeeDept : {
					required : true
				},
				employeeCenter : {
					required : true,
				},
				jobId : {
					required : true,
				},
				employeeSalaryHour : {
					required : true,
					rangelength:[1,6]
				},
				employeeStatus : {
					required: true,
				},
				employeePhoneNumber : {
					required: true,
					rangelength:[1,50]
				},
				employeeInnerPhoneNumber : {
					required: true,
					rangelength:[1,4]
				},
				employeeQq : {
					required: true,
					rangelength:[1,50]
				},
				employeeRemarks : {
					rangelength:[1,200]
				},
				// contractDraftPerson : {
				// 	required: true,
				// 	rangelength:[1,6]
				// },
				// contractSales : {
				// 	required: true,
				// },
				// contractCommitTime : {
				// 	required: true,
				// },
				// contractAttachment : {
				// 	required: true,
				// },
				// contractRemarks : {
				// 	rangelength:[1,200]
				// }
			},
			messages : {
				employeeId : {
					required : icon + "员工工号不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 16 之间的字符串"
				},
				employeeName : {
					required : icon + "员工姓名不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 6 之间的字符串"
				},
				innerUserId : {
					required : icon + "项目名称不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 16 之间的字符串"
				},
				employeeJoinDate : {
					required : icon + "入职时间不能为空"
				},
				employeeDept : {
					required : icon + "部门不能为空"
				},
				employeeCenter : {
					required : icon + "所属中心不能为空",
				},
				jobId : {
					required : icon + "岗位不能为空",
				},
				employeeSalaryHour : {
					required : icon + "时薪不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 6 之间的字符串"
				},
				employeeStatus : {
					required : icon + "员工状态不能为空",
				},
				employeePhoneNumber : {
					required : icon + "电话不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				employeeInnerPhoneNumber : {
					required : icon + "电话小号不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 4 之间的字符串"
				},
				employeeQq : {
					required : icon + "QQ不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				employeeRemarks : {
					rangelength: icon + "请输入一个长度介于 1 和 200 之间的字符串"
				},
				// contractDraftPerson : {
				// 	required : icon + "合同拟定人不能为空",
				// 	rangelength: icon + "请输入一个长度介于 1 和 6 之间的字符串"
				// },
				// contractSales : {
				// 	required : icon + "销售负责人不能为空",
				// },
				// contractCommitTime : {
				// 	required : icon + "提交评审时间不能为空"
				// },
				// contractAttachment : {
				// 	required : icon + "附件不能为空"
				// },
				// contractRemarks : {
				// 	rangelength: icon + "请输入一个长度介于 1 和 200 之间的字符串"
				// }
			}
	})
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#employeeDept").val(deptName);
}
function datetimepicker() {
	$('#employeeJoinDate').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}

function innerOrgEmployee_edit(){
	$.ajax({
		url : prefixinnerOrgEmployee + '/edit_ajax/' + $("#employeeId").val(),
		type : "get",
		data : {
			'employeeId' : $("#employeeId").val(),
		},
		success : function(data) {
			var result = data.employee;
			$("input[name='employeeName']").val(result.employeeName);
			$("input[name='employeeId']").val(result.employeeId);
			$("input[name='employeeQq']").val(result.employeeQq);
			$("input[name='employeeRemarks']").val(result.employeeRemarks);
			$("input[name='employeeLevel']").val(result.employeeLevel);
			$("input[name='employeeSalaryHour']").val(result.employeeSalaryHour);
			$("input[name='employeePhoneNumber']").val(result.employeePhoneNumber);
			$("input[name='employeeInnerPhoneNumber']").val(result.employeeInnerPhoneNumber);
			$("input[name='employeeJoinDate']").val(result.employeeJoinDate);
			$("input[name='employeeCenter']").val(result.employeeCenter);
			$("input[name='innerUserId']").val(result.innerUserId);
			$("input[name='employeeDept']").val(result.employeeDept);
			$("textarea[name='employeeRemarks']").val(result.employeeRemarks);
			$("select[name='jobId']").val(result.jobId);
			$("select[name='jobId']").trigger("chosen:updated");
			$(":radio[name='employeeStatus'][value='" + result.employeeStatus + "']").prop("checked", "checked");
			loadCrmDataValue("/inner/orgJob/listDic","jobId",result.jobId);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","employeeCenter",result.employeeCenter);
			loadDicValue("inner_employee_level","employeeLevel",result.employeeLevel);
			loadDicValue("employee_center","employeeCenter",result.employeeCenter);
		}
	});
}
