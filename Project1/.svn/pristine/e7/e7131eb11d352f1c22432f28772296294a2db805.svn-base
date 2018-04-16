var competitorPrefix = "/sales/competitor"
$().ready(function() {

	competitor_edit()
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
		url : "/sales/competitor/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoadCompetitor();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
//修改--绑定数据
function competitor_edit() {
	$.ajax({
		 url : competitorPrefix + '/edit_ajax/' + $("#complaintId").val(),
		type : "get",
		data : {
			'complaintId' : $("#complaintId").val(),
		},
		success : function(data) {
			result = data.competitor;
			
			
			
			 // 企业客户编号
			 $("input[name='customerId']").val(result.customerId);
			 //公司名称
			 $("input[name='complaintCompanyName']").val(result.complaintCompanyName);
			
			//项目类型complaintProjectType
			 loadDicValue("sales_project_gategory","complaintProjectType",result.complaintProjectType);
			 //产品分类complaintProductCategory
			 loadDicValue("sales_customer_product","complaintProductCategory",result.complaintProductCategory);
			 //产品名称 
			 $("input[name='complaintProductName']").val(result.complaintProductName);
			 //产品价格
			 $("input[name='complaintProductPrice']").val(result.complaintProductPrice);
			 //备注 
			 $("textarea[name='complaintRemarks']").val(result.complaintRemarks);
			 //产品描述
			 $("input[name='complaintProductDescription']").val(result.complaintProductDescription);
		}
	});
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
	ignore: ":hidden:not(select)",
		rules : {
			//产品价格
			complaintProductPrice : {
				digits : true
			}
		},
		messages : {
			complaintProductPrice : {
				digits : icon + "产品价格必须为数字！"
			}
		}
	})
}