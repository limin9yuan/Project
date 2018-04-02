$().ready(function() {
	loadDic("budget_Expenses_Type","expensesType");
	validateRule();
	 $('#expensesPlanPrice').bind('input propertychange', function() {    
         var expensesPlanPrice=$("#expensesPlanPrice").val();
         var expensesProjectRate=$("#expensesProjectRate").val();
         var expensesCustomerRate=$("#expensesCustomerRate").val();
         var sum= expensesPlanPrice/expensesProjectRate*expensesCustomerRate; 
         $("#expensesTotalPrice").val(sum);   
     });
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
			expensesCustomerRate : {
				max:100
			},
			expensesProjectRate : {
				max:100
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
			expensesCustomerRate : {
				max:icon + "输入值不能大于100。"
			},
			expensesProjectRate : {
				max:icon + "输入值不能大于100。"
			}
		}
	})
}