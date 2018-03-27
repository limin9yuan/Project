$().ready(function() {
	validateRule();
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
		url : "/budget/budgetPurchase/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadPurchase();
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
			purchaseType : {
				required : true,
				maxlength:20
			},
			purchasePrice : {
				required : true,
				maxlength:10
			},
			PurchaseTotalPrice : {
				required : true,
				maxlength:12
			}
		},
		messages : {
			purchaseType : {
				required : icon + "请输入采购类型",
				maxlength:icon + "字符长度不能大于20"
			},
			purchasePrice : {
				required : icon + "请输入采购金额预估",
				maxlength:icon + "字符长度不能大于10"
			},
			PurchaseTotalPrice : {
				required : icon + "请输入总计",
				maxlength:icon + "字符长度不能大于12"
			}
		}
	})
}