var prefix = "/sales/customerContact"
var address = null;
var result = null;
$().ready(
		function() {

			$('#contactBirthDay').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment.locale('zh-cn')
			});
			$('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e) {
				$('#lastBtn').attr("disabled", true);
				$('#nextBtn').attr("disabled", false);
			});
			$('#myTab a[href="#linkInfo"]').on('shown.bs.tab', function(e) {
				$('#lastBtn').attr("disabled", false);
				$('#nextBtn').attr("disabled", false);
			});
			$('#myTab a[href="#workUnit"]').on(
					'shown.bs.tab',
					function(e) {
						$('#lastBtn').attr("disabled", false);
						$('#nextBtn').attr("disabled", false);
						loadCrmDataValue("/sales/companyCustomer/listDic","customerId", result.customerId);// 客户编号
						loadCrmDataValue("/sales/customerDept/listDic", "contactDept",result.contactDept);// 部门
						 loadCrmDataValue("/sales/customerJob/listDic", "contactJob",result.contactJob);// 岗位
//						if (address == null) {
//							address = new addressResolve({
//								proId : 'province',
//								cityId : 'city',
//								areaId : 'area',
//								customerId : 'customerId'
//							}, {
//								proId : result.province,
//								cityId : 'city',
//								areaId : 'area',
//								customerId : 'customerId'
//							});
//							address.init();
//						}
						// loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);
					});
			$('#myTab a[href="#userDefine"]').on('shown.bs.tab', function(e) {
				$('#lastBtn').attr("disabled", false);
				$('#nextBtn').attr("disabled", true);
			});

			customerContactMapper_edit();
			getMainAndCopyPerson_ajax();
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
// 修改——显示数据绑定
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
function nextStepThis(tabId, totalStep, lastBtn, nextBtn) {
	nextStep(tabId, totalStep, lastBtn, nextBtn);
	if (address == null) {
		if ($('#' + tabId + ' li:eq(2)').attr("class") == 'active') {
			address = new addressResolve({
				proId : 'province',
				cityId : 'city',
				areaId : 'area'
			});
			address.init();
		}
	}

}
