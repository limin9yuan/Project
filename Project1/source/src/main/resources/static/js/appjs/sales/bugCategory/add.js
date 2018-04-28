$().ready(function() {
	loadDic("sales_categoryType","bugCategoryType");
	loadCrmData("/project/moduleCategory/listDic","bugCategory");
	loadCrmData("/project/productCategory/listDic","productId");
	loadCrmData("/project/moduleCategory/listDic","moduleId");
	validateRule();
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
		url : "/sales/bugCategory/save",
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
			bugCategoryType : {
				required : true
			},
			bugCategory : {
				required : true
			},
			productId : {
				required : true
			},
			moduleId : {
				required : true
			},
			elseCategory : {
				required : true,
				maxlength:50
			}
		},
		messages : {
			bugCategoryType : {
				required : icon + "请选择分类类别"
			},
			bugCategory : {
				required : icon + "请选择Bug分类"
			},
			productId : {
				required : icon + "请选择产品分类"
			},
			moduleId : {
				required : icon + "请选择模块分类"
			},
			elseCategory : {
				required : icon + "请输入其他分类",
				maxlength:icon + "字符长度不能大于50"
			}
		}
	})
}