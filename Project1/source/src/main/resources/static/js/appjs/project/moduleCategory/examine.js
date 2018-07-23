var prefixmoduleCategory = "/project/moduleCategory"
$().ready(function() {
	//loadCrmDataValue("/project/productCategory/listDic","productId");
	validateRule();
	moduleCategoryMapper_edit();
});

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
		rules : {
			moduleName : {
				required : true
			},
			productId : {
				required : true
			},
			moduleDescription : {
				required : true
			}
		},
		messages : {
			moduleName : {
				required : icon + "请输入模块名称"
			},
			productId : {
				required : icon + "请输入产品名称"
			},
			moduleDescription : {
				required : icon + "请输入模块描述"
			}
		}
	})
}
//修改——显示数据绑定
function moduleCategoryMapper_edit(){
	$.ajax({
		url : prefixmoduleCategory + '/edit_ajax/' + $("#moduleId").val(),
		type : "get",
		data : {
			'moduleId' : $("#moduleId").val(),
		},
		success : function(data) {
			var result = data.moduleCategory;
			$("input[name='moduleId']").val(result.moduleId);
			$("input[name='moduleName']").val(result.moduleName);
			$("input[name='moduleAttachment']").val(result.moduleAttachment);
			$("textarea[name='moduleDescription']").val(result.moduleDescription);
			$("textarea[name='moduleRemark']").val(result.moduleRemark);
			
			loadCrmDataValue("/project/productCategory/listDic","productId",result.productId);
		}
	});
}
