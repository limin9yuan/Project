var $ellipsis = null;//行选择物资图标对象

//页面加载完成之后执行
$().ready(function() {
	//初始化页面
	initPage();
	//初始化页面
	initControl();
    //加载数据
    loadRequireApplyDetail();
});

//初始化页面
function initPage() {
    $(".bills").height(360);
    //resize重设(表格、树形)宽高
    $(window).resize(function (e) {
        window.setTimeout(function () {
            $('#requireApplyTable').setGridWidth(($('.gridPanel').width()));
            $(".bills").height(360);
        }, 200);
        e.stopPropagation();
    });
}

//初始化控件
function initControl() {
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
                multiselect:false,
				colNames : [
				             '物资名称', '物资编码', '规格型号', '单位', '包装物资',
                             '库存数量' ,'预算数量', '需求数量',
                             '预算单价' ,'参考单价' , '预算金额' ,'参考金额' ,
                             '要求到货时间' , '受理人' , '说明信息'],//jqGrid的列显示名字
				colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
				             {name : 'name',index : 'name',width : 100},//物资名称
				             {name : 'code',index : 'code',width : 90},//物资编码
				             {name : 'specification',index : 'specification',width : 100},//规格型号
				             {name : 'materialUnitId',index : 'materialUnitId',width : 60,align : "right"},//单位
				             {name : 'materialSubArray',index : 'materialSubArray',width : 80,align : "right"},//包装物资

				             {name : 'stockQty',index : 'stockQty',width : 80,align : "right"},//库存数量
				             {name : 'budgetQty',index : 'budgetQty',width : 80,align : "right"},//预算数量
				             {name : 'requireQty',index : 'requireQty',width : 80,align : "right",editable:true},//需求数量

				             {name : 'budgetPrice',index : 'budgetPrice',width : 80,align : "right"},//预算单价
				             {name : 'referencePrice',index : 'referencePrice',width : 80,align : "right"},//参考单价
				             {name : 'budgetTotal',index : 'budgetTotal',width : 100,align : "right"},//预算金额
				             {name : 'referenceTotal',index : 'referenceTotal',width : 100,align : "right"},//参考金额

				             {name : 'requireDate',index : 'requireDate',width : 100,align : "right"},//要求到货时间
				             {name : 'acceptUserId',index : 'acceptUserId',width : 80,align : "right"},//受理人
				             {name : 'description',index : 'description',width : 150,align : "right"}//说明信息
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
                cellEdit: false,
                gridComplete: function () {
                   //合计
                   $(this).footerData("set", {
                       "UnitId": "合计：",
                       "budgetQty": "<span id='budgetQty'>0.00</span>",
                       "requireQty": "<span id='requireQty'>0.00</span>",
                       "budgetTotal": "<span id='budgetTotal'>0.00</span>",
                       "referenceTotal": "<span id='referenceTotal'>0.00</span>"
                   });
               },
                onSelectRow : function(id) {
                  /*if (id && id !== lastsel) {
                    jQuery('#requireApplyTable').jqGrid('restoreRow', lastsel);
                    //jQuery('#requireApplyTable').jqGrid('editRow', id, true);
                    lastsel = id;
                  }*/
                }
			});
	 //表头合并
    $grid.jqGrid('setGroupHeaders', {
        useColSpanStyle: true,
        groupHeaders: [
          { startColumnName: 'stockQty', numberOfColumns: 3, titleText: '<div class="group-headers">数量信息</div>' },
          { startColumnName: 'budgetPrice', numberOfColumns: 4, titleText: '<div  class="group-headers">金额信息</div>' }
        ]
    });

    //默认添加10行 空行
    for (var i = 0; i < 10; i++) {
        var rowdata = getEmptyRow();
        $grid.jqGrid('addRowData', i, rowdata);
    };
    //将全部输入框设为只读
     $grid.find('input').attr("disabled", "disabled");


}

//空数据行
function getEmptyRow(){
    var rowdata = {
         name : '<div class="product"><input name="name" readonly type="text" class="editable left" isvalid="no" checkexpession="NotNull"/><span class="ui-icon-ellipsis"></span></div>',
         code : '<input name="code" type="text" class="editable left disabled" />',//物资编码
         specification: '<input name="specification" type="text" class="editable left disabled" />',//规格型号
         materialUnitId: '<input name="materialUnitName" type="text" class="editable left disabled" /><input name="materialUnitId" type="hidden"/>',//单位
         materialSubArray: '<input name="materialSubArray" type="text" class="editable left disabled" />',//包装物资

         stockQty: '<input name="stockQty" type="text" class="editable left disabled" />',//库存数量
         budgetQty: '<input name="budgetQty" type="text" class="editable left disabled" />',//预算数量
         requireQty: '<input name="requireQty" type="text" class="editable left decimal" checkexpession="Double" />',//需求数量

         budgetPrice: '<input name="budgetPrice" type="text" class="editable left disabled" />',//预算单价
         referencePrice: '<input name="referencePrice" type="text" class="editable left disabled" />',//参考单价
         budgetTotal: '<input name="budgetTotal" type="text" class="editable left disabled" />',//预算金额
         referenceTotal: '<input name="referenceTotal" type="text" class="editable left disabled" />',//参考金额
         requireDate: '<input name="requireDate" type="text" class="editable left disabled" />',//要求到货时间
         acceptUserId: '<input name="acceptUserName" type="text" class="editable left disabled" /><input name="acceptUserId" type="hidden" />',//受理人
         description: '<input name="description" type="text" class="editable left  disabled" />'//说明信息
    }
    return rowdata;
}

