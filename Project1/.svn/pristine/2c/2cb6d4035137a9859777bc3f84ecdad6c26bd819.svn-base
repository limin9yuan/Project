var prefixexpenses = "/budget/expenses"
$().ready(function() {
	validateRule();
	expensesMapper_edit();
	$('#expensesPlanPrice').bind('input propertychange', function() { 
		 changeSum();
    });
	 $('#expensesCustomerRate').bind('input propertychange', function() { 
		 checkPercent("expensesCustomerRate","expensesProjectRate");
		 changeSum();
    });
	 $('#expensesProjectRate').bind('input propertychange', function() { 
		 checkPercent("expensesProjectRate","expensesCustomerRate");
		 changeSum();
    });
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
		url : "/budget/expenses/update",
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
				number:true,
				maxlength:10
			},
			expensesCustomerRate : {
				number:true,
				max:100
			},
			expensesProjectRate : {
				number:true,
				max:100
			}
		},
		messages : {
			expensesType : {
				required : icon + "请选择报销类型"
			},
			expensesPlanPrice : {
				required : icon + "请输入报销金额预估",
				number:icon + "请输入有效的数字",
				maxlength:icon + "字符长度不能大于10"
			},
			expensesCustomerRate : {
				number:icon + "请输入有效的数字",
				max:icon + "输入值不能大于100。"
			},
			expensesProjectRate : {
				number:icon + "请输入有效的数字",
				max:icon + "输入值不能大于100。"
			}
		}
	})
}
//修改——显示数据绑定
function expensesMapper_edit(){
	$.ajax({
		url : prefixexpenses + '/edit_ajax/' + $("#expensesId").val(),
		type : "get",
		data : {
			'expensesId' : $("#expensesId").val()
		},
		success : function(data) {
			var result = data.expenses;
			$("input[name='expensesId']").val(result.expensesId);
			$("input[name='budgetId']").val(result.budgetId);
			$("input[name='expensesPlanPrice']").val(result.expensesPlanPrice);
			$("input[name='expensesPlanDescription']").val(result.expensesPlanDescription);
			$("input[name='expensesCustomerRate']").val(result.expensesCustomerRate);
			$("input[name='expensesProjectRate']").val(result.expensesProjectRate);
			$("input[name='expensesTotalPrice']").val(result.expensesTotalPrice);
			$("textarea[name='expensesRemarks']").val(result.expensesRemarks);
			loadDicValue("budget_Expenses_Type","expensesType",result.expensesType);
		}
	});
}
function checkPercent(obj1,obj2){
	var obj1Value=$("#"+obj1).val();
    var obj2Value=$("#"+obj2).val();
    if( isNaN(obj1Value) || isNaN(obj2Value)){
   	 return;
    }    
    if(Number(obj1Value)+Number(obj2Value)>100){
	   	 if(Number($("#"+obj1).val())>100){
	   		 $("#"+obj1).val(100);
	   		 $("#"+obj2).val(0);
	   	 } 
	   	if(Number($("#"+obj2).val())>100){
	  		 $("#"+obj2).val(100);
	  		 $("#"+obj1).val(0);
	  	 }
    }
    $("#"+obj2).val(100-Number($("#"+obj1).val()));
}
function changeSum(){		   
    var expensesPlanPrice=$("#expensesPlanPrice").val();
    var expensesProjectRate=$("#expensesProjectRate").val();
    var expensesCustomerRate=$("#expensesCustomerRate").val();
    if( isNaN(expensesPlanPrice) || isNaN(expensesProjectRate) || isNaN(expensesCustomerRate)){
    	return;
    }
    var sum= ((Number(expensesPlanPrice)/Number(expensesProjectRate==""||expensesProjectRate=="0"?1:expensesProjectRate))*Number(expensesCustomerRate)+Number(expensesPlanPrice)).toFixed(2); 
    $("#expensesTotalPrice").val(sum);   
}