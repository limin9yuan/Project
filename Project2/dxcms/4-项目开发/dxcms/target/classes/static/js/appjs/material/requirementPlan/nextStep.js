$().ready(function() {
	validateRule();
	getGridData();
	pageInit();
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
function pageInit(){
    var $grid = $("#requireApplyTable");
	//创建jqGrid组件
	$grid.jqGrid(
			{
				//url : '/material/requireApply/test',//组件创建完成之后请求数据的url
				//datatype : "json",//请求数据返回的类型。可选json,xml,txt
				unwritten: false,
                datatype: "local",
				height: '100%',
                autowidth: true,
				colNames : [ '序号', '物资名称', '物资编码', '规格', '单位名称','包装物料',
							'需求数量', '采购数量', '库存数量', '安全库存', '在途数量','预算数量',
						 	'参考单价', '预算金额', '参考金额','需求日期', '要求到货时间', '采购员','说明信息'],//jqGrid的列显示名字
				colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
				             {name : 'requirePlanid',index : 'id',width : 55,editable:true},
				             {name : 'materialName',index : 'invdate',width : 90,editable:true},
				             {name : 'materilaCode',index : 'name asc, invdate',width : 100,editable:true},
				             {name : 'specification',index : 'amount',width : 80,align : "right",editable:true},
				             {name : 'materialUnitName',index : 'tax',width : 80,align : "right",editable:true},
				             {name : 'materialSubArray',index : 'total',width : 80,align : "right",editable:true},
							 {name : 'requireQty',index : 'id',width : 55,editable:true},
				             {name : 'purchaseQty',index : 'invdate',width : 90,editable:true},
				             {name : 'stockQty',index : 'name asc, invdate',width : 100,editable:true},
				             {name : 'reserveQty',index : 'amount',width : 80,align : "right",editable:true},
				             {name : 'onwayQty',index : 'tax',width : 80,align : "right",editable:true},
				             {name : 'budgetQty',index : 'total',width : 80,align : "right",editable:true},
							 {name : 'referencePrice',index : 'id',width : 55,editable:true},
				             {name : 'budgetPrice',index : 'invdate',width : 90,editable:true},
				             {name : 'id',index : 'name asc, invdate',width : 100,editable:true},
				             {name : 'requireDate',index : 'amount',width : 80,align : "right",editable:true},
				             {name : 'arriveDate',index : 'tax',width : 80,align : "right",editable:true},
				             {name : 'purchaserName',index : 'total',width : 80,align : "right",editable:true},
							 {name : 'description',index : 'total',width : 80,align : "right",editable:true}
				           ],
				rowNum : 10,//一页显示多少条
				rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
				//pager : '#requireApplyPage',//表格页脚的占位符(一般是div)的id
				pager: false,
				sortname : 'id',//初始化的时候排序的字段
				sortorder : "desc",//排序方式,可选desc,asc
				mtype : "get",//向后台请求数据的ajax的类型。可选post,get
				viewrecords : true,
				rownumbers: true,
               shrinkToFit: false,
               gridview: true,
               footerrow: true,
               gridComplete: function () {
                   //合计
                   $(this).footerData("set", {
                       "UnitId": "合计：",
                       "amount": "<span id='TotalAmount'>0.00</span>",
                       "total": "<span id='TotalTaxprice'>0.00</span>"
                   });
                   //$('table.ui-jqgrid-ftable td[aria-describedby="gridTable_UnitId"]').prevUntil().css("border-right-color", "#fff");
               },
                onSelectRow : function(id) {
                  if (id && id !== lastsel) {
                    jQuery('#requireApplyTable').jqGrid('restoreRow', lastsel);
                    jQuery('#requireApplyTable').jqGrid('editRow', id, true);
                    lastsel = id;
                  }
                }
			});
	 //表头合并
    $grid.jqGrid('setGroupHeaders', {
        useColSpanStyle: true,
        groupHeaders: [
          { startColumnName: 'requireQty', numberOfColumns: 6, titleText: '数量信息',align : "center" },
          { startColumnName: 'referencePrice', numberOfColumns: 3, titleText: '金额信息',align : "center" }
        ]
    });
    //默认添加13行 空行
    for (var i = 1; i < 13; i++) {
        var rowdata = {
            id: '<input name="id" type="text" />',
            invdate: '<div class="product"><input name="invdate" readonly type="text" class="editable" isvalid="no" checkexpession="NotNull"/><span class="ui-icon-ellipsis"></span></div>',
            name: '<input name="name" type="text" class="editable center disabled" /><input name="name" type="hidden" />',
            amount: '<input name="amount" type="text" class="editable center" />',
            tax: '<input name="tax" type="text" class="editable center decimal" isvalid="no" checkexpession="Double" />',
            total: '<input name="total" type="text" class="editable center decimal" isvalid="no" checkexpession="Double" />',
            note: '<input name="note" type="text" class="editable center decimal disabled" isvalid="no" checkexpession="Double" />'
        }
        $grid.jqGrid('addRowData', i, rowdata);
    };
	/*创建jqGrid的操作按钮容器*/
	/*可以控制界面上增删改查的按钮是否显示*/
	jQuery("#requireApplyTable").jqGrid('navGrid', '#requireApplyPage', {edit : false,add : true,del : true});
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
