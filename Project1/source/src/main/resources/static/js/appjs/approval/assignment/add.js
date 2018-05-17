$().ready(function() {
	loadCrmData("/sales/companyCustomer/listDic","customerId");
	loadCrmData("/sales/business/listDic","businessId");
	loadCrmData("/project/project/listDic","projectId");
	loadCrmData("/inner/orgJob/listDic","jobId");

	loadDic("sales_project_gategory","assignmentProjectCagegory");
	loadCrmData("/inner/innerOrgEmployee/listDic","assignmentPm");
	loadCrmData("/inner/innerOrgEmployee/listDic","assignmentPerson");
	loadCrmData("/inner/innerOrgEmployee/listDic","assignmentPrincipal");
	loadCrmData("/inner/innerOrgEmployee/listDic","assignmentRecipient");
	loadCrmData("/system/sysDept/listDic","deptId");
	loadCrmData("/system/sysDept/listDic","assignmentDept");
	loadCrmData("/system/sysDept/listDic","assignmentRecipientDept");

	validateRule();
	datetimepicker();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/approval/assignment/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				if (data.assignmentId > 0) {
					$('#assignmentIds').val(data.assignmentId);
				}
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
