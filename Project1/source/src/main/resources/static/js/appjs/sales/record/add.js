$().ready(function() {
	loadDic("sales_record_phase","recordPhase");
	loadDic("sales_record_expense_category","recordExpenseCategory");
	loadDic("sales_Record_Execute_Status","recordExecuteStatus");
	loadCrmData("/sales/business/listDic","businessId");
	loadCrmData("/sales/companyCustomer/listDic","customerId");
	loadCrmData("/inner/innerOrgEmployee/listDic","recordExecutor");
	loadCrmData("/project/project/listDic","projectId");
	
	
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/sales/record/upload', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
            	if (r.code == 0) {
	 				if (r.recordAttachment > 0) {
	 					$('#ids').val(r.recordAttachment);
	 					$('#recordAttachment').val(r.recordAttachment+','+document.getElementById("recordAttachment").value);
	 				}
            	$("#serviceAttachment").val(r.fileName);
                layer.msg(r.msg);
                app.getData();
            }else {
 				parent.layer.alert(r.msg)
            }
            }
        });
    });
	validateRule();
	datetimepicker();
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
		url : "/sales/record/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				closeParenWindow();
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
			customerId : {
				required : true
			},
			businessId : {
				required : true
			},
			projectId : {
				required : true
			},
			recordName : {
				required : true,
				maxlength:50
			},
			recordPhase : {
				required : true
			},
			recordExecutor : {
				required : true
			},
			recordExecuteStatus : {
				required : true
			},
			recordBeginDate: {
				required : true
			},recordEndDate: {
				required : true
			},recordSaleOpportunity: {
				required : true,
				maxlength:200
			},
			recordExpenseCategory : {
				required : true
			}
		},
		messages : {
			customerId : {
				required : icon + "请选择客户名称"
			},
			businessId : {
				required : icon + "请选择业务名称"
			},
			projectId : {
				required : icon + "请选择项目名称"
			},
			recordName : {
				required : icon + "请输入行动主题",
				maxlength:icon + "字符长度不能大于50"
			},
			recordPhase : {
				required : icon + "请选择阶段"
			},
			recordExecutor : {
				required : icon + "请选择执行人"
			},
			recordExecuteStatus : {
				required : icon + "请选择执行状态"
			},
			recordBeginDate : {
				required : icon + "实际执行开始时间不能为空"
			},recordEndDate : {
				required : icon + "实际执行开始时间不能为空"
			},recordSaleOpportunity : {
				required : icon + "请输入销售机会",
				maxlength:icon + "字符长度不能大于200"
			},
			recordExpenseCategory : {
				required : icon + "请选择费用类型"
			}
		}
	})
}
function datetimepicker() {
	 $('#recordBeginDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
	 $('#recordEndDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
}
