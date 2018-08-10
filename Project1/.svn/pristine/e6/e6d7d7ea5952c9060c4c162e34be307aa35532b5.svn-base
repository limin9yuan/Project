$().ready(function() {
	validateRule();
	// alert($("#contactIds", window.parent.document).val());
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

	var tmpContactId = $("#contactId", window.parent.document).val() == undefined ?
	$("#contactIds", window.parent.document).val() : $("#contactId", window.parent.document).val()
	if (tmpContactId ==-1) {
		parent.layer.msg("请先保存基本信息");
		return;
	}
	$('#contactId').val(tmpContactId);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/customerContact/saveField",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadCustomField();
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
				required : icon + "请输入姓名"
			}
		}
	})
}