//计算汇总值
function calculateTotal(){
    //合计
    var budgetTotalQty = 0.00, budgetTotalAccount = 0.00,  requireTotalQty = 0.00,  referenceTotal = 0.00;
    $("#requireApplyTable").find("tbody tr").each(function (i) {
         //预算数量
        var Qty = $(this).find('td:eq(7)').find('input').val();
        if (Qty != "" && Qty != undefined) {
            budgetTotalQty += Number(Qty);
        }
        //需求数量
         var qty = $(this).find('td:eq(9)').find('input').val();
        if (qty != "" && qty != undefined) {
            requireTotalQty += Number(qty);
        }
        //预算金额
        var account = $(this).find('td:eq(11)').find('input').val();
        if (account != "" && Qty != undefined) {
            requireTotalQty += Number(account);
        }
        //参考金额
        var account = $(this).find('td:eq(13)').find('input').val();
        if (account != "" && account != undefined) {
            referenceTotal += Number(account);
        }
    });
    $("#budgetQty").text(toDecimal(requireTotalQty));
    $("#budgetTotal").text(toDecimal(referenceTotal));
    $("#requireQty").text(toDecimal(requireTotalQty));
    $("#referenceTotal").text(toDecimal(referenceTotal));
}

//添加行
function addRow(){
     var $grid = $("#requireApplyTable");
     var ids = $grid.jqGrid('getDataIDs');
     var thistr = "1";
     var newid = "0"
     if(ids.length>0){
        thistr = ids.length+1;//新加行tr序号
        newid = Math.max.apply(Math,ids)+1;
     }else{
        thistr = "1";
        newid = "0"
     }
     var rowdata =getEmptyRow();
     $grid.jqGrid('addRowData', newid, rowdata);
     $grid.find("tbody tr:eq("+thistr+")").find('input').attr("disabled", "disabled");
     if(!!$grid.find("tbody tr:eq("+thistr+")").last().find('input[name="name"]').val() || ids.length==0){
        $grid.find("tbody tr:eq("+thistr+")").find('input').removeAttr('disabled').attr("isvalid", "yes");
     }
     $grid.find("tbody tr:eq("+thistr+")").find('.disabled').attr("disabled", "disabled");
     registEvent();
}

//关闭
function closeTabWin() {
    var tmpObj = $('.J_menuTab.active i',window.parent.document);
    parent.closeTabFromChild(tmpObj);
}

//加载修改数据
function loadRequireApplyDetail(){
    $.ajax({
		url : '/material/requireApply/getRequireApplyDetailByCode',
		type : "get",
		data : {
			'id' : $("#id").val(),
		},
		success : function(data) {
			var orderEntry = data.requireApplyDetailList;
			var $grid = $("#requireApplyTable");
   		    //添加空行
            for (var i = 0; i < orderEntry.length; i++) {
                var rowdata = getEmptyRow();
                $grid.jqGrid('addRowData', i, rowdata);
            };
            $grid.find('input').attr("disabled", "disabled");
            //加载数据
			$("#requireApplyTable").find('[role=row]').each(function (i) {
	            var result = orderEntry[i - 1];
	            if (result != undefined) {
	                $(this).find('input[name="name"]').val(result.name);
                    $(this).find('input[name="code"]').val(result.code).attr('data-value', result.code);
                    $(this).find('input[name="specification"]').val(result.specification);
                    $(this).find('input[name="materialUnitId"]').val(result.materialUnitId);
                    $(this).find('input[name="materialUnitName"]').val(result.materialUnitId);
                    $(this).find('input[name="materialSubArray"]').val(result.materialSubArray);
                    $(this).find('input[name="requireQty"]').val(result.requireQty);
                    $(this).find('input[name="budgetQty"]').val(result.budgetQty);
                    $(this).find('input[name="stockQty"]').val(result.stockQty);
                    $(this).find('input[name="referencePrice"]').val(result.referencePrice);
                    $(this).find('input[name="budgetPrice"]').val(result.budgetPrice);
                    var referenceTotal = (Number(result.referencePrice)*Number(result.requireQty)).toFixed(2);
                    $(this).find('input[name="referenceTotal"]').val(referenceTotal);
                    var tmpBudgetTotal = (Number(result.budgetPrice)*Number(result.budgetQty)).toFixed(2);
                    $(this).find('input[name="budgetTotal"]').val(tmpBudgetTotal);
                    $(this).find('input[name="requireDate"]').val(result.requireDate);
                    $(this).find('input[name="acceptUserId"]').val(result.acceptUserId);
                    $(this).find('input[name="acceptUserName"]').val(result.acceptUserName);
                    $(this).find('input[name="description"]').val(result.description);


	                // $(this).find('input').removeAttr('disabled').attr("isvalid", "yes");
	                // $(this).next().find('input').removeAttr('disabled');
	            }
			});
            calculateTotal();
		}
	});
}