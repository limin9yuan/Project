var prefix = "/timesheet/timesheet"

$().ready(function() {

	loadCrmData("/sales/companyCustomer/listDic","customerId");
	loadCrmData("/sales/business/listDic","businessId");
	loadCrmData("/sales/salesProject/listAllDic","projectId");
	loadCrmData("/inner/orgJob/listDic","jobId");

	loadCrmData("/inner/innerOrgEmployee/listDic","customerId");

	loadCrmData("/inner/innerOrgEmployee/listDic","assignmentPerson");
	loadCrmData("/inner/innerOrgEmployee/listDic","assignmentPrincipal");
	loadCrmData("/inner/innerOrgEmployee/listDic","assignmentRecipient");
	loadCrmData("/system/sysDept/listDic","deptId");
	loadCrmDataValue ("/system/sysDept/listDic","assignmentDept");
	loadCrmData("/system/sysDept/listDic","assignmentRecipientDept");
		//获取项目类型经理和编号
$("#projectId").bind("change",getProjectId);

datetimepicker();
	validateRule();
});
function getProjectId(){
	$.ajax({
		url : '/timesheet/timesheet/getProjectId/'+$("#projectId").val(),
		type : "get",
		data : {
			'projectId' : $("#projectId").val()
		},
		success : function(data) {
			var result = data.project;
			//项目描述
			$("input[name='projectDescription']").val(result.projectDescription);
			//项目经理
			$("input[name='employeeName']").val(result.employeeName);
			//项目名称
		 	$("input[name='projectName']").val(result.projectName);
			$("input[name='projectOwner']").val(result.projectOwner);
          	$("input[name='customerId']").val(result.customerId);
          	$("input[name='assignmentPm']").val(result.projectOwner);
        	$("input[name='timesheetPm']").val(result.employeeName);
			$("input[name='timesheetProjectCagegory']").val(result.projectGategory);
			$("input[name='assignmentProjectCagegory']").val(result.projectGategory);
		}
	});
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
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
alert($('#timesheetPm').val());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/timesheet/timesheet/save",
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
				required : icon + "请输入姓名"
			}
		}
	})
}











//修改--现实绑定数据
//function timeSheet_edit(){
//	$.ajax({
//		 url : prefix+'/edit_ajax/' + $("#timesheetId").val(),
//		type : "get",
//		data : {
//			'timesheetId' : $("#timesheetId").val(),
//		},
//		success:function(data){
//			result=data.timeSheet;
//
//            $("input[name='projectName']").val(result.projectName);
//             $("input[name='businessName']").val(result.businessName);
//
//
//
//		}
//	});
//}
