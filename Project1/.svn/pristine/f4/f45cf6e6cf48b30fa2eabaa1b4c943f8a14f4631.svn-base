var prefixbugCategory = "/sales/bugCategory"
$().ready(function() {
	validateRule();
	bugCategoryMapper_edit();
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
		url : "/sales/bugCategory/update",
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
//修改——显示数据绑定
function bugCategoryMapper_edit(){
	$.ajax({
		url : prefixbugCategory + '/edit_ajax/' + $("#bugId").val(),
		type : "get",
		data : {
			'bugId' : $("#bugId").val()
		},
		success : function(data) {
			var result = data.bugCategory;
			$("input[name='bugId']").val(result.bugId);
			$("input[name='newCategory']").val(result.newCategory);
			$("input[name='newCategoryContent']").val(result.newCategoryContent);
			$("input[name='elseCategory']").val(result.elseCategory);
			$("textarea[name='requirementDescription']").val(result.requirementDescription);
			$("textarea[name='bugRemarks']").val(result.bugRemarks);		
			loadDicValue("sales_categoryType","bugCategoryType",result.bugCategoryType);
			loadCrmDataValue("/project/moduleCategory/listDic","bugCategory",result.bugCategory);
			loadCrmDataValue("/project/productCategory/listDic","productId",result.productId);
			loadCrmDataValue("/project/moduleCategory/listDic","moduleId",result.moduleId);
		}
	});
}