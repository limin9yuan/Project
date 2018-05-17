var prefixassignment = "/approval/assignment"
$().ready(function() {
	validateRule();
	datetimepicker();
	assignmentMapper_edit();
	getMainAndCopyPerson_ajax();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#assignmentId").val()+"/approval_assignment";
	var mainPerson="";
	var copyPerson="";
	var isMainPerson;
	$.ajax({
		url : tmpUrl,
		type : "get",
		data : {
			// 'projectId' : $("#projectId").val(),
		},
		success : function(data) {
			result = data.mainAndCopyPerson;
			var mainPersonIds = "";
			var copyPersonIds = "";
			for (var i = 0; i < result.length; i++) {
				if (result[i].mainPerson == 1) {
					mainPerson = mainPerson + "<div class='personDiv' id=" + (result[i].employeeId + "_1") +
								" onclick='delete(\"" + (result[i].employeeId + "_1") +"\" )'>" +
								result[i].person +"</div>";
					$('#sendPerson').html(mainPerson);
					if (mainPersonIds == "") {
						mainPersonIds = result[i].employeeId
					}else {
						mainPersonIds = mainPersonIds + ","+result[i].employeeId;
					}

					$('#mainPersonId').val(mainPersonIds);

				}
				if (result[i].mainPerson == 0) {
					copyPerson = copyPerson + "<div class='personDiv' id=" + (result[i].employeeId + "_2") +
								" onclick='delete(\"" + (result[i].employeeId + "_2") +"\" )'>" +
								result[i].person +"</div>";
					$('#receivePerson').html(copyPerson);
					if (copyPersonIds == "") {
						copyPersonIds = result[i].employeeId
					}else {
						copyPersonIds = copyPersonIds + ","+result[i].employeeId;
					}

					$('#copyPersonId').val(copyPersonIds);


				}
			}
		}
	});
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/approval/assignment/update",
		data : $('#signupForm').serialize(),// 你的formid
		sync : false,
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
			customerId : {
				required : true
			},
			businessId : {
				required : true
			},
			projectId : {
				required : true
			},
			assignmentProjectCagegory : {
				required : true
			},
			assignmentPm: {
				required : true
			},
			assignmentPerson: {
				required : true
			},
			deptId : {
				required : true
			},
			jobId: {
				required : true
			},
			assignmentTaskName: {
				required : true,
				maxlength:50
			},
			assignmentTaskPerformance: {
				required : true,
				maxlength:200
			},
			assignmentBeginTime: {
				required : true
			},
			assignmentEndTime: {
				required : true
			},
			assignmentDept: {
				required : true
			},
			assignmentPrincipal: {
				required : true
			},
			assignmentRecipientDept: {
				required : true
			},
			assignmentRecipient: {
				required : true
			},
			assignmentTaskDescription: {
			required : true,
			maxlength:1000
		    }
		},
		messages : {
			customerId : {
				required : icon + "请选择客户名称"
			},
			businessId : {
				required : icon + "请选择业务名称"
			},
			projectId : {
				required : icon + "请选择项目名称"
			},
			assignmentProjectCagegory : {
				required : icon + "请选择项目类别"
			},
			assignmentPm : {
				required : icon + "请选择项目经理"
			},
			assignmentPerson : {
				required : icon + "请选择申请人"
			},
			deptId : {
				required : icon + "请选择所在部门"
			},
			jobId : {
				required : icon + "请选择岗位"
			},
			assignmentTaskName : {
				required : icon + "请输入任务名称",
				maxlength:icon + "字符长度不能大于50"
			},
			assignmentTaskPerformance : {
				required : icon + "请输入任务完成情况",
				maxlength:icon + "字符长度不能大于200"
			},
			assignmentBeginTime : {
				required : icon + "任务委托时间不能为空"
			},
			assignmentEndTime : {
				required : icon + "要求完成时间不能为空"
			},
			assignmentDept : {
				required : icon + "请选择委托部门"
			},
			assignmentPrincipal : {
				required : icon + "请选择委托人"
			},
			assignmentRecipientDept : {
				required : icon + "请选择承接部门"
			},
			assignmentRecipient : {
				required : icon + "请选择承接人"
			},
			assignmentTaskDescription : {
			required : icon + "请输入委托任务描述",
			maxlength:icon + "字符长度不能大于1000"
		    }
		}
	})
}
function datetimepicker() {
	 $('#assignmentBeginTime').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
	 $('#assignmentEndTime').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
}
//修改——显示数据绑定
function assignmentMapper_edit(){
	$.ajax({
		url : prefixassignment + '/edit_ajax/' + $("#assignmentId").val(),
		type : "get",
		data : {
			'assignmentId' : $("#assignmentId").val(),
		},
		success : function(data) {
			var result = data.assignment;
			//alert(result.assignmentTaskPerformance);
			$("input[name='assignmentId']").val(result.assignmentId);
			$("input[name='assignmentTaskName']").val(result.assignmentTaskName);
			$("input[name='assignmentTaskPerformance']").val(result.assignmentTaskPerformance);
			$("input[name='assignmentBeginTime']").val(result.assignmentBeginTime);
			$("input[name='assignmentEndTime']").val(result.assignmentEndTime);
			$("textarea[name='assignmentTaskDescription']").val(result.assignmentTaskDescription);
			$("textarea[name='assignmentRemarks']").val(result.assignmentRemarks);

			loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);
			loadCrmDataValue("/sales/business/listDic","businessId",result.businessId);
			loadCrmDataValue("/project/project/listDic","projectId",result.projectId);
			loadCrmDataValue("/inner/orgJob/listDic","jobId",result.jobId);
			loadCrmDataValue("/approval/assignment/listDic","assignmentTaskPerformance",result.assignmentTaskPerformance);

			loadDicValue("sales_project_gategory","assignmentProjectCagegory",result.assignmentProjectCagegory);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","assignmentPm",result.assignmentPm);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","assignmentPerson",result.assignmentPerson);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","assignmentPrincipal",result.assignmentPrincipal);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","assignmentRecipient",result.assignmentRecipient);
			loadCrmDataValue("/system/sysDept/listDic","deptId",result.deptId);
			loadCrmDataValue("/system/sysDept/listDic","assignmentDept",result.assignmentDept);
			loadCrmDataValue("/system/sysDept/listDic","assignmentRecipientDept",result.assignmentRecipientDept);
		}
	});
}
