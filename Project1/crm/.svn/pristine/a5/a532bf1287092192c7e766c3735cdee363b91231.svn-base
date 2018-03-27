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
            	$("#consultationAttachment").val(r.fileName);
                //layer.msg(r.msg);
                //app.getData();
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    });
	
	var address = new addressResolve({
	    proId: 'consultationProvence',
	    cityId: 'consultationCity',
	    areaId: 'consultationCounty'
	  });
	address.init();
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
		url : "/sales/projectConsultation/update",
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
			consultationName : {
				required : true,
				maxlength:6
			},
			consultationPhoneNumber : {
				required : true,
				maxlength:20
			},
			consultationMailbox : {
				required : true,
				maxlength:50
			},
			consultationCompanyName : {
				required : true,
				maxlength:50
			},
			consultationProvence : {
				required : true
			},
			consultationCity : {
				required : true
			},
			consultationCounty : {
				required : true
			},
			consultationIndustry : {
				required : true,
				maxlength:50
			},
			consultationProduct : {
				required : true,
				maxlength:50
			},
			consultationProjectScale : {
				required : true,
				maxlength:50
			},
			consultationProjectIntroduct : {
				required : true,
				maxlength:1000
			}
		},
		messages : {
			consultationName : {
				required : icon + "请输入姓名",
				maxlength:icon + "字符长度不能大于6"
			},
			consultationPhoneNumber : {
				required : icon + "请输入手机",
				maxlength:icon + "字符长度不能大于20"
			},
			consultationMailbox : {
				required : icon + "请输入邮箱",
				maxlength:icon + "字符长度不能大于50"
			},
			consultationCompanyName : {
				required : icon + "请输入公司名称",
				maxlength:icon + "字符长度不能大于50"
			},
			consultationProvence : {
				required : icon + "请输入省"
			},
			consultationCity : {
				required : icon + "请输入市"
			},
			consultationCounty : {
				required : icon + "请输入区"
			},
			consultationIndustry : {
				required : icon + "请输入所在行业",
				maxlength:icon + "字符长度不能大于50"
			},
			consultationProduct : {
				required : icon + "请输入感兴趣产品",
				maxlength:icon + "字符长度不能大于50"
			},
			consultationProjectScale : {
				required : icon + "请输入项目规模",
				maxlength:icon + "字符长度不能大于50"
			},
			consultationProjectIntroduct : {
				required : icon + "请输入项目概述",
				maxlength:icon + "字符长度不能大于1000"
			}
		}
	})
}