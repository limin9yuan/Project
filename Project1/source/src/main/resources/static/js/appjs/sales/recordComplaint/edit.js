var prefixrecordComplaint = "/sales/recordComplaint"
var result;
$().ready(function() {
			$('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e){
				 $('#lastBtn').attr("disabled",true);
				 $('#nextBtn').attr("disabled",false);
			 });
			$('#myTab a[href="#serviceSS"]').on('shown.bs.tab', function(e) {
		 		 loadDicValue("sales_record_type", "complaintAfterSaleType",result.complaintAfterSaleType);
				 $('#lastBtn').attr("disabled",false);
				 $('#nextBtn').attr("disabled",false);
			 });
			 $('#myTab a[href="#information"]').on('shown.bs.tab', function(e){
				 $('#lastBtn').attr("disabled",false);
				 $('#nextBtn').attr("disabled",false);
			 });
			 $('#myTab a[href="#options"]').on('shown.bs.tab', function(e){
				 $('#lastBtn').attr("disabled",false);
				 $('#nextBtn').attr("disabled",true);
			 });
// 文件上传
	layui.use('upload', function() {
		var upload = layui.upload;
		// 执行实例
		var uploadInst = upload.render({
			elem : '#test1', // 绑定元素
			url : '/common/sysFile/upload', // 上传接口
			size : 1000,
			accept : 'file',
			done : function(r) {
				// alert(r.fileName);
				$("#complaintAttachment").val(r.fileName);
				 layer.msg(r.msg);
				 app.getData();
			},
			error : function(r) {
				layer.msg(r.msg);
			}
		});
	});
	RecordComplaint_ajax();
	datetimepicker();
	validateRule();
	getMainAndCopyPerson_ajax();
});
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#complaintId").val() +"/sales_record_complaint";
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
								" onclick='deleteMainPerson(\"" + (result[i].employeeId + "_1") +"\" )'>" +
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
								" onclick='deleteCopyPerson(\"" + (result[i].employeeId + "_2") +"\" )'>" +
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
		url : "/sales/recordComplaint/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				layer.msg("操作成功");
				closeParenWindow();
				

			} else {
				layer.alert(data.msg)
			}

		}
	});
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
			rules : {
				complaintDate : {
					required : true
				},
				complaintName : {
					required : true,
					rangelength:[1,6]
				},
				complaintCompany : {
					required : true,
					rangelength:[1,50]
				},
				customerId : {
					required : true
				},
				projectId : {
					required : true
				},
				complaintProduct : {
					required : true,
					rangelength:[1,50]
				},
				complaintFeedbackType : {
					required : true,
					rangelength:[1,10]
				},
				complaintProblem : {
					required : true,
					rangelength:[1,500]
				},
				complaintAfterSaleType : {
					required: true,
					rangelength:[1,10]
				},
				complaintExecutor : {
					required: true,
					rangelength:[1,500]
				},
				complaintResult : {
					required: true,
					rangelength:[1,10]
				},
				complaintAfterSaleRemarks : {
					rangelength:[1,200]
				},
				complaintMailbox : {
					rangelength:[1,50]
				},
				complaintPhoneNumber : {
					required: true,
					rangelength:[1,20]
				},
			},
			messages : {
				complaintDate : {
					required : icon + "问题出现时间不能为空"
				},
				complaintName : {
					required : icon + "投诉人姓名姓名不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 6 之间的字符串"
				},
				complaintCompany : {
					required : icon + "所在单位不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				customerId : {
					required : icon + "客户名称不能为空"
				},
				projectId : {
					required : icon + "项目名称不能为空"
				},
				complaintProduct : {
					required : icon + "使用产品不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				complaintFeedbackType : {
					required : icon + "反馈方式不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				complaintProblem : {
					required : icon + "反馈内容不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 500 之间的字符串"
				},
				complaintAfterSaleType : {
					required : icon + "服务类型不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				complaintExecutor : {
					required : icon + "处理过程不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 500 之间的字符串"
				},
				complaintResult : {
					required : icon + "处理结果不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				complaintAfterSaleRemarks : {
					rangelength: icon + "请输入一个长度介于 1 和 200 之间的字符串"
				},
				complaintMailbox : {
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				complaintPhoneNumber : {
					required : icon + "电话不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 20 之间的字符串"
				},
			}
	})
}

function datetimepicker() {
	$('#complaintDate').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}
// 绑定
function RecordComplaint_ajax(){
	$.ajax({
		url : prefixrecordComplaint + '/edit_ajax/' + $("#complaintId").val(),
		type : "get",
		data : {
			'complaintId' : $("#complaintId").val(),
		},
		success : function(data) {
			result = data.recordComplaint;
			$("input[name='complaintId']").val(result.complaintId);
			$("input[name='complaintCompany']").val(result.complaintCompany);
			$("input[name='complaintName']").val(result.complaintName);
			$("input[name='complaintProduct']").val(result.complaintProduct);
			$("input[name='complaintProblem']").val(result.complaintProblem);
			$("input[name='complaintAttachmentCustomer']").val(result.complaintAttachmentCustomer);
			$("textarea[name='complaintProblemRemarks']").val(result.complaintProblemRemarks);
			$("input[name='complaintExecutor']").val(result.complaintExecutor);
			$("input[name='complaintResult']").val(result.complaintResult);
			$("input[name='complaintMailbox']").val(result.complaintMailbox);
			$("input[name='complaintPhoneNumber']").val(result.complaintPhoneNumber);
			$("input[name='complaintAttachment']").val(result.complaintAttachment);
			$("input[name='complaintDate']").val(result.complaintDate);
			$("textarea[name='complaintAfterSaleRemarks']").val(result.complaintAfterSaleRemarks);
			// 按钮
			loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);
			loadCrmDataValue("/project/project/listDic","projectId",result.projectId);
			loadDicValue("sales_record_service_Service_Feedback_Type","complaintFeedbackType",result.complaintFeedbackType);
		}
	});
}
