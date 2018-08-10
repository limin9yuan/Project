$().ready(function() {
	loadDic("sales_customer_product","complaintProductCategory");
	loadDic("sales_project_gategory","complaintProjectType");
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/sales/competitor/upload2', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
            	$("#complaintAttachment").val(r.fileName);
                //layer.msg(r.msg);
                //app.getData();
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    });
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		savecompetitor();
	}
});
function savecompetitor() {
	var tmpCustomerId = $("#customerId", window.parent.document).val() == undefined ? 
			$("#customerIds", window.parent.document).val() : $("#customerId", window.parent.document).val()
				if (tmpCustomerId ==-1) {
					parent.layer.msg("请先保存所有带红色*号的信息");
					return;
				}
				$('#customerId').val(tmpCustomerId);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/competitor/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadCompetitor();
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
			//产品价格
			complaintProductPrice : {
				number : true,
				maxlength:14
			}
		},
		messages : {
			complaintProductPrice : {
				number : icon + "产品价格必须为数字！",
				maxlength:icon + "产品价格不能大于14位！"
			}
		}
	})
}