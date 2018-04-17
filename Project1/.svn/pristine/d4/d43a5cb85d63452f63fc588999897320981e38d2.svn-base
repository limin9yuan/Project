$().ready(function() {
	datetimepicker();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		savePlan();
	}
});
function savePlan() {
	var tmpRecordId = $("#recordId",window.parent.document).val()==undefined ? $("#resultRecordId",window.parent.document).val()
			:$("#recordId",window.parent.document).val();
	var tmpContractId = $("#recordRelatedContractId",window.parent.document).val();
	if(tmpRecordId==-1){
		parent.layer.msg("请先保存合同增补基本信息");
		return;
	}
	if(tmpRecordId>0){
		$('#contractId').val(tmpContractId);
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/additionalRecords/plan/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadPlan();
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
			planDeliveryDate : {
				required : true
			},
			planDeliveryContent : {
				required : true,
				maxlength:1000
			}
		},
		messages : {
			planDeliveryDate : {
				required : icon + "计划交付时间不能为空"
			},
			planDeliveryContent : {
				required : icon + "请输入计划交付内容",
				maxlength:icon + "字符长度不能大于1000"
			}
		}
	})
}
function datetimepicker() {
	// 付款计划时间
	$('#planDeliveryDate').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}