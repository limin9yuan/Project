var prefixsalesProject = "/sales/salesProject"
$().ready(function() {
	
	validateRule();
	datetimepicker();
	salesProjectMapper_edit();
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
		url : "/sales/salesProject/update",
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
			projectBusiness : {
				required : true
			},
			projectName : {
				required : true,
				maxlength:50
			},
			projectSales : {
				required : true
			},
			projectManager : {
				required : true
			},
			projectBeginDate: {
				required : true
			},
			projectEndDate: {
				required : true
			},
			projectGategory : {
				required : true
			},
			projectPhase: {
				required : true,
				maxlength:20
			}
		},
		messages : {
			projectBusiness : {
				required : icon + "请选择业务名称"
			},
			projectName : {
				required : icon + "请输入项目名称",
				maxlength:icon + "字符长度不能大于50"
			},
			projectSales : {
				required : icon + "请选择销售负责人"
			},
			projectManager : {
				required : icon + "请选择售前经理"
			},
			projectBeginDate : {
				required : icon + "开始时间不能为空"
			},
			projectEndDate : {
				required : icon + "结束时间不能为空"
			},
			projectGategory : {
				required : icon + "请选择项目类型"
			},
			projectPhase : {
				required : icon + "请输入项目阶段",
				maxlength:icon + "字符长度不能大于20"
			}
		}
	})
}

function datetimepicker() {
	 $('#projectBeginDate').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    });  
	 $('#projectEndDate').datetimepicker({  
	        format: 'YYYY-MM-DD ',  
	        locale: moment.locale('zh-cn')  
	    }); 
}

//修改——显示数据绑定
function salesProjectMapper_edit(){
	//alert($("#projectId").val())
	$.ajax({
		url : prefixsalesProject + '/edit_ajax/' + $("#projectId").val(),
		type : "get",
		data : {
			'projectId' : $("#projectId").val()
		},
		success : function(data) {
			var result = data.salesProject;
			$("input[name='projectId']").val(result.projectId);
			$("input[name='projectName']").val(result.projectName);
			$("input[name='projectBeginDate']").val(result.projectBeginDate);
			$("input[name='projectEndDate']").val(result.projectEndDate);
			$("input[name='projectPhase']").val(result.projectPhase);
			$("input[name='projectOldId']").val(result.projectOldId);
			$("textarea[name='projectDescription']").val(result.projectDescription);
			$("textarea[name='projectRemarks']").val(result.projectRemarks);
			loadDicValue("sales_project_gategory","projectGategory",result.projectGategory);
			loadCrmDataValue("/sales/business/listDic","projectBusiness",result.projectBusiness);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","projectSales",result.projectSales);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","projectManager",result.projectManager);
		}
	});
}