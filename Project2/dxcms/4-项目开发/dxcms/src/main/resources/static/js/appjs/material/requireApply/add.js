var lastsel ="";
var $ellipsis = null;
$(function(){
	//页面加载完成之后执行
	initPage();
	initControl();
    initDatetimepicker()
});
function initDatetimepicker() {
	 $('#createDate').datetimepicker({
	        format: 'YYYY-MM-DD ',
	        locale: moment.locale('zh-cn')
	    });
	 $('#businessDate').datetimepicker({
	        format: 'YYYY-MM-DD ',
	        locale: moment.locale('zh-cn')
	    });
}
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
                multiselect:true,
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
                       "requireQty": "<span id='requireQty'>0.00</span>",
                       "referenceTotal": "<span id='referenceTotal'>0.00</span>"
                   });
                   //$('table.ui-jqgrid-ftable td[aria-describedby="gridTable_UnitId"]').prevUntil().css("border-right-color", "#fff");
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
    for (var i = 1; i < 5; i++) {
        var rowdata = getEmptyRow();
        $grid.jqGrid('addRowData', i, rowdata);
    };

    $grid.find('.editable').attr("disabled", "disabled");
    $grid.find('.disabled').attr("disabled", "disabled");
    $grid.find("tbody tr:eq(1)").find('input').removeAttr('disabled').attr("isvalid", "yes");
	/*可以控制界面上增删改查的按钮是否显示*/
	//jQuery("#requireApplyTable").jqGrid('navGrid', '#requireApplyPage', {edit : true,add : true,del : true});
	//jQuery("#requireApplyTable").jqGrid('inlineNav', '#requireApplyPage');
    registEvent();
}
function getMaterialDetailByCode(code){
    var dataTmp="";
    var url = '/material/requireApply/getMaterialDetailByCode/'+code;
   $.ajax({
   		cache : true,
   		type : "get",
   		url : url,
   		async : false,
   		error : function(request) {
   			parent.layer.alert("Connection error");
   		},
   		success : function(data) {
   		    result=data.materialDetail;
            if ( $("#requireApplyTable").find('[role=row]').find('[data-value=' + result.code + ']').length == 0) {

                $ellipsis.parents('[role=row]').find('input[name="name"]').val(result.name);
                $ellipsis.parents('[role=row]').find('input[name="code"]').val(result.code).attr('data-value', result.code);
                $ellipsis.parents('[role=row]').find('input[name="specification"]').val(result.specification);
                $ellipsis.parents('[role=row]').find('input[name="materialUnitId"]').val(result.materialUnitId);
                $ellipsis.parents('[role=row]').find('input[name="materialUnitName"]').val(result.materialUnitName);
                $ellipsis.parents('[role=row]').find('input[name="materialSubArray"]').val(result.materialSubArray);
                $ellipsis.parents('[role=row]').find('input[name="requireQty"]').val('0');
                $ellipsis.parents('[role=row]').find('input[name="budgetQty"]').val(result.budgetQty);
                $ellipsis.parents('[role=row]').find('input[name="stockQty"]').val(result.stockQty);
                $ellipsis.parents('[role=row]').find('input[name="referencePrice"]').val(result.referencePrice);
                $ellipsis.parents('[role=row]').find('input[name="budgetPrice"]').val(result.budgetPrice);
                $ellipsis.parents('[role=row]').find('input[name="referenceTotal"]').val('0.00');
                var tmpBudgetTotal = (Number(result.budgetPrice)*Number(result.budgetQty)).toFixed(2);
                $ellipsis.parents('[role=row]').find('input[name="budgetTotal"]').val(tmpBudgetTotal);
                $ellipsis.parents('[role=row]').find('input[name="requireDate"]').val(result.requireDate);
                $ellipsis.parents('[role=row]').find('input[name="acceptUserId"]').val(result.acceptUserId);
                $ellipsis.parents('[role=row]').find('input[name="acceptUserName"]').val(result.acceptUserName);
                $ellipsis.parents('[role=row]').find('input[name="description"]').val(result.description);

                //$ellipsis.parents('[role=row]').find('input').removeAttr('disabled').attr("isvalid", "yes");
                $ellipsis.parents('[role=row]').next().find('input').removeAttr('disabled');
            } else {
                parent.layer.msg('该物资已存在,不能重复添加');
            }
   		}
   	});

    return dataTmp;
}
function getEmptyRow(){
    var rowdata = {
        // name : '<input name="name" type="text" class="editable center disabled" readonly/>',//物资名称
         name : '<div class="product"><input name="name" readonly type="text" class="editable" isvalid="no" checkexpession="NotNull"/><span class="ui-icon-ellipsis"></span></div>',
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
         requireDate: '<input name="requireDate" type="text" class="editable left" />',//要求到货时间
         acceptUserId: '<input name="acceptUserName" type="text" class="editable left disabled" /><input name="acceptUserId" type="hidden" />',//受理人
         description: '<input name="description" type="text" class="editable left" />'//说明信息
    }
    return rowdata;
}
function addRow(){
    //alert($(".bills").height());
    //s$(".bills").height($(".bills").height+25);

     var $grid = $("#requireApplyTable");
     var ids = $grid.jqGrid('getDataIDs');
     var maxid = Math.max.apply(Math,ids);
     var newid = maxid+1;
     var rowdata =getEmptyRow();
     $grid.jqGrid('addRowData', newid, rowdata);
     $grid.find("tbody tr:eq("+newid+")").find('input').find('.editable').attr("disabled", "disabled");
     $grid.find("tbody tr:eq("+newid+")").find('input').find('.disabled').attr("disabled", "disabled");
     alert($grid.find("tbody tr:eq("+maxid+")").find('input[name="name"]').val());
     if($grid.find("tbody tr:eq("+maxid+")").find('input[name="name"]').val()!=""){
        $grid.find("tbody tr:eq("+newid+")").find('input').removeAttr('disabled').attr("isvalid", "yes");
     }
     registEvent();
}
function registEvent(){
    var $grid = $("#requireApplyTable");
    //数字input
    $grid.find('.decimal').attr('onfocus', 'IsMoney(this.id)');
    //物资名称事件
    $('input[name="name"]').focus(function () {
        $('.ui-icon-ellipsis').hide();
        $(this).next('.ui-icon-ellipsis').show();
       // $(this).Contextmenu();
    });
    //选择物资事件
    $('.ui-icon-ellipsis').click(function () {
        $ellipsis = $(this);
        layer.open({
                type : 2,
                title : '选择物资',
                maxmin : true,
                shadeClose : false, // 点击遮罩关闭层
                area : [ '90%', '85%' ],
                content : "/material/requireApply/materialList",
                btn: ['确定','取消'],
                yes: function(index, layero){
                   var res = window["layui-layer-iframe" + index].getSelectedMaterial();
                   getMaterialDetailByCode(res);
                   layer.close(index);
                },
                btn2:function(index, layero){
                    layer.close(index);
               }
            });
    });
    //需求数量文本框换算
    $grid.find('.decimal').keyup(function () {
        var $qty = $(this).parents('[role=row]').find('input[name="requireQty"]');                    //数量
        var $price = $(this).parents('[role=row]').find('input[name="referencePrice"]');                //单价
        var $referenceTotal = $(this).parents('[role=row]').find('input[name="referenceTotal"]');                //单价
        //数量*单价=金额
        $referenceTotal.val(toDecimal($qty.val() * $price.val()));
        //合计
        var budgetTotalQty = 0.00, budgetTotalAccount = 0.00,  requireTotalQty = 0.00,  referenceTotal = 0.00;
        $grid.find("tbody tr").each(function (i) {
             //预算数量
            /*var Qty = $(this).find('td:eq(7)').find('input').val();
            if (Qty != "" && Qty != undefined) {
                budgetTotalQty += Number(Qty);
            }*/
            //需求数量
             var qty = $(this).find('td:eq(8)').find('input').val();
            if (qty != "" && qty != undefined) {
                requireTotalQty += Number(qty);
            }
            //预算金额
            /*var account = $(this).find('td:eq(11)').find('input').val();
            if (account != "" && Qty != undefined) {
                requireTotalQty += Number(account);
            }*/
            //参考金额
            var account = $(this).find('td:eq(12)').find('input').val();
            if (account != "" && account != undefined) {
                referenceTotal += Number(account);
            }
        });
        $("#requireQty").text(toDecimal(requireTotalQty));
        $("#referenceTotal").text(toDecimal(referenceTotal));
    });
}