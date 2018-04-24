
var prefixCompanyCustomer="/sales/companyCustomer";
var address = null;
var result = null;
$().ready(function() {
			$('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e) {
				if (address == null) {
					address = new addressResolve({
						proId : 'province',
						cityId : 'city',
						areaId : 'area'
					}, {
						proId : result.province,
						cityId : 'city',
						areaId : 'arae'
					});
					address.init();
				}

			});
		
	$('#myTab a[href="#Hotspot"]').on('shown.bs.tab', function(e){
		 if($("#customerHotRank option").length==0){
			
			 loadDicValue("sales_customer_hot_Rank","customerHotRank",result.customerHotRank);//热度s
		 }
		 if($("#customerHotClassif option").length == 0){
			 loadDicValue("sales_Customer_Hot_Classif","customerHotClassif",result.customerHotClassif);//热点客户分类  
			}
	    
	 });
	$('#myTab a[href="#linkInfo"]').on('shown.bs.tab', function(e){
		
		 if($("#customerContactSta option").length==0){
			 loadDicValue("sales_customer_contact_Sta","customerContactSta",result.customerContactSta);
		 }
		
	    
	 });
	$('#myTab a[href="#Gegner"]').on('shown.bs.tab', function(e) {
//		$('#lastBtn').attr("disabled",false);
//		$('#nextBtn').attr("disabled",false);
		loadCompetitor();
	});
	$('#myTab a[href="#Organisation"]').on('shown.bs.tab', function(e) {
		loadChild();
		loadJob();
		loadDept();
	});

	

//	layui.use('upload', function () {
//        var upload = layui.upload;
//        //执行实例
//        var uploadInst = upload.render({
//            elem: '#test1', //绑定元素
//            url: '/common/sysFile/upload', //上传接口
//            size: 1000,
//            accept: 'file',
//            done: function (r) {
//            	//alert(r.fileName);
//            	$("#serviceAttachment").val(r.fileName);
//                //layer.msg(r.msg);
//                //app.getData();
//            },
//            error: function (r) {
//                layer.msg(r.msg);
//            }
//        });
//    });
	companyCustomer_edit();
	if (address == null) {
		address = new addressResolve({
			proId : 'province',
			cityId : 'city',
			areaId : 'area'
		});
		address.init();
	}
});



$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

//function update() {
//	$.ajax({
//		cache : true,
//		type : "POST",
//		url : "/sales/companyCustomer/update",
//		data : $('#signupForm').serialize(),// 你的formid
//		async : false,
//		error : function(request) {
//			parent.layer.alert("Connection error");
//		},
//		success : function(data) {
//			if (data.code == 0) {
//				parent.layer.msg("操作成功");
//				parent.reLoad();
//				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
//				parent.layer.close(index);
//
//			} else {
//				parent.layer.alert(data.msg)
//			}
//
//		}
//	});
//
//}



