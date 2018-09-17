$().ready(function() {
	pageInit();
	check_ajax();
});
function calculateTotal() {
	var sum_requireQty = 0.00;
	var sum_purchaseQty = 0.00;
	var sum_stockQty = 0.00;
	var sum_onwayQty = 0.00;
	var sum_budgetQty = 0.00;
	var sum_budgetPrice = 0.00;
	var sum_referenceAmount = 0.00;
	$("#purchasePlanTable").find("tbody tr").each(function (i) {
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
function check_ajax(){
	$.ajax({
		url : '/material/purchasePlan/check_ajax/'+$("#planNo").val(),
		type : "get",
		data : {
			'code' : $("#materilaCode").val(),
		},
		success : function(data) {

			var orderEntry = data.checkList;
			for (var i = 0; i < orderEntry.length; i++) {
                var rowdata = {
                    //requirePlanid : '<input name="requirePlanid" type="text" class="editable left disabled" readonly/>',//序号
                    materialType : '<input name="materialType" type="text" class="editable left disabled" readonly/>',//物料类别
					materialName : '<input name="materialName" type="text" class="editable left disabled" readonly/>',//物料名称
					materilaCode: '<input name="materilaCode" type="text" class="editable left disabled" readonly/>',//物料编码
					specification: '<input name="specification" type="text" class="editable left disabled" readonly/>',//规格型号
					materialUnitName: '<input name="materialUnitName" type="text" class="editable left disabled" readonly/>',//单位

					materialSubArray: '<input name="materialSubArray" type="text" class="editable left disabled" readonly/>',//包装物料
					requireQty: '<input name="requireQty" type="text" class="editable left disabled decimal" readonly/>',//需求数量
					purchaseQty: '<input name="purchaseQty" type="text" class="editable left disabled decimal" readonly/>',//采购数量

					stockQty: '<input name="stockQty" type="text" class="editable left disabled decimal" readonly/>',//库存数量
					reserveQty: '<input name="reserveQty" type="text" class="editable left disabled" readonly/>',//安全库存
					onwayQty: '<input name="onwayQty" type="text" class="editable left disabled decimal" readonly/>',//在途数量
					budgetQty: '<input name="budgetQty" type="text" class="editable left disabled decimal" readonly/>',//预算数量
					referencePrice: '<input name="referencePrice" type="text" class="editable left disabled" readonly/>',//参考单价
					budgetPrice: '<input name="budgetPrice" type="text" class="editable left disabled decimal" readonly/>',//预算金额
					referenceAmount: '<input name="referenceAmount" type="text" class="editable left disabled decimal" readonly/>',//参考金额

					//requireDate: '<input name="requireDate" type="text" class="editable left disabled" readonly/>',//需求日期
					arriveDate: '<input name="arriveDate" type="text" class="editable left disabled" readonly/>',//要求到货时间
					//purchaserName: '<input name="purchaserName" type="text" class="editable left disabled" readonly/>',//采购员
                    requireDept: '<input name="requireDept" type="text" class="editable left disabled" readonly/>',//需求部门
                    requirePlanid: '<input name="requirePlanid" type="text" class="editable left disabled" readonly/>',//需求计划编号
					description: '<input name="description" type="text" class="editable left disabled" readonly/>'//说明信息
                }
                $("#purchasePlanTable").jqGrid('addRowData', i, rowdata);
            };
			$("#purchasePlanTable").find('[role=row]').each(function (i) {
	            var row = orderEntry[i - 1];
	            if (row != undefined) {
	                //$(this).find('input[name="requirePlanid"]').val(row.requirePlanid).attr('data-value', row.requirePlanid);
                    $(this).find('input[name="materialType"]').val(row.materialType);
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
				    $(this).find('input[name="referenceAmount"]').val(row.referenceAmount);
				    //$(this).find('input[name="requireDate"]').val(row.requireDate);
				    $(this).find('input[name="arriveDate"]').val(row.arriveDate);
				    //$(this).find('input[name="purchaserName"]').val(row.purchaserName);
                    $(this).find('input[name="requireDept"]').val(row.requireDept);
                    $(this).find('input[name="requirePlanid"]').val(row.requirePlanid).attr('data-value', row.requirePlanid);
				    $(this).find('input[name="description"]').val(row.description);
	                // $(this).find('input').removeAttr('disabled').attr("isvalid", "yes");
	                // $(this).next().find('input').removeAttr('disabled');
	            }
			});
			//合计
			calculateTotal();

			$("#purchasePlanTable").find('.decimal').keyup(function () {
				//合计
				calculateTotal();
			});
		}
	});
}
function pageInit(){
    var $grid = $("#purchasePlanTable");
	//创建jqGrid组件
	$grid.jqGrid(
			{
				// url : '/requirementPlan/requirementPlan/getRequirePlanDetailByCode/'+code,//组件创建完成之后请求数据的url
				// datatype : "json",//请求数据返回的类型。可选json,xml,txt
				unwritten: false,
                datatype: "local",
				height: '100%',
                autowidth: true,
				colNames : [ /*'序号',*/'物资类别', '物资名称', '物资编码', '规格', '单位名称','包装物料',
							'需求数量', '采购数量', '库存数量', '安全库存', '在途数量','预算数量',
						 	'参考单价', '预算金额', '参考金额',/*'需求日期',*/ '要求到货时间', /*'采购员',*/'需求部门','需求计划编号','说明信息'],//jqGrid的列显示名字
				colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
				             //{name : 'requirePlanid',index : 'requirePlanid',width : 100,align : "right",sortable: false},
					         {name : 'materialType',index : 'materialType',width : 100,align : "right",sortable: false},
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
				             //{name : 'requireDate',index : 'requireDate',width : 100,align : "right",sortable: false},
				             {name : 'arriveDate',index : 'arriveDate',width : 130,align : "right",sortable: false},
				             //{name : 'purchaserName',index : 'purchaserName',width : 100,align : "right",sortable: false},
                             {name : 'requireDept',index : 'requireDept',width : 100,align : "right",sortable: false},
					         {name : 'requirePlanid',index : 'requirePlanid',width : 100,align : "right",sortable: false},
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
                    jQuery('#purchasePlanTable').jqGrid('restoreRow', lastsel);
                    jQuery('#purchasePlanTable').jqGrid('editRow', id, true);
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
