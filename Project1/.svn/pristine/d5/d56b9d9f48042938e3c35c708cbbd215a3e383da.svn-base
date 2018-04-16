var prefixpurchase = "/budget/budgetPurchase"
$().ready(function() {
	validateRule();
	purchaseMapper_edit();
	$('#purchasePrice').bind('input propertychange', function() { 
		 changeSum();
    });
	 $('#purchaseCustomerRate').bind('input propertychange', function() { 
		 checkPercent("purchaseCustomerRate","purchaseProjectRate");
		 changeSum();
    });
	 $('#purchaseProjectRate').bind('input propertychange', function() { 
		 checkPercent("purchaseProjectRate","purchaseCustomerRate");
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
		url : "/budget/budgetPurchase/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadbudgetPurchase();
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
				required : true
			},
			purchasePrice : {
				required : true,
				number:true,
				maxlength:10
			},
			purchaseCustomerRate : {
				number:true,
				max:100
			},
			purchaseProjectRate : {
				number:true,
				max:100
			}
		},
		messages : {
			purchaseType : {
				required : icon + "请选择采购类型"
			},
			purchasePrice : {
				required : icon + "请输入采购金额预估",
				number:icon + "请输入有效的数字",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseCustomerRate : {
				number:icon + "请输入有效的数字",
				max:icon + "输入值不能大于100。"
			},
			purchaseProjectRate : {
				number:icon + "请输入有效的数字",
				max:icon + "输入值不能大于100。"
			}
		}
	})
}
//修改——显示数据绑定
function purchaseMapper_edit(){
	$.ajax({
		url : prefixpurchase + '/edit_ajax/' + $("#purchaseId").val(),
		type : "get",
		data : {
			'purchaseId' : $("#purchaseId").val()
		},
		success : function(data) {
			var result = data.purchase;
			$("input[name='purchaseId']").val(result.purchaseId);
			$("input[name='budgetId']").val(result.budgetId);
			$("input[name='purchasePrice']").val(result.purchasePrice);
			$("input[name='purchaseDescription']").val(result.purchaseDescription);
			$("input[name='purchaseCustomerRate']").val(result.purchaseCustomerRate);
			$("input[name='purchaseProjectRate']").val(result.purchaseProjectRate);
			$("input[name='purchaseTotalPrice']").val(result.purchaseTotalPrice);
			$("textarea[name='purchaseRemarks']").val(result.purchaseRemarks);
			loadDicValue("budget_Purchase_Type","purchaseType",result.purchaseType);
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
    var purchasePrice=$("#purchasePrice").val();
    var purchaseProjectRate=$("#purchaseProjectRate").val();
    var purchaseCustomerRate=$("#purchaseCustomerRate").val();
    if( isNaN(purchasePrice) || isNaN(purchaseProjectRate) || isNaN(purchaseCustomerRate)){
    	return;
    }
    var sum= ((Number(purchasePrice)/Number(purchaseProjectRate==""||purchaseProjectRate=="0"?1:purchaseProjectRate))*Number(purchaseCustomerRate)+Number(purchasePrice)).toFixed(2); 
    $("#purchaseTotalPrice").val(sum);   
}