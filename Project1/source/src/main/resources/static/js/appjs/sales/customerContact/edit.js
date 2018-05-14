var prefix = "/sales/customerContact"
var address = null;
var result = null;
$().ready(function() {

	 $('#contactBirthDay').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    });
	 $('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",true);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#linkInfo"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#workUnit"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);//客户编号
		 loadCrmDataValue("/sales/customerDept/listDic", "contactDept",result.contactDept);// 部门
		 loadCrmDataValue("/sales/customerJob/listDic", "contactJob",result.contactJob);// 岗位
//		 if(address==null){
//			 address = new addressResolve({
//				    proId: 'province',
//				    cityId: 'city',
//				    areaId: 'area',
//				    customerId: 'customerId'
//				  },{
//					    proId: result.province,
//					    cityId: 'city',
//					    areaId: 'area',
//					    customerId: 'customerId'
//					  });
//			 address.init();
//		 }
		// loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);
	 });
	 $('#myTab a[href="#userDefine"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",true);
	 });

	customerContactMapper_edit();
	validateRule();
	getMainAndCopyPerson_ajax();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#contactId").val() +"/sales_customer_contact";
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
		url : "/sales/customerContact/update",
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
			contactName : {
				required : true
			},
			customerId : {
				required : true
			},
			province : {
				required : true
			},
			contactYearsWorking : {
				number : true,
				max : 50
			},
			contactAge : {
				digits : true,
				max : 100
			},
			contactPhoneNumber : {
				required : true,
				digits : true,
				rangelength : [ 5, 11 ]
			},
			// 工作电话
			contactWorkPhoneNumber : {
				digits : true,
				rangelength : [ 5, 11 ]
			},
			// 家庭电话
			contactFamilyPhoneNumber : {
				digits : true,
				rangelength : [ 5, 11 ]
			},
			// 微信
			// contactWeixin:{
			//
			// },
			// QQ
			contactQq : {
				digits : true
			},
			// 邮件地址
			contactMailbox : {
				email : true
			}

		},
		messages : {
			contactName : {
				required : icon + "请输入姓名"
			},
			customerId : {
				required : icon + "请填入企业客户名称！"
			},
			province : {
				required : icon + "请选择行政区"
			},
			contactYearsWorking : {
				number : icon + "请输入正确的工作年限（数字）！",
				max : $.validator.format("请输入正确的工作年限不大于50年！")
			},
			contactAge : {
				digits : icon + "年龄必须为数字、整数！",
				max : $.validator.format("请输入年龄不大于100岁！")
			},
			contactPhoneNumber : {
				required : icon + "请输入手机",
				digits : icon + "请输入正确的电话号码（数字）！",
				rangelength : icon + "请输入有效的电话号码、5-11位！！"
			},
			// 工作电话
			contactWorkPhoneNumber : {
				digits : icon + "请输入正确的电话号码（数字）！",
				rangelength : icon + "请输入有效的电话号码、5-11位！！"
			},
			// 家庭电话
			contactFamilyPhoneNumber : {
				digits : icon + "请输入正确的电话号码（数字）！",
				rangelength : icon + "请输入有效的电话号码、5-11位！"
			},

			// 微信
			// contactWeixin:{
			//
			// },
			// QQ
			contactQq : {
				digits : icon + "QQ必须为数字、整数！",
			},
			// 邮件地址
			contactMailbox : {
				email : icon + "请输入有效的邮件地址！"
			}
		}
	})
}
//修改——显示数据绑定
function customerContactMapper_edit() {
	$.ajax({
		url : prefix + '/edit_ajax/' + $("#contactId").val(),
		type : "get",
		data : {
			'contactId' : $("#contactId").val(),
		},
		success : function(data) {
			result = data.customerContact;
			$("input[name='contactName']").val(result.contactName);
			$("input[name='contactSalutation']").val(result.contactSalutation);
			$(":radio[name='contactSex'][value='" + result.contactSex + "']").prop("checked", "checked");
			$(":radio[name='contactMaritalStatus'][value='"+ result.contactMaritalStatus + "']").prop("checked", "checked");
			$("input[name='contactBirthDay']").val(result.contactBirthDay);
			$("input[name='contactAge']").val(result.contactAge);
			$("input[name='contactFamilyStatus']").val(
					result.contactFamilyStatus);
			$("input[name='contactGraduateInstitutions']").val(
					result.contactGraduateInstitutions);
			$("input[name='contactSkill']").val(result.contactSkill);
			$("input[name='contactYearsWorking']").val(
					result.contactYearsWorking);
			$("textarea[name='contactExperience']").val(result.contactExperience);
			$("input[name='contactPreviousCompany']").val(
					result.contactPreviousCompany);
			$("input[name='contactSuperiors']").val(result.contactSuperiors);
			$("input[name='contactIntroduction']").val(
					result.contactIntroduction);
			$("input[name='contactResponsibility']").val(
					result.contactResponsibility);
			$("input[name='contactMailbox']").val(result.contactMailbox);
			$("input[name='contactWorkPhoneNumber']").val(
					result.contactWorkPhoneNumber);
			$("input[name='contactFamilyPhoneNumber']").val(
					result.contactFamilyPhoneNumber);
			$("input[name='contactFax']").val(result.contactFax);
			$("input[name='contactFamilyAddress']").val(
					result.contactFamilyAddress);
			$("input[name='contactWeixin']").val(result.contactWeixin);
			$("input[name='contactQq']").val(result.contactQq);
			$("input[name='contactPhoneNumber']")
					.val(result.contactPhoneNumber);
			loadDicValue("sales_customer_contact_Sta", "contactIntroduction",
					result.contactIntroduction);
			// 部门
			$("input[name='contactDept']").val(result.contactDept);
			// 岗位
			$("input[name='contactJob']").val(result.contactJob);
			// 职务
			$("input[name='contactTitle']").val(result.contactTitle);
			// 角色
			$("input[name='contactRole']").val(result.contactRole);
			// 负责业务
			$("input[name='contactResponsibility']").val(
					result.contactResponsibility);
			// 纪念日类别
			$("input[name='contactSpecialDayCategory']").val(
					result.contactSpecialDayCategory);
			// 纪念日
			$("input[name='contactSpecialDay']").val(result.contactSpecialDay);
			// 爱好
			$("input[name='contactInterest']").val(result.contactInterest);
			// 单位地址
			// $("input[name='contactRemarks']").val(result.contactRemarks);
			// 备注
			$("textarea[name='contactRemarks']").val(result.contactRemarks);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic", "contactOwner",
					result.contactOwner);// 客户所有者
			loadDicValue("sales_Customer_Contact_Status", "contactStatus",
					result.contactStatus);// 联系人状态
			loadCrmDataValue("/inner/innerOrgEmployee/listDic", "contactSales",
					result.contactSales);// 销售负责人
			// loadDic("sales_customer_contact_Sta","contactIntroduction");
		}
	});
}
function nextStepThis(tabId,totalStep,lastBtn,nextBtn){
	nextStep(tabId,totalStep,lastBtn,nextBtn);
	if(address ==null ){
			if( $('#'+tabId+' li:eq(2)').attr("class")=='active'){
				address = new addressResolve({
				    proId: 'province',
				    cityId: 'city',
				    areaId: 'area'
				  });
				address.init();
			}

	}

}
