var $ellipsis = null;//行选择物资图标对象

//页面加载完成之后执行
$().ready(function() {
	//初始化页面
	initPage();
	//初始化页面
	initControl();
	//初始化日期控件
    initDatetimepicker();
    //加载数据
    loadRequireApplyDetail();
    //页面数据验证
    validateRule();
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
    //将第一行的选择物资按钮激活
    $grid.find("tbody tr:eq(1)").find('input').removeAttr('disabled').attr("isvalid", "yes");
    $grid.find('.disabled').attr("disabled", "disabled");
    //注册事件
    registEvent();

    //初始化统管部门
    $.ajax({
        type : "GET",
        url : "/system/sysDept/treeEdit",
        data : {
        		'deptIds' : $("#authorCorpIdInit").val()
        		},
        success : function(tree) {
            tree.checked=false;
            var defaults = {
                    zNodes : tree,
                    height : 233,
                    chkStyle: "radio",//设置单选树形 默认是多选
                    radioType : "all",
                    callback:{
                        onCheck: function (treeNode) {
                        //alert("my callback");
                        }
                    }
                };
            $("#authorCorpName").drawMultipleTree(defaults); //初始化树状下拉复选框

        }
    });
}

//初始化日期控件
function initDatetimepicker() {
	 $('#createDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
	 $('#businessDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
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
         requireDate: '<input name="requireDate" type="text" class="editable left" />',//要求到货时间
         acceptUserId: '<input name="acceptUserName" type="text" class="editable left disabled" /><input name="acceptUserId" type="hidden" />',//受理人
         description: '<input name="description" type="text" class="editable left" />'//说明信息
    }
    return rowdata;
}

//注册单元格事件
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
                   getMaterialDetailByCode(res,index);

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
        var $referenceTotal = $(this).parents('[role=row]').find('input[name="referenceTotal"]');                //金额
        //数量*单价=金额
        $referenceTotal.val(toDecimal($qty.val() * $price.val()));
        calculateTotal();
    });
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

//取得物资数据，并赋值给Grid
function getMaterialDetailByCode(code,index){
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
                layer.close(index);
            } else {
                parent.layer.msg('该物资已存在,不能重复添加');
            }
   		}
   	});
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

//删除行
function deleteRow(){
	var ids = $("#requireApplyTable").jqGrid("getGridParam", "selarrrow");
	$(ids).each(function (index, id){
		$("#requireApplyTable").delRowData(id);
	});
	$("#requireApplyTable").find("tbody tr:eq(1)").find('input').removeAttr('disabled').attr("isvalid", "yes");
	//计算汇总值
	calculateTotal();
}

//取得可编辑表格数据
function getGridData(){
    var applyEntryJson = [];
    $("#requireApplyTable").find('[role=row]').each(function (i) {
        if (!!$(this).find('input[name="name"]').val()) {
             applyEntryJson.push({
             name : $(this).find('input[name="name"]').val(),//物资名称
             code : $(this).find('input[name="code"]').val(),//物资编码
             specification: $(this).find('input[name="specification"]').val(),//规格型号
             materialUnitId: $(this).find('input[name="materialUnitId"]').val(),//单位
             materialSubArray:$(this).find('input[name="materialSubArray"]').val(),//包装物资

             stockQty:$(this).find('input[name="stockQty"]').val(),//库存数量
             budgetQty: $(this).find('input[name="budgetQty"]').val(),//预算数量
             requireQty: $(this).find('input[name="requireQty"]').val(),//需求数量

             budgetPrice: $(this).find('input[name="budgetPrice"]').val(),//预算单价
             referencePrice: $(this).find('input[name="referencePrice"]').val(),//参考单价
             budgetTotal:$(this).find('input[name="budgetTotal"]').val(),//预算金额
             referenceTotal:$(this).find('input[name="referenceTotal"]').val(),//参考金额
             requireDate: $(this).find('input[name="requireDate"]').val(),//要求到货时间
             acceptUserId: $(this).find('input[name="acceptUserId"]').val(),//受理人
             description: $(this).find('input[name="description"]').val()//说明信息
            });
        }
    });
    return applyEntryJson;
}

//保存
function save() {
    $('#saveBtn').attr("disabled","disabled");
    applyEntryJson = getGridData();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/material/requireApply/save",
        data :  {
                 'name' : $("#name").val(),
                 'code' : $("#code").val(),
                 'authorCorpId' : $("#authorCorpId").val(),
                 'businessDate' : $("#businessDate").data('date'),
                 'createUserId' : $("#createUserId").val(),
                 'createDate' : $("#createDate").data('date'),
                 'applyEntryJson' : applyEntryJson
                 },
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                $('#saveBtn').removeAttr('disabled');
                $("#id").val(data.id);
                parent.layer.msg("保存成功");
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });
}

//提交审批
function commitApply() {
    //提交审批按钮设为不可用
    $('#commitApplyBtn').attr("disabled","disabled");
    //组织数据
    var param = null;
    if($("#id").val()!=""){
        param = {id:$("#id").val()};
    }else{
        applyEntryJson = getGridData();
        param = {
                 'id' : $("#id").val(),//id
                 'name' : $("#name").val(),//名称
                 'code' : $("#code").val(),//单据编号
                 'authorCorpId' : $("#authorCorpId").val(),//统管部门id
                 'businessDate' : $("#businessDate").data('date'),//单据日期
                 'createUserId' : $("#createUserId").val(),//编制人
                 'createDate' : $("#createDate").data('date'),//编制日期
                 'applyEntryJson' : applyEntryJson //物资明细数据
                 };
    }
    //提交
    $.ajax({
    		cache : true,
    		type : "POST",
    		url : "/material/requireApply/commitApply",
    		data :  param,
    		async : false,
    		error : function(request) {
    			parent.layer.alert("Connection error");
    		},
    		success : function(data) {
    			if (data.code == 0) {
    			    //提交审批按钮设为可用
    			    $('#commitApplyBtn').removeAttr('disabled');
    				parent.layer.msg("提交审批成功");
    			} else {
    				parent.layer.alert(data.msg)
    			}

    		}
    	});
}
//撤销审批
function cancelApply() {
}
//关闭
function closeTabWin() {
    var tmpObj = $('.J_menuTab.active i',window.parent.document);
    parent.closeTabFromChild(tmpObj);
}

//验证规则
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#requireApplyForm").validate({
		rules : {
			name : {
				maxlength : 200
			},
			code : {
				required : true,
                maxlength : 50
			},
			businessDate : {
				required : true,
				date:true
			},
			remark : {
				maxlength : 100
			}
		},
		messages : {
			code : {
			    required : "请输入单据编号",
				maxlength : icon + "单据编号必须50个字符以内"
			},
			businessDate : {
                required : "请输入编制日期",
                date:"请输入正确格式的日期"
            },
			remark : {
            	maxlength : icon + "备注必须100个字符以内"
            }
		}
	})
}

//保存按钮事件
$("#saveBtn").click(function (){
    if($("#requireApplyForm").valid()){
        save();
     }
})

//提交审批按钮事件
$("#commitApplyBtn").click(function (){
    if($("#requireApplyForm").valid()){
        commitApply();
     }
})

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
            $grid.find('.decimal').attr('onfocus', 'IsMoney(this.id)');
            //$grid.find('input').attr("disabled", "disabled");
            //$grid.find("tbody tr:eq(1)").find('input').removeAttr('disabled').attr("isvalid", "yes");
            $grid.find('.disabled').attr("disabled", "disabled");
            //注册事件
            registEvent();
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