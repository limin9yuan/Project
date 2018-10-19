var $ellipsis = null;//行选择物资图标对象

//页面加载完成之后执行
$().ready(function() {
	//初始化页面
	initPage();
	//初始化页面
	initControl();
	//加载数据
	loadRequireApplyDetail();
	//税率
    loadD("contract-tax-rate", "taxRate","oldtaxRate"); 
	//页面数据验证
	validateRule();
});

//初始化页面
function initPage() {
    $(".bills").height(360);
    //resize重设(表格、树形)宽高
    $(window).resize(function (e) {
        window.setTimeout(function () {
            $('#purchaseOrderTable').setGridWidth(($('.gridPanel').width()));
            $(".bills").height(360);
        }, 200);
        e.stopPropagation();
    });
}

//初始化控件
function initControl() {
    var $grid = $("#purchaseOrderTable");
	//创建jqGrid组件
	$grid.jqGrid(
			{
				//url : '/material/requireApply/test',//组件创建完成之后请求数据的url
				//datatype : "json",//请求数据返回的类型。可选json,xml,txt
                datatype: "local",
				height: '100%',
                autowidth: true,
                multiselect:true,
            	rownumbers: true,
                colNames : ['序号','物资名称', '物资编码', '规格型号', '单位','原单价','新单价8',
					'原数量','新数量','原到货日期',' 新到货日期','备注'],//jqGrid的列显示名字
				colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
					{name : 'num',index : 'num',width : 80},//序号
					{name : 'materialName',index : 'materialName',width : 100},//物资名称
		            {name : 'materilaCode',index : 'materilaCode',width : 90},//物资编码
		            {name : 'specification',index : 'specification',width : 100},//规格型号
		            {name : 'materialUnitName',index : 'materialUnitName',width : 60,align : "right"},//单位
					{name : 'price',index : 'price',width : 80},//原单价
					{name : 'newprice',index : 'newprice',width : 80,editable:true},//新单价
					{name : 'qty',index : 'qty',width : 80},//原数量
					{name : 'newqty',index : 'newqty',width : 80,editable:true},//新数量
					{name : 'arriveDate',index : 'arriveDate',width : 100},//原到货日期
					{name : 'newarriveDate',index : 'newarriveDate',width : 100,editable:true},//新到货日期
					{name : 'remark',index : 'remark',width : 80},//备注
				],
				//pager : '#requireApplyPage',//表格页脚的占位符(一般是div)的id
				sortname : 'id',//初始化的时候排序的字段
				sortorder : "desc",//排序方式,可选desc,asc
				mtype : "get",//向后台请求数据的ajax的类型。可选post,get
                shrinkToFit: false,
			});
}
//空数据行
function getEmptyRow(){
    var rowdata = {
    	 num : '<input name="num" readonly type="text" class="editable left disabled"/>',//序号
         materialName : '<input name="materialName" type="text" class="editable left disabled" />',//物资名称
         materilaCode : '<input name="materilaCode" type="text" class="editable left disabled" />',//物资编码
         specification: '<input name="specification" type="text" class="editable left disabled" />',//规格型号
         materialUnitName: '<input name="materialUnitName" type="text" class="editable left disabled" />',//单位
         price: '<input name="price" type="text" class="editable left disabled" />',//原单价
         newprice: '<input name="newprice" type="text" class="editable left decimal" />',//新单价
         qty: '<input name="qty" type="text" class="editable left disabled" />',//数量
         newqty: '<input name="newqty" type="text" class="editable left decimal" />',//新数量
         arriveDate: '<input name="arriveDate" type="text" class="editable left disabled" />',//原到货日期
         newarriveDate: '<input name="newarriveDate" type="text" class="editable left"/>',//新到货日期
         remark: '<input name="remark" type="text" class="editable left disabled" />',//备注
    }
    return rowdata;
}

