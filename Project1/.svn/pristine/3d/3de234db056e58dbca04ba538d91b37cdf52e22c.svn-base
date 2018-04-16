$().ready(function() {
	loadDic("payment_received_received_contract_status","receivedContractStatus");
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
		url : "/payment/received/save",
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
				receivedTime : {
					required : true
				},
				receivedPrice : {
					required : true,
					rangelength:[1,10]
				},
				receivedCardNumber : {
					required : true,
					rangelength:[1,25]
				},
				receivedType : {
					required : true,
					rangelength:[1,20]
				},
				receivedContractStatus : {
					required : true,
				},
				receivedRemarks: {
					rangelength:[1,200]
				}
			},
			messages : {
				receivedTime : {
					required : icon + "收款时间不能为空"
				},
				receivedPrice : {
					required : icon + "收款金额不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				receivedCardNumber : {
					required : icon + "收款账户不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 25 之间的字符串"
				},
				receivedType : {
					required : icon + "款项类型不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 20 之间的字符串"
				},
				receivedContractStatus : {
					required : icon + "合同状态不能为空",
				},
				receivedRemarks : {
					rangelength: icon + "请输入一个长度介于 1 和 200 之间的字符串"
				}
			}
	})
}
function datetimepicker() {
	$('#receivedTime').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}
