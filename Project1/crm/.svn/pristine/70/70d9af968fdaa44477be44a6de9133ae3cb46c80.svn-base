$().ready(function() {
	validateRule();
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/common/sysFile/upload', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
            	//alert(r.fileName);
            	$("#feedbackAttachment").val(r.fileName);
                //layer.msg(r.msg);
                //app.getData();
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    });
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/onlineFeedback/save",
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
			feedbackCategory : {
				required : true
			},
			feedbackPhoneNumber : {
				required : true,
				maxlength:20
			},
			feedbackMailbox : {
				required : true,
				maxlength:50
			},
			feedbackName : {
				required : true,
				maxlength:50
			},
			feedbackCompanyName : {
				required : true,
				maxlength:50
			},
			feedbackProduct : {
				required : true,
				maxlength:50
			},
			feedbackDescription : {
				required : true,
				maxlength:1000
			}
		},
		messages : {
			feedbackCategory : {
				required : icon + "请选择反馈内容分类"
			},
			feedbackPhoneNumber : {
				required : icon + "请输入手机",
				maxlength:icon + "字符长度不能大于20"
			},
			feedbackMailbox : {
				required : icon + "请输入邮箱",
				maxlength:icon + "字符长度不能大于50"
			},
			feedbackName : {
				required : icon + "请输入姓名",
				maxlength:icon + "字符长度不能大于50"
			},
			feedbackCompanyName : {
				required : icon + "请输入公司名称",
				maxlength:icon + "字符长度不能大于50"
			},
			feedbackProduct : {
				required : icon + "请输入使用产品",
				maxlength:icon + "字符长度不能大于50"
			},
			feedbackDescription : {
				required : icon + "请输入问题描述",
				maxlength:icon + "字符长度不能大于1000"
			}
		}
	})
}