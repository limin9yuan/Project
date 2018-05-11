var prefixadditionalRecords = "/contract/additionalRecords"
//var result;
$().ready(function() {
	$('#myTab a[href="#basic"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",true);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#payment"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadPayable();
	 });
	 $('#myTab a[href="#Receivables"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadReceivable();
	 });
	 $('#myTab a[href="#plan"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadPlan();
	 });
	validateRule();
	datetimepicker();
	additionalRecordsMapper_edit();
	getMainAndCopyPerson_ajax();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#recordId").val();
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
		url : "/contract/additionalRecords/update",
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
			recordName : {
				required : true
			},
			jobId : {
				required : true
			},
			contractName : {
				required : true,
				maxlength:50
			},
			recordBulidCompany : {
				required : true,
				maxlength:50
			},
			recordSales: {
				required : true
			},
			recordTotalPrice : {
				required : true,
				maxlength:16
			},
			recordDescription: {
				required : true,
				maxlength:1000
			},recordReason: {
				required : true,
				maxlength:1000
			},recordCommitTime: {
				required : true
			},preInvoiceDate: {
				required : true
			},contractDraftPerson: {
				required : true
			}
		},
		messages : {
			recordName : {
				required : icon + "请选择申请人姓名"
			},
			jobId : {
				required : icon + "请选择岗位"
			},
			contractName : {
				required : icon + "请输入合同名称",
				maxlength:icon + "字符长度不能大于50"
			},
			recordBulidCompany : {
				required : icon + "请输入建设单位",
				maxlength:icon + "字符长度不能大于50"
			},
			recordSales : {
				required : icon + "请选择销售负责人"
			},
			recordTotalPrice : {
				required : icon + "请输入增补总金额",
				maxlength:icon + "字符长度不能大于16"
			},
			recordDescription : {
				required : icon + "请输入增补内容描述",
				maxlength:icon + "字符长度不能大于1000"
			},
			recordReason : {
				required : icon + "请输入增补原因",
				maxlength:icon + "字符长度不能大于1000"
			},
			recordCommitTime : {
				required : icon + "开始时间提交评审时间"
			},
			preInvoiceDate : {
				required : icon + "预计开发票时间不能为空"
			},
			contractDraftPerson : {
				required : icon + "请选择合同拟定人"
			}
		}
	})
}
function datetimepicker() {
	 $('#recordCommitTime').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
	 $('#preInvoiceDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
}
//修改——显示数据绑定
function additionalRecordsMapper_edit(){
	$.ajax({
		url : prefixadditionalRecords + '/edit_ajax/' + $("#recordId").val(),
		type : "get",
		data : {
			'recordId' : $("#recordId").val(),
		},
		success : function(data) {
			var result = data.additionalRecords;
			$("input[name='recordId']").val(result.recordId);
			$("input[name='employeeDept']").val(result.employeeDept);
			$("input[name='contractName']").val(result.contractName);
			$("input[name='recordBulidCompany']").val(result.recordBulidCompany);
			$("input[name='recordTotalPrice']").val(result.recordTotalPrice);
			$("textarea[name='recordDescription']").val(result.recordDescription);
			$("textarea[name='recordReason']").val(result.recordReason);
			$("input[name='recordCommitTime']").val(result.recordCommitTime);
			$("input[name='preInvoiceDate']").val(result.preInvoiceDate);
			$("input[name='contractAttachment']").val(result.contractAttachment);
			$("textarea[name='recordRemarks']").val(result.recordRemarks);
			//$("select[name='recordRelatedContractId']").val(result.recordRelatedContractId);
			//$("select[name='recordRelatedContractId']").trigger("chosen:updated");

			loadCrmDataValue("/inner/innerOrgEmployee/listDic","recordName",result.recordName);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","recordSales",result.recordSales);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","contractDraftPerson",result.contractDraftPerson);
			loadCrmDataValue("/inner/orgJob/listDic", "jobId",result.jobId);
			loadCrmDataValue("/contract/contract/listDic", "recordRelatedContractId",result.recordRelatedContractId);
		}
	});
}
function nextStepThis(tabId,totalStep,lastBtn,nextBtn){
	nextStep(tabId,totalStep,lastBtn,nextBtn);
}