//修改--现实绑定数据
function companyCustomer_edit(){
	$.ajax({
		 url : prefixCompanyCustomer+'/edit_ajax/' + $("#customerId").val(),
		type : "get",
		data : {
			'customerId' : $("#customerId").val(),
		},
		success:function(data){
			result=data.companyCustomer;
			
			loadDicValue("sales_customer_category","customerCategory",result.customerCategory);//客户类别
			loadDicValue("sales_customer_product","customerProduct",result.customerProduct);//产品需求
			loadDicValue("sales_customer_character","customerCharacter",result.customerCharacter);//企业性质
			loadDicValue("sales_customer_status","customerStatus",result.customerStatus);//客户状态
			loadDicValue("sales_customer_level","customerLevel",result.customerLevel);//客户级别
			loadDicValue("sales_customer_sale_Phase","customerSalePhase",result.customerSalePhase);//销售阶段
			loadDicValue("sales_customer_inner_Phase","customerInnerPhase",result.customerInnerPhase);//客户内部阶段
			loadDicValue("sales_customer_sourcee","customerSource",result.customerSource);//来源
			loadDicValue("sales_customer_credit_Rank","customerCreditRank",result.customerCreditRank);//信用等级
			loadDicValue("sales_customer_potential","customerPotential",result.customerPotential);//客户潜力
			loadDicValue("sales_customer_Province","customerProvince",result.province);//省
			loadDicValue("sales_customer_City","customerCity",result.city);//市
			loadDicValue("sales_customer_County","customerCounty",result.area);//区
			loadDicValue("sales_customer_visit_Mode","customerVisitMode",result.customerVisitMode);//拜访交通方式
			loadCrmDataValue("/inner/innerOrgEmployee/listDic", "customerOwner",result.customerOwner);//客户所有者
			loadCrmDataValue("/inner/innerOrgEmployee/listDic", "customerSales",result.customerSales);//销售负责人
			$("input[name='customerHotClassif']").val(result.customerHotClassif);//热点客户分类
			$("input[name='customerHotRank']").val(result.customerHotRank);//热度
			
			
			$("input[name='customerId']").val(result.customerId);//
			
			$("input[name='customerName']").val(result.customerName);//业务名称//
			
			$("input[name='customerSimpleName']").val(result.customerSimpleName);//助记简称//
			
			$("input[name='customerEmpNumber']").val(result.customerEmpNumber);//员工数量//
			
			$("input[name='customerParent']").val(result.customerParent);//上级单位//
			
			$("textarea[name='customerReqDes']").val(result.customerReqDes);//需求简要描述//
			
			$("input[name='customerOldId']").val(result.customerOldId);//旧客户编号//
			
			$("input[name='customerIndustry']").val(result.customerIndustry);//行业//
			
			$("textarea[name='customerIntroduction']").val(result.customerIntroduction);//公司简介//
			
			$("textarea[name='customerRemarks']").val(result.customerRemarks);//备注//
			
			$("input[name='customerStaffSize']").val(result.customerStaffSize);//人员规模//
			
			$("select[name='customerOwner']").val(result.customerOwner);//客户所有者
			$("select[name='customerOwner']").trigger("chosen:updated");
			
			$("select[name='customerSales']").val(result.customerSales);//销售负责人
			$("select[name='customerSales']").trigger("chosen:updated");

			$("select[name='test1']").val(result.test1);//附件
			$("select[name='test1']").trigger("chosen:updated");
			
			//热点情况
			$(":radio[name='customerHot'][value='" + result.customerHot + "']").prop("checked", "checked");//热点客户
			
			$("input[name='customerVolume']").val(result.customerVolume);//预期成交金额
			
			$("input[name='customerHotDesc']").val(result.customerHotDesc);//热点说明
		
			
			
			//开票信息
			$("input[name='customerInvoiceName']").val(result.customerInvoiceName);//开票名称
			
			$("input[name='customerTaxNumber']").val(result.customerTaxNumber);//纳税人账号
			
			$("input[name='customerBank']").val(result.customerBank);//开户行
			
			$("input[name='customerAccountNum']").val(result.customerAccountNum);//账号
			
			//联系方式
			$("input[name='customerFax']").val(result.customerFax);//传真
			
			$("input[name='customerPostcode']").val(result.customerPostcode);//邮编
			
			$("input[name='customerUrl']").val(result.customerUrl);//官网地址
			
			$("input[name='customerMailbox']").val(result.customerMailbox);//企业邮箱
			
			$("input[name='customerLeader']").val(result.customerLeader);//企业负责人
			
			$("input[name='customerJobTitle']").val(result.customerJobTitle);//职务
		
			$("input[name='customerPhoneNum']").val(result.customerPhoneNum);//电话号码
			
			$("input[name='customerAddress']").val(result.customerAddress);//企业办公地址
			
			

			
			//生产信息
			$("input[name='customerPaymentRate']").val(result.customerPaymentRate);//收费收缴率
			
			$("input[name='customerHeatingShare']").val(result.customerHeatingShare);//企业占全市热化率
			
			$("input[name='customerComplaintRate']").val(result.customerComplaintRate);//投诉率
			
			$("input[name='customerHeatingArea']").val(result.customerHeatingArea);//供热面积
			
			$("input[name='customerHeatingSourceNumber']").val(result.customerHeatingSourceNumber);//热力站数量
			
			$("input[name='customerSteamArea']").val(result.customerSteamArea);//蒸汽面积
			
			$("input[name='customerHotWaterArea']").val(result.customerHotWaterArea);//热水面积
			
			$("input[name='customerOwnHeatingSource']").val(result.customerOwnHeatingSource);//自有热源
			
			$("input[name='customerOutHeatingSource']").val(result.customerOutHeatingSource);//外购热源
			
			//能耗
			$("input[name='customerHeatLoss']").val(result.customerHeatLoss);//热耗
			
			$("input[name='customerWaterLoss']").val(result.customerWaterLoss);//水耗
			
			$("input[name='customerElectrickLoss']").val(result.customerElectrickLoss);//电耗
			
			//未来规划
			$("textarea[name='customerPlanOneYear']").val(result.customerPlanOneYear);//未来一年新增规划
			
			$("textarea[name='customerPlanTowYear']").val(result.customerPlanTowYear);//未来两年新增规划
			
			$("textarea[name='customerPlanThreeYear']").val(result.customerPlanThreeYear);//未来三年新增规划
			
			//自定义字段
			$("input[name='opponentProjectType']").val(result.opponentProjectType);//字段信息
			
			$("input[name='opponentProductType']").val(result.opponentProductType);//产品名称
			
			$("input[name='opponentProductPrice']").val(result.opponentProductPrice);//价格
			
			$("input[name='customerRemarks']").val(result.customerRemarks);//备注	

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


