//注册单元格事件
function registEvent(){
    var $grid = $("#purchaseOrderTable");
    //数字input
    $grid.find('.decimal').attr('onfocus', 'IsMoney(this.id)');
    $grid.find('.decimal').keyup(function () {
    	clearNoNum($(this));
        var $newqty = $(this).parents('[role=row]').find('input[name="newqty"]');                    //数量
        var $newprice = $(this).parents('[role=row]').find('input[name="newprice"]');                //单价
    });
   

}
//删除行
function deleteRow(){
	var ids = $("#purchaseOrderTable").jqGrid("getGridParam", "selarrrow");
	$(ids).each(function (index, id){
		$("#purchaseOrderTable").delRowData(id);
	});
	//只有第一行可编辑
	//$("#requireApplyTable").find("tbody tr:eq(0)").find('input').removeAttr('disabled').attr("isvalid", "yes");
}
//取得可编辑表格数据
function getGridData(){
    var applyEntryJson = [];
    $("#purchaseOrderTable").find('[role=row]').each(function (i) {
        if (!!$(this).find('input[name="materialName"]').val()) {
             applyEntryJson.push({
            	 newprice: $(this).find('input[name="newprice"]').val(),//新单价
            	 newqty: $(this).find('input[name="newqty"]').val(),//新数量
            	 num:$(this).find('input[name="num"]').val(),//序号
            	 materialName:$(this).find('input[name="materialName"]').val(),//物资名称
            	 specification:$(this).find('input[name="specification"]').val(),//规格型号
            	 materialUnitName:$(this).find('input[name="materialUnitName"]').val(),//单位
            	 remark:$(this).find('input[name=" remark"]').val(),//备注
            	 price: $(this).find('input[name="price"]').val(),//原价格            	
            	 qty: $(this).find('input[name="qty"]').val(),//原数量
            	 newarriveDate: $(this).find('input[name="newarriveDate"]').val(),//原到货日期
            	 arriveDate: $(this).find('input[name="arriveDate"]').val(),//新到货日期
            	 materilaCode:$(this).find('input[name="materilaCode"]').val(),//物资编码
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
            	'code' : $("#code").val(),//采购订单编号
            	'contractDelivers' : $("#contractDelivers").val(),//供应商
            	'DeliverCompanyName' : $("#DeliverCompanyName").val(),//发货单位
            	'PurchasePlanCode' : $("#PurchasePlanCode").val(),//采购计划编号
            	'ContractCode' : $("#ContractCode").val(),//意向协议(询比价报告单）编号
				'taxRate' : $("#taxRate").val(),//税率
            	'ArriveAddress' : $("#ArriveAddress").val(),//交货地点
            	'PaymentType' : $("#PaymentType").val(),//付款方式及期限
            	'QualityStandard' : $("#QualityStandard").val(),//质量标准

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
    if($("#materialName").val()!=""){
        param = {materialName:$("#materialName").val()};
    }else{
        applyEntryJson = getGridData();
        param = {
        		'code' : $("#code").val(),//采购订单编号
            	'contractDelivers' : $("#contractDelivers").val(),//供应商
            	'DeliverCompanyName' : $("#DeliverCompanyName").val(),//发货单位
            	'PurchasePlanCode' : $("#PurchasePlanCode").val(),//采购计划编号
            	'AgreementNumber' : $("#AgreementNumber").val(),//意向协议(询比价报告单）编号
            	'taxRate' : $("#taxRate").val(),//税率
            	'ArriveAddress' : $("#ArriveAddress").val(),//交货地点
            	'PaymentType' : $("#PaymentType").val(),//付款方式及期限
            	'QualityStandard' : $("#QualityStandard").val(),//质量标准


                'applyEntryJson' : applyEntryJson
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
$("#saveBtn").click(function (){
    if($("#purchaseOrderForm").valid()){
        save();
     }
})
//提交审批按钮事件
$("#commitApplyBtn").click(function (){
    if($("#purchaseOrderForm").valid()){
        commitApply();
     }
})

//验证规则
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#purchaseOrderForm").validate({
		rules : {
			code : {
				required : true,
                maxlength : 50
			},
		},
		messages : {
			code : {
			    required : "请输入采购订单编号",
				maxlength : icon + "采购订单编号必须50个字符以内"
			},
			
		},
        submitHandler : function() {
            save();
        }
	})
}
function openContractDelivers(newObj,oldObj) {
	layer.open({
		type : 2,
		title : "选择供货信息",
		area : [ '60%', '90%' ],
		content : "/ContractCreation/ContractCreation/contractDelivers"
			
	})			


}
function loadRequireApplyDetail(){
    $.ajax({
		url : '/material/purchaseOrder/getPurchaseOrderDetailList',
		type : "get",
		data : {
			'id' : $("#id").val(),
		},
		success :function(data) {
			var orderEntry = data.getPurchaseOrderDetailList;
			var $grid = $("#purchaseOrderTable");
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
			$("#purchaseOrderTable").find('[role=row]').each(function (i) {
	            var result = orderEntry[i-1];
	            if (result != undefined) {
	            	$(this).find('input[name="num"]').val(result.num);
                    $(this).find('input[name="materialName"]').val(result.materialName);
                    $(this).find('input[name="materilaCode"]').val(result.materilaCode);
                    $(this).find('input[name="specification"]').val(result.specification);
                    $(this).find('input[name="materialUnitName"]').val(result.materialUnitName);
                    $(this).find('input[name="price"]').val(result.price);
                    $(this).find('input[name="newprice"]').val(result.newprice);
                    $(this).find('input[name="qty"]').val(result.qty);
                    $(this).find('input[name="newqty"]').val(result.newqty);
                    $(this).find('input[name="arriveDate"]').val(result.arriveDate);
                    $(this).find('input[name="newarriveDate"]').val(result.newarriveDate);
                    $(this).find('input[name="remark"]').val(result.remark);
	                // $(this).find('input').removeAttr('disabled').attr("isvalid", "yes");
	                // $(this).next().find('input').removeAttr('disabled');
	            }
			});
            //calculateTotal();
		}
	});
}

function changeValue(newObj,oldObj){
	if($("#"+newObj).val()!=$("#"+newObj).attr("data")){
		$("#"+oldObj).html($("#"+newObj).attr("data"));
	}else{
		$("#"+oldObj).html("");
	}
} 


