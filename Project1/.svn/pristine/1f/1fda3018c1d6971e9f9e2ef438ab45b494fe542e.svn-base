var prefix = "/sales/customerContact"
var address = null;
var result = null;
$().ready(function() {
	
	 $('#birthDay').datetimepicker({  
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
		 if(address==null){
			 address = new addressResolve({
				    proId: 'province',
				    cityId: 'city',
				    areaId: 'area',
				    customerId: 'customerId'
				  },{
					    proId: result.province,
					    cityId: 'city',
					    areaId: 'area',
					    customerId: 'customerId'
					  });
			 address.init(); 
		 }
		// loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);
	 });
	 $('#myTab a[href="#userDefine"]').on('shown.bs.tab', function(e){		
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",true);	    
	 });
	 
	customerContactMapper_edit();
	validateRule();
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
		url : "/sales/customerContact/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				//parent.reLoad();
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
		rules : {
			contactName : {
				required : true
			},
			contactPhoneNumber : {
				required : true
			}
		},
		messages : {
			contactName : {
				required : icon + "请输入姓名"
			},
			contactPhoneNumber : {
				required : icon + "请输入手机"
			}
		}
	})
}
//修改——显示数据绑定
function customerContactMapper_edit(){
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
			$(":radio[name='contactMaritalStatus'][value='" + result.contactMaritalStatus + "']").prop("checked", "checked");
			$("input[name='birthDay']").val(result.birthDay);
			$("input[name='contactAge']").val(result.contactAge);
			$("input[name='contactFamilyStatus']").val(result.contactFamilyStatus);
			$("input[name='contactGraduateInstitutions']").val(result.contactGraduateInstitutions);
			$("input[name='contactSkill']").val(result.contactSkill);
			$("input[name='contactYearsWorking']").val(result.contactYearsWorking);
			$("input[name='contactExperience']").val(result.contactExperience);
			$("input[name='contactPreviousCompany']").val(result.contactPreviousCompany);
			$("input[name='contactSuperiors']").val(result.contactSuperiors);
			$("input[name='contactStatus']").val(result.contactStatus);
			$("input[name='contactIntroduction']").val(result.contactIntroduction);
			$("input[name='contactOwner']").val(result.contactOwner);
			$("input[name='contactSales']").val(result.contactSales);
			$("input[name='contactResponsibility']").val(result.contactResponsibility);
			$("input[name='contactMailbox']").val(result.contactMailbox);
			$("input[name='contactWorkPhoneNumber']").val(result.contactWorkPhoneNumber);
			$("input[name='contactFamilyPhoneNumber']").val(result.contactFamilyPhoneNumber);
			$("input[name='contactFax']").val(result.contactFax);
			$("input[name='contactFamilyAddress']").val(result.contactFamilyAddress);
			$("input[name='contactWeixin']").val(result.contactWeixin);
			$("input[name='contactQq']").val(result.contactQq);
			$("input[name='contactPhoneNumber']").val(result.contactPhoneNumber);
			loadDicValue("sales_customer_contact_Sta","contactIntroduction",result.contactIntroduction);
			//loadDic("sales_customer_contact_Sta","contactIntroduction");
		}
	});
}
/*
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	/*
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
		rules : {
			contactName : {
				required : true
			},
			contactPhoneNumber : {
				required : true
			},
			province : {
				required : true
			},
			contactAge: {
				digits:true
			}
		},
		messages : {
			contactName : {
				required : icon + "请输入姓名"
			},
			contactPhoneNumber : {
				required : icon + "请输入手机"
			},
			province : {
				required : icon + "请选择行政区"
			},
			contactAge: {
				digits: icon + "请输入数字"
			}
		
	})}
}*/