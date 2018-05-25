var prefix="/contract/travel"
$().ready(function() {
	 $('#travelDepartureDate').datetimepicker({
         format: 'YYYY-MM-DD',
         locale: moment.locale('zh-cn')
     });
	 $('#travelReturnDate').datetimepicker({
         format: 'YYYY-MM-DD',
         locale: moment.locale('zh-cn')
     });

	 travel_edit();
	validateRule();
	getMainAndCopyPerson_ajax();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#travelId").val() +"/approval_travel";
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
								" onclick='delete(\"" + (result[i].employeeId + "_1") +"\" )'>" +
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
								" onclick='delete(\"" + (result[i].employeeId + "_2") +"\" )'>" +
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
		url : "/contract/travel/update",
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
		ignore : ":hidden:not(select)",
		rules : {
			// 企业客户编号
			customerId : {
				required : true
			},
			// 业务编号
			businessId : {
				required : true
			},
			// 项目编号
			projectId : {
				required : true
			},
			// 出差人姓名
			travelName : {
				required : true
			},
			// 出发地
			travelPlaceIssue : {
				required : true
			},
			// 目的地
			travelPlaceEnded : {
				required : true
			},
			// 拟出差时间
			travelDepartureDate : {
				required : true
			},
			// 拟返程时间
			travelReturnDate : {
				required : true
			},
			// 预计费用类别
			travelPlanCostType : {
				required : true
			},
			// 预计交通方式
			travelVisitMode : {
				required : true
			},
			// 计划任务信息
			travelPlanTask : {
				required : true
			}

		},
		messages : {
			// 企业客户编号
			customerId : {
				required :  icon + "请选择企业客户编号！"
			},
			// 业务编号
			businessId : {
				required :  icon + "请选择业务编号！"
			},
			// 项目编号
			projectId : {
				required :  icon + "请选择项目编号！"
			},
			// 出差人姓名
			travelName : {
				required :  icon + "输入出差人姓名！"
			},
			// 出发地
			travelPlaceIssue : {
				required :  icon + "请输入出发地！"
			},
			// 目的地
			travelPlaceEnded : {
				required :  icon + "请输入目的地！"
			},
			// 拟出差时间
			travelDepartureDate : {
				required :  icon + "请输入拟出差时间！"
			},
			// 拟返程时间
			travelReturnDate : {
				required :  icon + "请输入拟返程时间！"
			},
			// 预计费用类别
			travelPlanCostType : {
				required : icon + "请输入预计费用类别！"
			},
			// 预计交通方式
			travelVisitMode : {
				required : icon + "请选择预计交通方式！"
			},
			// 计划任务信息
			travelPlanTask : {
				required : icon + "请输入计划任务信息！！"
			}
		}
	})
}

//修改--实现绑定数据
function travel_edit() {
	$.ajax({
		 url : prefix + '/edit_ajax/' + $("#travelId").val(),
		type : "get",
		data : {
			'travelId' : $("#travelId").val(),
		},
		success : function(data) {
			result = data.travel;
			// 企业客户编号--下拉框
			 loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);
			// 业务编号--下拉框
			 loadCrmDataValue("/sales/business/listDic","businessId",result.businessId);
			// 项目编号--下拉框
			 loadCrmDataValue("/sales/salesProject/listAllDic","projectId",result.projectId);
			// 出差人姓名--下拉框
			 loadCrmDataValue("/inner/innerOrgEmployee/listDic","travelName",result.travelName);
			// 出发地--文本框
			 $("input[name='travelPlaceIssue']").val(result.travelPlaceIssue);
			// 目的地--文本框
			 $("input[name='travelPlaceEnded']").val(result.travelPlaceEnded);
			// 同行人--下拉框
			 loadCrmDataValue("/inner/innerOrgEmployee/listDic","travelPartner",result.travelPartner);
			// 拟出差时间 --文本框
			 $("input[name='travelDepartureDate']").val(result.travelDepartureDate);
			// 拟返程时间--文本框
			 $("input[name='travelReturnDate']").val(result.travelReturnDate);
			// 预计费用类别--下拉框
			 loadDicValue("Travel_Plan_Cost_Type","travelPlanCostType",result.travelPlanCostType);
			// 预计交通方式--下拉框
			 loadDicValue("sales_customer_visit_Mode","travelVisitMode",result.travelVisitMode);
			//计划任务信息--文半框
			 $("input[name='travelPlanTask']").val(result.travelPlanTask);
			 //实际完成结果--单选按钮
			 $(":radio[name='travelActualPerformance'][value='" + result.travelActualPerformance + "']").prop("checked", "checked");
			 //未完成原因--文本框
			 $("input[name='travelUncompletedCause']").val(result.travelUncompletedCause);
			 //出差任务确认--单选按钮
			 $(":radio[name='travelTaskConfirm'][value='" + result.travelTaskConfirm + "']").prop("checked", "checked");
			 //审批状态--单选按钮
			 $(":radio[name='travelApprovalStatus'][value='" + result.travelApprovalStatus + "']").prop("checked", "checked");
		}
	});
}
