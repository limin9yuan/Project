var prefix = "/sales/companyCustomer"

$.()ready(function(){
	$('#myTab a[href="#Hotspot"]').on('shown.bs.tab', function(e){
		 if($("#customerHotRank option").length==0){
			
			 loadDicValue("sales_customer_hot_Rank","customerHotRank",result.customerHotRank);//热度s
		 }
	    
	 });
	validateRule();
	companyCustomer_edit();
})	;
	

//验证
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
	ignore: ":hidden:not(select)",
		rules : {
			customerName : {
				required : true
			},
			customerSimpleName : {
				required : true
			},
			customerProduct : {
				required : true
			},
			customerCategory : {
				required : true
			},
			customerCharacter : {
				required : true
			},
			customerStatus : {
				required : true
			},
			customerLevel : {
				required : true
			},
			customerSalePhase : {
				required : true
			},
			customerInnerPhase : {
				required : true
			},
			customerParent : {
				required : true
			},
			customerVolume : {
				digits:true,
				required : true
			},
			customerHotDesc : {
				required : true
			},
			// 联系方式
			customerAddress:{
				required:true
			},
			customerPhoneNum:{
				required:true,
				digits:true,
				rangelength:[5,11]
			},
			customerLeader:{
				required:true
			},
//			生产信息
			customerPaymentRate:{
				required:true,
				digits:true,
				range:[0,100]
			},
			customerHeatingShare:{
				required:true,
				digits:true,
				range:[0,100]
			},
			customerComplaintRate:{
				required:true,
				digits:true,
				range:[0,100]
			},
			customerHeatingArea:{
				max:99999999999999
			},
			customerHeatingSourceNumber:{
				max:99999999
			},
			customerSteamArea:{
				max:99999999999999
			},
			customerHotWaterArea:{
				max:99999999999999
			},
			customerOwnHeatingSource:{
				max:99999999
			},
			customerOutHeatingSource:{
				max:99999999
			},
			customerEmpNumber:{
				max:9999999,
				digits:true
			}
		},
		messages : {
			customerName : {
				required : icon + "请输入企业名称！"
			},
			customerSimpleName : {
				required : icon + "请输入助记简称！"
			},
			customerProduct : {
				required : icon + "请输入产品需求！"
			},
			customerCategory : {
				required : icon + "请输入客户类别！"
			},
			customerCharacter : {
				required : icon + "请输入企业性质！"
			},
			customerStatus : {
				required : icon + "请输入客户状态！"
			},
			customerLevel : {
				required : icon + "请输入客户级别！"
			},
			customerSalePhase : {
				required : icon + "请输入销售阶段！"
			},
			customerInnerPhase : {
				required : icon + "请输入客户内部阶段！"
			},
			customerParent : {
				required : icon + "请输入上级单位！"
			},			
			customerVolume : {
				required : icon + "与其成交金额不能为空！",
				digits:icon+"请输入有效的数字！"
			},
			customerHotDesc : {
				required : icon + "热点说明不能为空！"
			},
			customerAddress:{
				required:icon+"企业地址不能为空！"
			},
			customerPhoneNum:{
				required:icon +"电话号码不能为空！",
				digits:icon +"请输入数字！",
				rangelength:icon +"请输入有效的电话号码！"
			},
			customerLeader:{
				required:icon +"企业负责人不能为空！"
			},
//			生产信息
			customerPaymentRate:{
				required:icon +"收费收缴率不能为空！",
				digits:icon +"收费收缴率必须为数字！",
				range:icon +"收费收缴率只能在0-100之间！"
			},
			customerHeatingShare:{
				required:icon +"企业占全市热化率不能为空！",
				digits:icon +"企业占全市热化率必须为数字！",
				range:icon +"企业占全市热化率只能在0-100之间！"
			},
			customerComplaintRate:{
				required:icon +"投诉率不能为空！",
				digits:icon +"投诉率必须为数字！",
				range:icon +"投诉率只能在0-100之间！"
			},
			customerEmpNumber:{
				max:icon+"员工数量不能大于7位数（9999999）！",
				digits:icon+"员工数量必须为数字！"
			},
			customerHeatingArea:{
				max:$.validator.format("请输入不大于 99999999999999在14位之间的数值")
			},
			customerHeatingSourceNumber:{
				max:$.validator.format("请输入不大于 999999999 在14位之间的数值")
			},
			customerSteamArea:{
				max:$.validator.format("请输入不大于 99999999999999 在14位之间的数值")
			},
			customerHotWaterArea:{
				max:$.validator.format("请输入不大于 99999999999999 在14位之间的数值")
			},
			customerOwnHeatingSource:{
				max:$.validator.format("请输入不大于 999999999 在14位之间的数值")
			},
			customerOutHeatingSource:{
				max:$.validator.format("请输入不大于 999999999 在14位之间的数值")
			}
		}
	})
}   

//查看--实现绑定数据
function companyCustomer_edit(){
	$.ajax({
		 url : prefix+'/edit_ajax/' + $("#customerId").val(),
		type : "get",
		data : {
			'customerId' : $("#customerId").val(),
		},
		success:function(data){
			result=data.companyCustomer;
			$("input[name='customerId']").val(result.customerId);//
			$("input[name='customerName']").val(result.customerName);//企业务名称//
			//热点情况
			$(":radio[name='customerHot'][value='" + result.customerHot + "']").prop("checked", "checked");//热点客户
			$("input[name='customerVolume']").val(result.customerVolume);//预期成交金额
//			loadDicValue("sales_customer_hot_Rank","customerHotRank",result.customerHotRank);//热度
		}
	});
}
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/companyCustomer/update",
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

