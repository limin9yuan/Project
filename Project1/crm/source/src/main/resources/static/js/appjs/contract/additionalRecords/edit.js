var prefixadditionalRecords = "/contract/additionalRecords"
//var result;
$().ready(function() {
	loadCrmDataValue("/inner/innerOrgEmployee/listDic","recordName");
	loadCrmDataValue("/inner/innerOrgEmployee/listDic","recordSales");
	loadCrmDataValue("/inner/innerOrgEmployee/listDic","contractDraftPerson");
	loadCrmDataValue("/inner/orgJob/listDic", "jobId");
	loadCrmDataValue("/sales/salesProject/listDic", "projectId");
	/*$('#myTab a[href="#payment"]').on('shown.bs.tab', function(e) {
		//loadDicValue("sales_record_type","serviceType",result.serviceType);
	});*/
	validateRule();
	datetimepicker();
	additionalRecordsMapper_edit();
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
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
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
		url : prefixadditionalRecords + '/edit_ajax/' + $("#additionalRecords").val(),
		type : "get",
		data : {
			'recordId' : $("#additionalRecords").val(),
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
			
			$("select[name='recordName']").val(result.recordName);
			$("select[name='recordName']").trigger("chosen:updated");
			$("select[name='jobId']").val(result.jobId);
			$("select[name='jobId']").trigger("chosen:updated");
			$("select[name='projectId']").val(result.projectId);
			$("select[name='projectId']").trigger("chosen:updated");
			$("select[name='recordSales']").val(result.recordSales);
			$("select[name='recordSales']").trigger("chosen:updated");
			$("select[name='recordRelatedContractId']").val(result.recordRelatedContractId);
			$("select[name='recordRelatedContractId']").trigger("chosen:updated");
			$("select[name='contractDraftPerson']").val(result.contractDraftPerson);
			$("select[name='contractDraftPerson']").trigger("chosen:updated");
		}
	});
}