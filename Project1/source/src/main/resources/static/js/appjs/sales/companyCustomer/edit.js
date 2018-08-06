var prefixCompanyCustomer="/sales/companyCustomer";
var address = null;
var result = null;
$().ready(function() {
//			$('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e) {
//				if (address == null) {
//					address = new addressResolve({
//						proId : 'province',
//						cityId : 'city',
//						areaId : 'area'
//					}, {
//						proId : result.province,
//						cityId : 'city',
//						areaId : 'arae'
//					});
//					address.init();
//				}
//
//			});

	$('#myTab a[href="#Hotspot"]').on('shown.bs.tab', function(e){

			 loadDicValue("sales_customer_hot_Rank","customerHotRank",result.customerHotRank);//热度s

			 loadDicValue("sales_Customer_Hot_Classif","customerHotClassif",result.customerHotClassif);//热点客户分类
	 });
	$('#myTab a[href="#linkInfo"]').on('shown.bs.tab', function(e){

			 loadDicValue("sales_customer_contact_Sta","customerContactSta",result.customerContactSta);


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


	layui.use('upload', function () {
		var $ = layui.jquery;
        var upload = layui.upload;
      // 多文件列表示例
  	  var demoListView = $('#demoList');
        var uploadinst = upload.render({
            elem: '#test1', //绑定元素
            url: '/sales/companyCustomer/upload', //上传接口
            size: 1000,
            accept: 'file',
            multiple: true,
            auto: false,			//不自动上传设置
            bindAction: '#uploadFile',	//“上传”按钮的ID
            multiple: false,
            choose:function(obj){
//******************************预览选择的文件并根据后缀名判断显示不同的图片********************************************           	
            	var files = this.files = obj.pushFile(); // 将每次选择的文件追加到文件队列
      	      // 读取本地文件
      	      obj.preview(function(index, file, result){
      	        var tr = $(['<tr id="upload-'+ index +'">'
      	          ,'<td style="text-align: center;">'+file.name+'</td>'
      	          ,'<td style="text-align: center;">'+ (file.size/1014).toFixed(1) +'kb</td>'
      	          ,'<td id="zhuangtai" style="text-align: center;">等待上传</td>'
      	          ,'<td style="text-align: center;">'
      	            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
      	            ,'<button  id="delete" class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
      	          ,'</td>'
      	        ,'</tr>'].join(''));
      	        
      	        // 单个重传
      	        tr.find('.demo-reload').on('click', function(){
      	          obj.upload(index, file);
      	        });
      	        
      	        // 删除
      	        tr.find('.demo-delete').on('click', function(){
      	          delete files[index]; // 删除对应的文件
      	          tr.remove();
      	          uploadListIns.config.elem.next()[0].value = ''; // 清空 input
																	// file
																	// 值，以免删除后出现同名文件不可选
      	        });
      	        
      	        demoListView.append(tr);
      	      });
              },
            done: function (r) {
           if (r.code == 0) {
//        	   alert(r.customerAttachment)
 				if (r.customerAttachment > 0) {
 					$('#ids').val(r.customerAttachment);
 					$('#customerAttachment').val(r.customerAttachment+','+document.getElementById("customerAttachment").value);
 				}
// 				parent.layer.msg(r.msg);
//				 app.getData();

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
	companyCustomer_edit();
	getMainAndCopyPerson_ajax();
	loadField();
});
function loadField() {
	$('#listCustomField')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/sales/companyCustomer/listField", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						showRefresh:true,					// 显示刷新按钮
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								t_id: $('#customerId').val()
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="editField(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="removeField(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								},
																{
									field : 'fieldName',
									title : '新建字段名称'
								},
																{
									field : 'belongCategory',
									title : '所属分类'
								},
																{
									field : 'content',
									title : '内容'
								}]
					});
}
function addField() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '95%' ],
		content : '/sales/companyCustomer/addField'
	});
}
function editField(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '95%' ],
		content : '/sales/companyCustomer/editField/' + id // iframe的url
	});
}
function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#customerId").val() + "/sales_company_customer";
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
								">" +result[i].person +"<div style='float:right;margin-top:-8px;margin-right:5px'><img id=" +(result[i].employeeId + "_1") +" onclick='deleteMainPerson(\"" +  (result[i].employeeId + "_1") +"\" )' src='../../../img/close.png'></div>"+"</div>";
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
								">" +result[i].person +"<div style='float:right;margin-top:-8px;margin-right:5px'><img id=" +(result[i].employeeId + "_2") +" onclick='deleteMainPerson(\"" +  (result[i].employeeId + "_2") +"\" )' src='../../../img/close.png'></div>"+"</div>";
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


