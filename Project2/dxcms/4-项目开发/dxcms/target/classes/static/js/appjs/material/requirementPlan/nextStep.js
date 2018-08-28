$().ready(function() {
	validateRule();
	getGridData();
	pageInit();
	getRequirePlanDetailByCode();
	// initPage();
	// datetimepicker();
});
// function datetimepicker(){
// 	//开始时间
// 	$('#timeStart').datetimepicker({
// 		format:'YYYY-MM-DD',
// 		// locale:moment.locale('zh-cn')
// 	});
// 	//结束时间
// 	$('#timeEnd').datetimepicker({
// 		format:'YYYY-MM-DD',
// 		// locale:moment.locale('zh-cn')
// 	});
// }
// function initPage() {
//     $(".bills").height($(window).width() +200);
//     //resize重设(表格、树形)宽高
//     $(window).resize(function (e) {
//         window.setTimeout(function () {
//             $('#requirePlanPage').setGridWidth(($('.gridPanel').width()));
//             $(".bills").height($(window).height() - 128);
//         }, 200);
//         e.stopPropagation();
//     });
// }
function getGridData(){
    var dataTmp="";
    var url = '/material/requireApply/test';
   $.ajax({
   		cache : true,
   		type : "get",
   		url : url,
   		async : false,
   		error : function(request) {
   			parent.layer.alert("Connection error");
   		},
   		success : function(data) {
   			dataTmp=data;

   		}
   	});

    return dataTmp;
}
function getRequirePlanDetailByCode(){
	$.ajax({
		url : '/requirementPlan/requirementPlan/getRequirePlanDetailByCode',
		type : "get",
		data : {
			'code' : $("#materilaCode").val(),
		},
		success : function(data) {
			var orderEntry = data.getRequirePlanDetailList;
			$("#requirePlanTable").find('[role=row]').each(function (i) {
	            var row = orderEntry[i - 1];
	            if (row != undefined) {
	                $(this).find('input[name="requirePlanid"]').val(row.requirePlanid);
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
				    $(this).find('input[name="requireDate"]').val(row.requireDate);
				    $(this).find('input[name="arriveDate"]').val(row.arriveDate);
				    $(this).find('input[name="purchaserName"]').val(row.purchaserName);
				    $(this).find('input[name="description"]').val(row.description);
	                // $(this).find('input').removeAttr('disabled').attr("isvalid", "yes");
	                // $(this).next().find('input').removeAttr('disabled');
	            }
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
				             {name : 'requirePlanid',index : 'requirePlanid',width : 100,editable:false},
				             {name : 'materialName',index : 'materialName',width : 100,editable:true},
				             {name : 'materilaCode',index : 'materilaCode',width : 100,editable:false},
				             {name : 'specification',index : 'specification',width : 100,align : "right",editable:false},
				             {name : 'materialUnitName',index : 'materialUnitName',width : 100,align : "right",editable:false},
				             {name : 'materialSubArray',index : 'materialSubArray',width : 100,align : "right",editable:false},
							 {name : 'requireQty',index : 'requireQty',width : 100,editable:true},
				             {name : 'purchaseQty',index : 'purchaseQty',width : 100,editable:true},
				             {name : 'stockQty',index : 'stockQty',width : 100,editable:false},
				             {name : 'reserveQty',index : 'reserveQty',width : 100,align : "right",editable:false},
				             {name : 'onwayQty',index : 'onwayQty',width : 100,align : "right",editable:false},
				             {name : 'budgetQty',index : 'budgetQty',width : 100,align : "right",editable:false},
							 {name : 'referencePrice',index : 'referencePrice',width : 100,editable:false},
				             {name : 'budgetPrice',index : 'budgetPrice',width : 100,editable:false},
				             {name : 'referenceAmount',index : 'referenceAmount',width : 100,editable:false},
				             {name : 'requireDate',index : 'requireDate',width : 100,align : "right",editable:false},
				             {name : 'arriveDate',index : 'arriveDate',width : 130,align : "right",editable:true},
				             {name : 'purchaserName',index : 'purchaserName',width : 100,align : "right",editable:false},
							 {name : 'description',index : 'description',width : 100,align : "right",editable:true}
				           ],
				rowNum : 10,//一页显示多少条
				rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
				//pager : '#requireApplyPage',//表格页脚的占位符(一般是div)的id
				gridComplete: completeMethod,
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
               // gridComplete: function () {
               //     //合计
               //     $(this).footerData("set", {
               //         "UnitId": "合计：",
               //         "amount": "<span id='TotalAmount'>0.00</span>",
               //         "total": "<span id='TotalTaxprice'>0.00</span>"
               //     });
               //     //$('table.ui-jqgrid-ftable td[aria-describedby="gridTable_UnitId"]').prevUntil().css("border-right-color", "#fff");
               // }
			   // ,
               //  onSelectRow : function(id) {
               //    if (id && id !== lastsel) {
               //      jQuery('#requireApplyTable').jqGrid('restoreRow', lastsel);
               //      jQuery('#requireApplyTable').jqGrid('editRow', id, true);
               //      lastsel = id;
               //    }
               //  }
			});
	function completeMethod(){
                var sum_requireQty=$("#requirePlanTable").getCol('requireQty',false,'sum');
                var sum_purchaseQty=$("#requirePlanTable").getCol('purchaseQty',false,'sum');
				var sum_stockQty=$("#requirePlanTable").getCol('stockQty',false,'sum');
				var sum_onwayQty=$("#requirePlanTable").getCol('onwayQty',false,'sum');
				var sum_budgetQty=$("#requirePlanTable").getCol('budgetQty',false,'sum');
				var sum_budgetPrice=$("#requirePlanTable").getCol('budgetPrice',false,'sum');
				var sum_referenceAmount=$("#requirePlanTable").getCol('referenceAmount',false,'sum');
                $("#requirePlanTable").footerData('set', { "materialSubArray": '合计',requireQty: sum_requireQty, purchaseQty: sum_purchaseQty,
															stockQty: sum_stockQty,onwayQty: sum_onwayQty,budgetQty: sum_budgetQty,
															budgetPrice: sum_budgetPrice,referenceAmount: sum_referenceAmount});
            }
	 //表头合并
    $grid.jqGrid('setGroupHeaders', {
        useColSpanStyle: true,
        groupHeaders: [
          { startColumnName: 'requireQty', numberOfColumns: 6, titleText: '<div align="center"><span>数量信息</span></div>'},
          { startColumnName: 'referencePrice', numberOfColumns: 3, titleText: '<div align="center"><span>金额信息</span></div>'}
        ]
    });
    //默认添加13行 空行
    for (var i = 1; i < 11; i++) {
        var rowdata = {
			requirePlanid : '<input name="requirePlanid" readonly type="text" class="editable left disabled"/>',
			materialName : '<input name="materialName" type="text" readonly class="editable left disabled" />',//物资编码
			materilaCode: '<input name="materilaCode" type="text" class="editable left disabled" />',//规格型号
			specification: '<input name="specification" type="text" class="editable left disabled" />',//单位
			materialUnitName: '<input name="materialUnitName" type="text" class="editable left disabled" />',//包装物资

			materialSubArray: '<input name="materialSubArray" type="text" class="editable left disabled" />',//库存数量
			requireQty: '<input name="requireQty" type="text" class="editable left disabled" />',//预算数量
			purchaseQty: '<input name="purchaseQty" type="text" class="editable left disabled"/>',//需求数量

			stockQty: '<input name="stockQty" type="text" class="editable left disabled" />',//预算单价
			reserveQty: '<input name="reserveQty" type="text" class="editable left disabled" />',//参考单价
			onwayQty: '<input name="onwayQty" type="text" class="editable left disabled" />',//预算金额
			budgetQty: '<input name="budgetQty" type="text" class="editable left disabled" />',//参考金额
			referencePrice: '<input name="referencePrice" type="text" class="editable left disabled" />',//要求到货时间
			budgetPrice: '<input name="budgetPrice" type="text" class="editable left disabled" />',//受理人
			referenceAmount: '<input name="referenceAmount" type="text" class="editable left disabled" />',//说明信息

			requireDate: '<input name="requireDate" type="text" class="editable left disabled" />',//预算单价
			arriveDate: '<input name="arriveDate" type="text" class="editable left disabled" />',//参考单价
			purchaserName: '<input name="purchaserName" type="text" class="editable left disabled" />',//预算金额
			description: '<input name="description" type="text" class="editable left disabled" />'//参考金额
		}
        $grid.jqGrid('addRowData', i, rowdata);
    };
	/*创建jqGrid的操作按钮容器*/
	/*可以控制界面上增删改查的按钮是否显示*/
	jQuery("#requirePlanTable").jqGrid('navGrid', '#requirePlanPage', {edit : false,add : true,del : true});
}
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/requirementPlan/save",
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
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}
