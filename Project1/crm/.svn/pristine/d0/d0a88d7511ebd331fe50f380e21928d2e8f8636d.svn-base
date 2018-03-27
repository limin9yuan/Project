var prefixpaid = "/payment/paid"
$().ready(function() {
	validateRule();
	datetimepicker();
	paidMapper_edit();
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
		url : "/payment/paid/update",
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
			paidPrice : {
				required : true,
				maxlength:10
			},
			paidAccountPrice : {
				required : true,
				maxlength:10
			},
			paidAccountNumber : {
				required : true,
				maxlength:25
			},
			paidType : {
				required : true,
				maxlength:50
			},
			paidTime: {
				required : true
			}/*,
			paidStatus: {
				required : true
			}*/
		},
		messages : {
			paidPrice : {
				required : icon + "请输入付款金额",
				maxlength:icon + "字符长度不能大于10"
			},
			paidAccountPrice : {
				required : icon + "请输入应付款金额",
				maxlength:icon + "字符长度不能大于10"
			},
			paidAccountNumber : {
				required : icon + "请输入付款账号",
				maxlength:icon + "字符长度不能大于25"
			},
			paidType : {
				required : icon + "请输入款项类型",
				maxlength:icon + "字符长度不能大于20"
			},
			paidTime : {
				required : icon + "付款时间不能为空"
			}/*,
			paidStatus : {
				required : icon + "请选择合同状态"
			}*/
		}
	})
}
function datetimepicker() {
	 $('#paidTime').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    });   
}
//修改——显示数据绑定
function paidMapper_edit(){
	$.ajax({
		url : prefixpaid + '/edit_ajax/' + $("#paidId").val(),
		type : "get",
		data : {
			'paidId' : $("#paidId").val()
		},
		success : function(data) {
			var result = data.paid;
			$("input[name='paidId']").val(result.paidId);
			$("input[name='paidPrice']").val(result.paidPrice);
			$("input[name='paidAccountPrice']").val(result.paidAccountPrice);
			$("input[name='paidAccountNumber']").val(result.paidAccountNumber);
			$("input[name='paidType']").val(result.paidType);
			$("input[name='paidTime']").val(result.paidTime);
			$("input[name='receiptDelayTime']").val(result.receiptDelayTime);
			$("input[name='paidStatus']").val(result.paidStatus);
			$("textarea[name='paidRemarks']").val(result.paidRemarks);		
		}
	});
}