$.validator.setDefaults({
	submitHandler : function() {
		document.getElementById("uploadFile").click();
		setTimeout('removeaa()', 200);
		setTimeout('update()', 500);//延迟执行updte()方法2毫秒
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
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				closeParenWindow();
			} else {
				parent.layer.msg(data.msg)
			}

		}
	});
}

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
			customerHotClassif : {
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
			customerMailbox:{
				email:true
			},
//			生产信息
			customerPaymentRate:{
				required:true,
				number:true,
				range:[0,1]
			},
			customerHeatingShare:{
				required:true,
				number:true,
				range:[0,1]
			},
			customerComplaintRate:{
				required:true,
				number:true,
				range:[0,1]
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
				digits:true,
				max:9999999
			},
			customerStaffSize:{
				digits:true,
				max:999999
			},
			customerHeatLoss:{
				number:true
			},
			customerWaterLoss:{
				number:true
			},
			customerElectrickLoss:{
				number:true
			},
			customerPostcode:{
				isZipCode:true
			},
			customerUrl:{
				url:true
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
			customerHotClassif : {
				required : icon + "请输入热点客户分类！"
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
			customerMailbox:{
				email:icon +"请输入正确的邮箱！"
			},
//			生产信息
			customerPaymentRate:{
				required:icon +"收费收缴率不能为空！",
				number:icon +"收费收缴率必须小数！",
				range:$.validator.format("请输入0到1之间的数值！")
			},
			customerHeatingShare:{
				required:icon +"企业占全市热化率不能为空！",
				number:icon +"企业占全市热率必须小数！",
				range:$.validator.format("请输入0到1之间的数值！")
			},
			customerComplaintRate:{
				required:icon +"投诉率不能为空！",
				number:icon +"投诉率必须小数！",
				range:$.validator.format("请输入0到1之间的数值！")
			},
			customerEmpNumber:{
				digits:icon+"员工数量必须为数字！",
				max:icon+"员工数量不能大于7位数（9999999）！"
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
			},
			customerStaffSize:{
				digits:icon +"人员规模必须为数字！",
				max:$.validator.format("请输入不大于999999在14位之间的数值")
			},
			customerHeatLoss:{
				number:icon +"请输入数字！"
			},
			customerWaterLoss:{
				number:icon +"请输入数字！"
			},
			customerElectrickLoss:{
				number:icon +"请输入正确的邮政编码"
			},
			customerPostcode:{
				isZipCode:icon +"请输入正确的邮政编码"
			},
			customerUrl:{
				url:icon+"请输入正确的网址"
			}
		}
	})
}


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
//			alert(result.customerContactSta)
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

			$("input[name='id']").val(result.customerAttachment);//附件ID
			$("input[name='customerAttachment']").val(result.customerAttachment);//附件ID
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

			$("textarea[name='customerHotDesc']").val(result.customerHotDesc);//热点说明



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
//图片预览
function previewImg(obj) {
    var img = new Image();  
    img.src = obj.src;
    var imgHtml = "<img src='" + obj.src + "' style='width:90% height:90%'/>";  
    //弹出层
    parent.layer.open({  
        type: 1,  
        shade: 0.8,
        offset: 'auto',
        area: [90 + '%',90+'%'],
        shadeClose:true,
        scrollbar: false,
        title: "图片预览", //不显示标题  
        content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
        cancel: function () {  
            //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });  
        }  
    }); 
}
