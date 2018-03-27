var prefixreceived = "/payment/received"
$().ready(function() {
	loadDic("payment_received_received_contract_status","receivedContractStatus");
	validateRule();
	datetimepicker();
	received_ajax();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	alert($('#receivedTime').data('date'))
	$.ajax({
		cache : true,
		type : "POST",
		url : "/payment/received/update",
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
				layer.close(index);

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
// 绑定
function received_ajax(){
	$.ajax({
		url : prefixreceived + '/edit_ajax/' + $("#receivedId").val(),
		type : "get",
		data : {
			'receivedId' : $("#receivedId").val(),
		},
		success : function(data) {
			var result = data.received;
 			$("select[name='receivedContractStatus']").val(result.receivedContractStatus);
 			$("select[name='receivedContractStatus']").trigger("chosen:updated");
 			$("input[name='receivedPrice']").val(result.receivedPrice);
 			$("input[name='receivedCardNumber']").val(result.receivedCardNumber);
 			$("input[name='receivedType']").val(result.receivedType);
			$("input[name='receivedTime']").val(result.receivedTime);
 			$("textarea[name='receivedRemarks']").val(result.receivedRemarks);
		}
	});
}
