$().ready(function() {
	loadDic("budget_Expenses_Type","expensesType");
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		saveExpenses();
	}
});
function saveExpenses() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/budget/expenses/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadExpenses();
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
			expensesType : {
				required : true
			},
			expensesPlanPrice : {
				required : true,
				maxlength:10
			},
			expensesTotalPrice : {
				required : true,
				maxlength:12
			}
		},
		messages : {
			expensesType : {
				required : icon + "请选择报销类型"
			},
			expensesPlanPrice : {
				required : icon + "请输入报销金额预估",
				maxlength:icon + "字符长度不能大于10"
			},
			expensesTotalPrice : {
				required : icon + "请输入总计",
				maxlength:icon + "字符长度不能大于12"
			}
		}
	})
}