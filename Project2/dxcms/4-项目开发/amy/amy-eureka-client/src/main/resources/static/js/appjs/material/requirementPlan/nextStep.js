$().ready(function() {
	validateRule();
	pageInit();
	getRequirePlanDetail();
	datetimepicker();
	// initPage();
	// datetimepicker();
});
//导入
function importMaterial() {
	layer.open({
		type : 2,
		title : 'Excel导入',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '500px', '250px' ],
		content : '/requirementPlan/requirementPlan/import', // iframe的url
		btn : [ '确定', '取消' ],
		yes : function(index, layero) {
			var info="add";
			window["layui-layer-iframe" + index].clickButtonFile(info);
		}
	});
}
//导入Excel数据
function addExcel(data) {
	var dataThis = data;
	var $grid = $("#requirePlanTable");
	for (var i = 0; i < data.length; i++) {

		$grid.find('[role=row]').each(function (i) {
			var result = dataThis[i-1];
			if (result != undefined ) {
				$(this).find('input[name="requirePlanid"]').val(result.requirePlanid).attr('data-value', row.requirePlanid);
				$(this).find('input[name="materialName"]').val(result.materialName);
				$(this).find('input[name="materilaCode"]').val(result.materilaCode);
				$(this).find('input[name="specification"]').val(result.specification);
				$(this).find('input[name="materialUnitName"]').val(result.materialUnitName);
				$(this).find('input[name="materialSubArray"]').val(result.materialSubArray);
				$(this).find('input[name="requireQty"]').val(result.requireQty);
				$(this).find('input[name="purchaseQty"]').val(result.purchaseQty);
				$(this).find('input[name="stockQty"]').val(result.stockQty);
				$(this).find('input[name="reserveQty"]').val(result.reserveQty);
				$(this).find('input[name="onwayQty"]').val(result.onwayQty);
				$(this).find('input[name="budgetQty"]').val(result.budgetQty);
				$(this).find('input[name="referencePrice"]').val(result.referencePrice);
				$(this).find('input[name="budgetPrice"]').val(result.budgetPrice);
				var referenceAmount = $(this).find('input[name="referencePrice"]').val()
									* $(this).find('input[name="purchaseQty"]').val();
				$(this).find('input[name="referenceAmount"]').val(referenceAmount);
				$(this).find('input[name="requireDate"]').val(result.requireDate.substr(0,10));
				$(this).find('input[name="arriveDate"]').val(result.arriveDate.substr(0,10));
				$(this).find('input[name="purchaserName"]').val(result.purchaserName);
				$(this).find('input[name="description"]').val(result.description);
			}
		});
	}
	calculateTotal();
}
function withdrawApproval() {
	var planNo = $("#planNo").val();//单据编号
	$.ajax({
		cache : true,
		type : "POST",
		url : "/requirementPlan/requirementPlan/withdrawApproval",
		data :  {
				 'planNo' : planNo
				 },// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				// parent.reLoad();
				// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				// parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}
function submitApproval() {
	var planNo = $("#planNo").val();//单据编号
	$.ajax({
		cache : true,
		type : "POST",
		url : "/requirementPlan/requirementPlan/submitApproval",
		data :  {
				 'planNo' : planNo
				 },// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				// parent.reLoad();
				// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				// parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}
function save() {
	var applyEntryJson = [];
    $("#requirePlanTable").find('[role=row]').each(function (i) {
        if ($(this).find('input[name="requirePlanid"]').val()) {
             applyEntryJson.push({
				 requirePlanid : $(this).find('input[name="requirePlanid"]').val(),//序号
				 materialName : $(this).find('input[name="materialName"]').val(),//物料名称
				 materilaCode: $(this).find('input[name="materilaCode"]').val(),//物料编码
				 specification: $(this).find('input[name="specification"]').val(),//规格型号
				 materialUnitName: $(this).find('input[name="materialUnitName"]').val(),//单位

				 materialSubArray: $(this).find('input[name="materialSubArray"]').val(),//包装物料
				 requireQty: $(this).find('input[name="requireQty"]').val(),//需求数量
				 purchaseQty: $(this).find('input[name="purchaseQty"]').val(),//采购数量

				 stockQty: $(this).find('input[name="stockQty"]').val(),//库存数量
				 reserveQty: $(this).find('input[name="reserveQty"]').val(),//安全库存
				 onwayQty: $(this).find('input[name="onwayQty"]').val(),//在途数量
				 budgetQty: $(this).find('input[name="budgetQty"]').val(),//预算数量
				 referencePrice: $(this).find('input[name="referencePrice"]').val(),//参考单价
				 budgetPrice: $(this).find('input[name="budgetPrice"]').val(),//预算金额
				 referenceAmount: $(this).find('input[name="referenceAmount"]').val(),//参考金额

				 requireDate: $(this).find('input[name="requireDate"]').val(),//需求日期
				 arriveDate: $(this).find('input[name="arriveDate"]').val(),//要求到货时间
				 purchaserName: $(this).find('input[name="purchaserName"]').val(),//采购员
				 description: $(this).find('input[name="description"]').val()//说明信息
            });
        }
    });
	$.ajax({
		cache : true,
		type : "POST",
		url : "/requirementPlan/requirementPlan/save",
		data :  {
				 'title' : $("#title").val(),
				 'planNo' : $("#planNo").val(),
				 'type' : $("#type").val(),
				 'purchaseDept' : $("#purchaseDept").val(),
				 'invoiceDate' : $("#invoiceDate").val(),
				 'authorUser' : $("#authorUser").val(),
				 'createDate' : $("#createDate").data('date'),
				 'remark' : $("#remark").val(),
				 'applyEntryJson' : JSON.stringify(applyEntryJson)
				 },// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				// parent.reLoad();
				// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				// parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function calculateTotal() {
	var sum_requireQty = 0.00;
	var sum_purchaseQty = 0.00;
	var sum_stockQty = 0.00;
	var sum_onwayQty = 0.00;
	var sum_budgetQty = 0.00;
	var sum_budgetPrice = 0.00;
	var sum_referenceAmount = 0.00;
	$("#requirePlanTable").find("tbody tr").each(function (i) {
		//需求数量
		var requireQty = $(this).find('td:eq(8)').find('input').val();
		if (requireQty != "" && requireQty != undefined) {
			sum_requireQty += Number(requireQty);
		}
		//采购数量
		var purchaseQty = $(this).find('td:eq(9)').find('input').val();
		if (purchaseQty != "" && purchaseQty != undefined) {
			sum_purchaseQty += Number(purchaseQty);
		}
		//库存数量
		var stockQty = $(this).find('td:eq(10)').find('input').val();
		if (stockQty != "" && stockQty != undefined) {
			sum_stockQty += Number(stockQty);
		}
		//在途数量
		var onwayQty = $(this).find('td:eq(12)').find('input').val();
		if (onwayQty != "" && onwayQty != undefined) {
			sum_onwayQty += Number(onwayQty);
		}
		//预算数量
		var budgetQty = $(this).find('td:eq(13)').find('input').val();
		if (budgetQty != "" && budgetQty != undefined) {
			sum_budgetQty += Number(budgetQty);
		}
		//预算金额
		var budgetPrice = $(this).find('td:eq(15)').find('input').val();
		if (budgetPrice != "" && budgetPrice != undefined) {
			sum_budgetPrice += Number(budgetPrice);
		}
		//参考金额
		var referenceAmount = $(this).find('td:eq(16)').find('input').val();
		if (referenceAmount != "" && referenceAmount != undefined) {
			sum_referenceAmount += Number(referenceAmount);
		}
	});
	$("#requireQty").text(toDecimal(sum_requireQty));
	$("#purchaseQty").text(toDecimal(sum_purchaseQty));
	$("#stockQty").text(toDecimal(sum_stockQty));
	$("#onwayQty").text(toDecimal(sum_onwayQty));
	$("#budgetQty").text(toDecimal(sum_budgetQty));
	$("#budgetPrice").text(toDecimal(sum_budgetPrice));
	$("#referenceAmount").text(toDecimal(sum_referenceAmount));
}
function getMaterialDetailByCode(code) {
	$.ajax({
		url : '/requirementPlan/requirementPlan/getMaterialDetailByCode/' + code,
		type : "get",
		data : {
			'code' : $("#materilaCode").val(),
		},
		success : function(data) {

			var orderEntry = data.getRequirePlanDetailList;
			var ids = jQuery("#requirePlanTable").jqGrid('getDataIDs');
		    //获得当前最大行号（数据编号）
			var thistr = "1";
			var newrowid = "0"
			//获得新添加行的行号（数据编号）
			if(ids.length>0){
			   thistr = ids.length+1;//新加行tr序号
			   newrowid = Math.max.apply(Math,ids)+1;
			}else{
			   thistr = "1";
			   newrowid = "0"
			}
			for (var i = 0; i < orderEntry.length; i++) {
				if ( $("#requirePlanTable").find('[role=row]').find('[data-value=' + orderEntry[i].requirePlanid + ']').length == 0) {
	                var rowdata = {
						requirePlanid : '<input name="requirePlanid" type="text" class="editable left disabled"/ readonly>',//序号
						materialName : '<input name="materialName" type="text" class="editable left disabled"/>',//物料名称
						materilaCode: '<input name="materilaCode" type="text" class="editable left disabled"/ readonly>',//物料编码
						specification: '<input name="specification" type="text" class="editable left disabled" readonly/>',//规格型号
						materialUnitName: '<input name="materialUnitName" type="text" class="editable left disabled" readonly/>',//单位

						materialSubArray: '<input name="materialSubArray" type="text" class="editable left disabled" readonly/>',//包装物料
						requireQty: '<input name="requireQty" type="text" class="editable left disabled decimal"/>',//需求数量
						purchaseQty: '<input name="purchaseQty" type="text" class="editable left disabled decimal"/>',//采购数量

						stockQty: '<input name="stockQty" type="text" class="editable left disabled decimal" readonly/>',//库存数量
						reserveQty: '<input name="reserveQty" type="text" class="editable left disabled" readonly/>',//安全库存
						onwayQty: '<input name="onwayQty" type="text" class="editable left disabled decimal" readonly/>',//在途数量
						budgetQty: '<input name="budgetQty" type="text" class="editable left disabled decimal" readonly/>',//预算数量
						referencePrice: '<input name="referencePrice" type="text" class="editable left disabled" readonly/>',//参考单价
						budgetPrice: '<input name="budgetPrice" type="text" class="editable left disabled decimal" readonly/>',//预算金额
						referenceAmount: '<input name="referenceAmount" type="text" class="editable left disabled decimal" readonly/>',//参考金额

						requireDate: '<input name="requireDate" type="text" class="editable left disabled" readonly/>',//需求日期
						arriveDate: '<input name="arriveDate" type="text" class="editable left disabled" />',//要求到货时间
						purchaserName: '<input name="purchaserName" type="text" class="editable left disabled" readonly/>',//采购员
						description: '<input name="description" type="text" class="editable left disabled" readonly/>'//说明信息
	                }
	                $("#requirePlanTable").jqGrid('addRowData', newrowid, rowdata);
					var tmpRowId = Number(newrowid) +1;
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="requirePlanid"]').val(orderEntry[i].requirePlanid).attr('data-value', orderEntry[i].requirePlanid);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="materialName"]').val(orderEntry[i].materialName);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="materilaCode"]').val(orderEntry[i].materilaCode);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="specification"]').val(orderEntry[i].specification);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="materialUnitName"]').val(orderEntry[i].materialUnitName);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="materialSubArray"]').val(orderEntry[i].materialSubArray);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="requireQty"]').val(orderEntry[i].requireQty);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="purchaseQty"]').val(orderEntry[i].purchaseQty);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="stockQty"]').val(orderEntry[i].stockQty);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="reserveQty"]').val(orderEntry[i].reserveQty);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="onwayQty"]').val(orderEntry[i].onwayQty);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="budgetQty"]').val(orderEntry[i].budgetQty);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="referencePrice"]').val(orderEntry[i].referencePrice);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="budgetPrice"]').val(orderEntry[i].budgetPrice);
					var referenceAmount = $("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="referencePrice"]').val()
										* $("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="purchaseQty"]').val();
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="referenceAmount"]').val(referenceAmount);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="requireDate"]').val(orderEntry[i].requireDate.substr(0,10));
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="arriveDate"]').val(orderEntry[i].arriveDate.substr(0,10));
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="purchaserName"]').val(orderEntry[i].purchaserName);
					$("#requirePlanTable").find("tbody tr:eq("+tmpRowId+")").find('input[name="description"]').val(orderEntry[i].description);
				}else {
	                layer.msg('该物资已存在,不能重复添加');
					return;
	            }
				newrowid = newrowid+1;
            };
			//合计
			calculateTotal();

			$("#requirePlanTable").find('.decimal').keyup(function () {
				calculateTotal();
			});
		}
	});
}
function addRow() {
	layer.open({
			type : 2,
			title : '选择物资',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '90%', '85%' ],
			content : "/requirementPlan/requirementPlan/addMore",
			btn: ['确定','取消'],
			yes: function(index, layero){
			   var res = window["layui-layer-iframe" + index].getSelectedMaterial();
			   // var row = $("#requirePlanTable").jqGrid('getRowData');
			   // for (var i = 0; i < row.length; i++) {
			   // 		alert($("#requirePlanTable").jqGrid('getRowData', i));
			   // }

			   getMaterialDetailByCode(res);
			   layer.close(index);
			},
			btn2:function(index, layero){
				layer.close(index);
		   }
		});
}
function deleteSelectedRow(){
	//获取多选到的id集合
	var ids = $("#requirePlanTable").jqGrid("getGridParam", "selarrrow");
	//遍历访问这个集合
	$(ids).each(function (index, id){
	     //由id获得对应数据行
		var row = $("#requirePlanTable").jqGrid('getRowData', id);
		$("#requirePlanTable").delRowData(id);
	});
	calculateTotal();
}
function getRequirePlanDetail(){
	$.ajax({
		url : '/requirementPlan/requirementPlan/getRequirePlanDetail',
		type : "get",
		data : {
			'code' : $("#materilaCode").val(),
		},
		success : function(data) {

			var orderEntry = data.getRequirePlanDetailList;
			for (var i = 0; i < orderEntry.length; i++) {
                var rowdata = {
					requirePlanid : '<input name="requirePlanid" type="text" class="editable left disabled"/ readonly>',//序号
					materialName : '<input name="materialName" type="text" class="editable left disabled"/>',//物料名称
					materilaCode: '<input name="materilaCode" type="text" class="editable left disabled"/ readonly>',//物料编码
					specification: '<input name="specification" type="text" class="editable left disabled" readonly/>',//规格型号
					materialUnitName: '<input name="materialUnitName" type="text" class="editable left disabled" readonly/>',//单位

					materialSubArray: '<input name="materialSubArray" type="text" class="editable left disabled" readonly/>',//包装物料
					requireQty: '<input name="requireQty" type="text" class="editable left disabled decimal"/>',//需求数量
					purchaseQty: '<input name="purchaseQty" type="text" class="editable left disabled decimal"/>',//采购数量

					stockQty: '<input name="stockQty" type="text" class="editable left disabled decimal" readonly/>',//库存数量
					reserveQty: '<input name="reserveQty" type="text" class="editable left disabled" readonly/>',//安全库存
					onwayQty: '<input name="onwayQty" type="text" class="editable left disabled decimal" readonly/>',//在途数量
					budgetQty: '<input name="budgetQty" type="text" class="editable left disabled decimal" readonly/>',//预算数量
					referencePrice: '<input name="referencePrice" type="text" class="editable left disabled" readonly/>',//参考单价
					budgetPrice: '<input name="budgetPrice" type="text" class="editable left disabled decimal" readonly/>',//预算金额
					referenceAmount: '<input name="referenceAmount" type="text" class="editable left disabled decimal" readonly/>',//参考金额

					requireDate: '<input name="requireDate" type="text" class="editable left disabled" readonly/>',//需求日期
					arriveDate: '<input name="arriveDate" type="text" class="editable left disabled" />',//要求到货时间
					purchaserName: '<input name="purchaserName" type="text" class="editable left disabled" readonly/>',//采购员
					description: '<input name="description" type="text" class="editable left disabled" readonly/>'//说明信息
                }
                $("#requirePlanTable").jqGrid('addRowData', i, rowdata);
            };
			$("#requirePlanTable").find('[role=row]').each(function (i) {
	            var row = orderEntry[i - 1];
	            if (row != undefined) {
	                $(this).find('input[name="requirePlanid"]').val(row.requirePlanid).attr('data-value', row.requirePlanid);
	                $(this).find('input[name="materialName"]').val(row.materialName);
	                $(this).find('input[name="materilaCode"]').val(row.materilaCode);
	                $(this).find('input[name="specification"]').val(row.specification);
	                $(this).find('input[name="materialUnitName"]').val(row.materialUnitName);
	                $(this).find('input[name="materialSubArray"]').val(row.materialSubArray);
	                $(this).find('input[name="requireQty"]').val(row.requireQty);
	                $(this).find('input[name="purchaseQty"]').val(row.purchaseQty);
	                $(this).find('input[name="stockQty"]').val(row.stockQty);
	                $(this).find('input[name="reserveQty"]').val(row.reserveQty);
	                $(this).find('input[name="onwayQty"]').val(row.onwayQty);
	                $(this).find('input[name="budgetQty"]').val(row.budgetQty);
	                $(this).find('input[name="referencePrice"]').val(row.referencePrice);
					$(this).find('input[name="budgetPrice"]').val(row.budgetPrice);
					var referenceAmount = $(this).find('input[name="referencePrice"]').val()
										* $(this).find('input[name="purchaseQty"]').val();
				    $(this).find('input[name="referenceAmount"]').val(referenceAmount);
				    $(this).find('input[name="requireDate"]').val(row.requireDate.substr(0,10));
				    $(this).find('input[name="arriveDate"]').val(row.arriveDate.substr(0,10));
				    $(this).find('input[name="purchaserName"]').val(row.purchaserName);
				    $(this).find('input[name="description"]').val(row.description);
	            }
			});
			//合计
			calculateTotal();

			$("#requirePlanTable").find('.decimal').keyup(function () {
				//合计
				calculateTotal();
			});
		}
	});
}
function pageInit(){
    var $grid = $("#requirePlanTable");
	//创建jqGrid组件
	$grid.jqGrid(
			{
				// url : '/requirementPlan/requirementPlan/getRequirePlanDetailByCode/'+code,//组件创建完成之后请求数据的url
				// datatype : "json",//请求数据返回的类型。可选json,xml,txt
				unwritten: false,
                datatype: "local",
				height: '100%',
                autowidth: true,
				colNames : [ '序号', '物资名称', '物资编码', '规格', '单位名称','包装物料',
							'需求数量', '采购数量', '库存数量', '安全库存', '在途数量','预算数量',
						 	'参考单价', '预算金额', '参考金额','需求日期', '要求到货时间', '采购员','说明信息'],//jqGrid的列显示名字
				colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
				             {name : 'requirePlanid',index : 'requirePlanid',width : 100,align : "right",sortable: false},
				             {name : 'materialName',index : 'materialName',width : 100,align : "right",sortable: false},
				             {name : 'materilaCode',index : 'materilaCode',width : 100,align : "right",sortable: false},
				             {name : 'specification',index : 'specification',width : 100,align : "right",sortable: false},
				             {name : 'materialUnitName',index : 'materialUnitName',width : 100,align : "right",sortable: false},
				             {name : 'materialSubArray',index : 'materialSubArray',width : 100,align : "right",sortable: false},
							 {name : 'requireQty',index : 'requireQty',width : 100,align : "right",sortable: false},
				             {name : 'purchaseQty',index : 'purchaseQty',width : 100,align : "right",sortable: false},
				             {name : 'stockQty',index : 'stockQty',width : 100,align : "right",sortable: false},
				             {name : 'reserveQty',index : 'reserveQty',width : 100,align : "right",sortable: false},
				             {name : 'onwayQty',index : 'onwayQty',width : 100,align : "right",sortable: false},
				             {name : 'budgetQty',index : 'budgetQty',width : 100,align : "right",sortable: false},
							 {name : 'referencePrice',index : 'referencePrice',width : 100,align : "right",sortable: false},
				             {name : 'budgetPrice',index : 'budgetPrice',width : 100,align : "right",sortable: false},
				             {name : 'referenceAmount',index : 'referenceAmount',width : 100,align : "right",sortable: false},
				             {name : 'requireDate',index : 'requireDate',width : 100,align : "right",sortable: false},
				             {name : 'arriveDate',index : 'arriveDate',width : 130,align : "right",sortable: false},
				             {name : 'purchaserName',index : 'purchaserName',width : 100,align : "right",sortable: false},
							 {name : 'description',index : 'description',width : 100,align : "right",sortable: false}
				           ],
				rowNum : 10,//一页显示多少条
				rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
				//pager : '#requireApplyPage',//表格页脚的占位符(一般是div)的id
				// gridComplete: completeMethod,
				pager: false,
				sortname : 'id',//初始化的时候排序的字段
				sortorder : "desc",//排序方式,可选desc,asc
				mtype : "get",//向后台请求数据的ajax的类型。可选post,get
				viewrecords : true,
				rownumbers: true,
               shrinkToFit: false,
               gridview: true,
               footerrow: true,
			   cellEdit:true,
			    multiselect: true,
               gridComplete: function () {
                   //合计
                   $(this).footerData("set", {
                       "materialSubArray": "合计：",
                       "requireQty": "<span id='requireQty'>0.00</span>",
                       "purchaseQty": "<span id='purchaseQty'>0.00</span>",
					   "stockQty": "<span id='stockQty'>0.00</span>",
                       "onwayQty": "<span id='onwayQty'>0.00</span>",
					   "budgetQty": "<span id='budgetQty'>0.00</span>",
                       "budgetPrice": "<span id='budgetPrice'>0.00</span>",
					   "referenceAmount": "<span id='referenceAmount'>0.00</span>"
                   });
               },
			   onSelectRow : function(id) {
				  var lastsel;
				   // deleteSelectedRow(id);
                  if (id && id !== lastsel) {
                    jQuery('#requirePlanTable').jqGrid('restoreRow', lastsel);
                    jQuery('#requirePlanTable').jqGrid('editRow', id, true);
                    lastsel = id;
                  }
                }
			});
			 //表头合并
		    $grid.jqGrid('setGroupHeaders', {
		        useColSpanStyle: true,
		        groupHeaders: [
		          { startColumnName: 'requireQty', numberOfColumns: 6, titleText: '<div align="center"><span>数量信息</span></div>'},
		          { startColumnName: 'referencePrice', numberOfColumns: 3, titleText: '<div align="center"><span>金额信息</span></div>'}
		        ]
		    });
	/*创建jqGrid的操作按钮容器*/
	/*可以控制界面上增删改查的按钮是否显示*/
	// jQuery("#requirePlanTable").jqGrid('navGrid', '#requirePlanPage', {edit : false,add : false,del : false});
	// jQuery("#requirePlanTable").jqGrid('inlineNav', "#requirePlanPage");
}
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			planNo : {
				required : true
			},
			invoiceDate : {
				required : true,
			},
			purchaseDept : {
				required : true
			},
			authorUser : {
				required : true
			},
			createDate : {
				required : true,
			},
			remark : {
				required : true,
			}
		},
		messages : {
			planNo : {
				required : icon + "请输入单据编号"
			},
			invoiceDate : {
				required : icon + "请输入单据日期",
			},
			purchaseDept : {
				required : icon + "请输入采购部门"
			},
			authorUser : {
				required : icon + "请输入编制人"
			},
			createDate : {
				required : icon + "请输入编制日期",
			},
			remark : {
				required : icon + "请输入备注"
			},
		}
	})
}
function datetimepicker(){
	//编制日期
	$('#createDate').datetimepicker({
		   format: 'YYYY-MM-DD ',
		   locale: moment.locale('zh-cn')
	});
	//单据日期
	$('#invoiceDate').datetimepicker({
		   format: 'YYYY-MM-DD ',
		   locale: moment.locale('zh-cn')
	});

}
