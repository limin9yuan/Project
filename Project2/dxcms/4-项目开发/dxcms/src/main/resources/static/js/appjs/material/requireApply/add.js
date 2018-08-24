lastsel ="";
$(function(){
	//页面加载完成之后执行
	pageInit();
});
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
				colNames : [ 'Inv No', 'Date', 'Client', 'Amount', 'Tax','Total', 'Notes' ],//jqGrid的列显示名字
				colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
				             {name : 'id',index : 'id',width : 55,editable:true},
				             {name : 'invdate',index : 'invdate',width : 90,editable:true},
				             {name : 'name',index : 'name asc, invdate',width : 100,editable:true},
				             {name : 'amount',index : 'amount',width : 80,align : "right",editable:true},
				             {name : 'tax',index : 'tax',width : 80,align : "right",editable:true},
				             {name : 'total',index : 'total',width : 80,align : "right",editable:true},
				             {name : 'note',index : 'note',width : 150,sortable : false,editable:true}
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
          { startColumnName: 'invdate', numberOfColumns: 3, titleText: '数量信息' },
          { startColumnName: 'total', numberOfColumns: 7, titleText: '金额信息' }
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