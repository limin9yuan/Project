var prefix = "/timesheet/timesheet"

$().ready(function() {

datetimepicker();
	validateRule();

});


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

	$.ajax({
		cache : true,
		type : "POST",
		url : "/timesheet/timesheet/addtimesheet",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {


			if (data.code == 0) {
				parent.layer.msg("操作成功");
					closeParenWindow();
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
		timesheetContent : {
        				required : true
        			},
        			timesheetNormal:{
        				required : true,
        				number:true
        			}
		},
		messages : {


            timesheetContent : {
            				required : icon + "请输入工作内容！"
            			},
            			timesheetNormal:{
            				required : icon+"请填写正常工时！",
            				number:icon+"请填写数字"
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
