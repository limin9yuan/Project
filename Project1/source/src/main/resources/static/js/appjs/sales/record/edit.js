var prefixrecord = "/sales/record"
$().ready(function() {
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
      				parent.layer.msg(r.msg);
//     				 app.getData();

      			} else {
      				parent.layer.msg(r.msg)
      			}
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    });
	validateRule();
	datetimepicker();
	recordMapper_edit();
	getMainAndCopyPerson_ajax();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#recordId").val()+"/sales_record";
	var mainPerson="";
	var copyPerson="";
	var isMainPerson;
	$.ajax({
		url : tmpUrl,
		type : "get",
		data : {
			// 'projectId' : $("#projectId").val(),
		},
		success : function(data) {
			result = data.mainAndCopyPerson;
			var mainPersonIds = "";
			var copyPersonIds = "";
			for (var i = 0; i < result.length; i++) {
				if (result[i].mainPerson == 1) {
					mainPerson = mainPerson + "<div class='personDiv' id=" + (result[i].employeeId + "_1") +
								" onclick='deleteMainPerson(\"" + (result[i].employeeId + "_1") +"\" )'>" +
								result[i].person +"</div>";
					$('#sendPerson').html(mainPerson);
					if (mainPersonIds == "") {
						mainPersonIds = result[i].employeeId
					}else {
						mainPersonIds = mainPersonIds + ","+result[i].employeeId;
					}

					$('#mainPersonId').val(mainPersonIds);

				}
				if (result[i].mainPerson == 0) {
					copyPerson = copyPerson + "<div class='personDiv' id=" + (result[i].employeeId + "_2") +
								" onclick='deleteCopyPerson(\"" + (result[i].employeeId + "_2") +"\" )'>" +
								result[i].person +"</div>";
					$('#receivePerson').html(copyPerson);
					if (copyPersonIds == "") {
						copyPersonIds = result[i].employeeId
					}else {
						copyPersonIds = copyPersonIds + ","+result[i].employeeId;
					}

					$('#copyPersonId').val(copyPersonIds);


				}
			}
		}
	});
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/record/update",
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
//修改——显示数据绑定
function recordMapper_edit(){
	$.ajax({
		url : prefixrecord + '/edit_ajax/' + $("#recordId").val(),
		type : "get",
		data : {
			'recordId' : $("#recordId").val()
		},
		success : function(data) {
			var result = data.record;
			$("input[name='recordId']").val(result.recordId);
			$("input[name='recordName']").val(result.recordName);
			$("input[name='recordBeginDate']").val(result.recordBeginDate);
			$("input[name='recordEndDate']").val(result.recordEndDate);
			$("input[name='recordSaleOpportunity']").val(result.recordSaleOpportunity);
			$("input[name='recordExpenseActual']").val(result.recordExpenseActual);
			$("input[name='recordAttachment']").val(result.recordAttachment);
			$("textarea[name='recordRemarks']").val(result.recordRemarks);
			loadDicValue("sales_record_phase","recordPhase",result.recordPhase);
			loadDicValue("sales_record_expense_category","recordExpenseCategory",result.recordExpenseCategory);
			loadDicValue("sales_Record_Execute_Status","recordExecuteStatus",result.recordExecuteStatus);
			loadCrmDataValue("/sales/business/listDic","businessId",result.businessId);
			loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","recordExecutor",result.recordExecutor);
			loadCrmDataValue("/project/project/listDic","projectId",result.projectId);
		}
	});
}

//*********************** 文件下载及相关 ************************************
//文件大小
function getFileSize() {
	console.log("文件大小数据加载");
	$.ajax({
		type : "Post",
		url : "/sales/companyCustomer/getFileSize",
		data : {},
		async : false,
		success : function(data) {
			// 将后台传来的fileSizeString文件代销传给html页面的fileSizeString
			$("#fileSizeString").val(data);
		},
		error : function(msg) {
			parent.layer.msg("文件总大小计算错误！");
		}
	});
}
//下载全部附件
function download() {
	console.log("下载全部附件");
	$.ajax({
		type : "Post",
		url : "/sales/companyCustomer/compressedFile",
		data : {},
		success : function(data) {
			parent.layer.msg("附件下载成功！已保存在您的卓面！");
			// alert("success");
		},
		error : function(msg) {
			parent.layer.msg("附件下载失败!请联系管理员！");
			// alert(msg);
		}
	});
}

//*************************** END *************************************=======
