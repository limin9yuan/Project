$().ready(function() {
	validateRule();
	editField_ajax();
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
		url : "/sales/companyCustomer/updateField",
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
function editField_ajax(){
	$.ajax({
		url : '/sales/companyCustomer/editField_ajax/' + $("#id").val(),
		type : "get",
		data : {
			// 'purchaseId' : $("#purchase").val(),
		},
		success : function(data) {
			var result = data.fieldList;
			$("input[name='fieldName']").val(result.fieldName);
			$("select[name='belongCategory']").val(result.belongCategory);
			$("select[name='belongCategory']").trigger("chosen:updated");
			$("input[name='content']").val(result.content);
		}
	});
}
