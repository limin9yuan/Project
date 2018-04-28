$().ready(function() {
	validateRule();
	loadDic("sales_categoryType","feedbackCategory");
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

//电话号码验证 
/*jQuery.validator.addMethod("isTel", function(value, element) { 
  var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678 
  return this.optional(element) || (tel.test(value)); 
}, "请正确填写您的电话号码"); 

// 联系电话(手机/电话皆可)验证 
jQuery.validator.addMethod("isPhone", function(value,element) { 
  var length = value.length; 
  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
  var tel = /^\d{3,4}-?\d{7,9}$/; 
  return this.optional(element) || (tel.test(value) || mobile.test(value)); 

}, "请正确填写您的联系电话"); */

//手机号码验证 
jQuery.validator.addMethod("isMobile", function(value, element) { 
  var length = value.length; 
  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
  return this.optional(element) || (length == 11 && mobile.test(value)); 
}, "请正确填写您的手机号码"); 

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
				isMobile:true 
			},
			feedbackMailbox : {
				required : true,
				email: true
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
				isMobile: "请输入一个有效的联系电话" 
			},
			feedbackMailbox : {
				required : icon + "请输入邮箱",
				email: "请输入一个正确的邮箱"
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